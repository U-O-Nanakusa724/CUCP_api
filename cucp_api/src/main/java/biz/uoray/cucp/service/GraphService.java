package biz.uoray.cucp.service;

import biz.uoray.cucp.dto.GraphDto;

import biz.uoray.cucp.entity.CarDetail;
import biz.uoray.cucp.entity.Price;
import biz.uoray.cucp.repository.CarDetailRepository;
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
     * @return
     */
    public GraphDto getCarDetailForGraph() {
        List<CarDetail> carDetailList = carDetailRepository.findActive(PageRequest.of(0,10000))
                .getContent();

        GraphDto graphDto = new GraphDto();
        graphDto.setCarDetailList(carDetailList);

        // 日付ラベル生成処理
        List<Date> dateList= new ArrayList<>();
        for (CarDetail carDetail : carDetailList) {
            for (Price price : carDetail.getPriceList()) {
                if (!dateList.contains(price.getDate())) {
                    dateList.add(price.getDate());
                }
            }
        }
        Collections.sort(dateList);
        return graphDto;
    }

}
