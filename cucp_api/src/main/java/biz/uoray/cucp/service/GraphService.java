package biz.uoray.cucp.service;

import biz.uoray.cucp.constant.PointStyle;
import biz.uoray.cucp.dto.DatasetDto;
import biz.uoray.cucp.dto.GraphDto;

import biz.uoray.cucp.entity.CarDetail;
import biz.uoray.cucp.entity.Price;
import biz.uoray.cucp.repository.CarDetailRepository;
import biz.uoray.cucp.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackOn = Exception.class)
public class GraphService {

    @Autowired
    CarDetailRepository carDetailRepository;

    /**
     * 車種詳細を取得し、整形してDTOに格納する
     *
     * @return グラフ出力用DTO
     */
    public GraphDto getCarDetailForGraph() {
        List<CarDetail> carDetailList = carDetailRepository.findActive(PageRequest.of(0, 10000))
                .getContent();
        // TODO Entityで対応できればここのロジックは不要
        carDetailList.forEach(carDetail -> {
            List<Price> priceList = carDetail.getPriceList();
            priceList.removeIf(price -> price.getDeletedAt() != null);
            carDetail.setPriceList(priceList);
        });

        GraphDto graphDto = new GraphDto();

        // 日付ラベル生成処理
        List<Date> dateList = new ArrayList<>();
        carDetailList.forEach(carDetail -> carDetail.getPriceList()
                .forEach(price -> {
                    if (!dateList.contains(price.getDate())) {
                        dateList.add(price.getDate());
                    }
                }));
        Collections.sort(dateList);
        graphDto.setLabelList(dateList);

        // データをDatasetsDTOに格納、変換
        List<DatasetDto> datasetDtoList = carDetailList
                .stream()
                .filter(carDetail -> carDetail.getPriceList().size() != 0)
                .map(carDetail -> {
                    DatasetDto datasetDto = new DatasetDto();
                    // ラベル
                    datasetDto.setLabel(carDetail.getGrade().getGrade() + " " + carDetail.getStore().getName());
                    // 値段データ(無効データふるい落とし＋日付順ソート)
                    List<Object> priceList = carDetail.getPriceList()
                            .stream()
                            .filter(price -> price.getDeletedAt() == null)
                            .sorted(Comparator.comparing(Price::getDate))
                            .collect(Collectors.toList());
                    datasetDto.setData(priceList);

                    datasetDto.setBorderWidth(1);
                    datasetDto.setPointRadius(5);
                    datasetDto.setPointHoverRadius(20);
                    // 価格データの数が２つ以上だったら点の形を判別
                    if (datasetDto.getData().size() > 1) {
                        datasetDto.setPointStyle(comparePrices(datasetDto.getData()).getStyle());
                    } else {
                        datasetDto.setPointStyle(PointStyle.CIRCLE.getStyle());
                    }
                    datasetDto.setFill(false);
                    datasetDto.setLineTension(0.0);
                    return datasetDto;
                })
                .collect(Collectors.toList());

        graphDto.setDatasetDto(datasetDtoList);

        return graphDto;
    }

    /**
     * 最後の価格と直前の価格を比較し、点の形を変える
     *
     * @param priceList 価格データが入ったリスト(Object)
     * @return PointStyle
     */
    private PointStyle comparePrices(List<Object> priceList) {
        int size = priceList.size();
        List<Price> comparePriceList = ObjectUtil.convertObjectToList(priceList);

        double lastPrice = comparePriceList.get(size - 1).getPrice();
        double beforePrice = comparePriceList.get(size - 2).getPrice();

        if (lastPrice > beforePrice) {
            return PointStyle.STAR;
        } else if (lastPrice == beforePrice) {
            return PointStyle.CIRCLE;
        } else {
            return PointStyle.TRIANGLE;
        }
    }
}
