//package biz.uoray.cucp.service;
//
//import biz.uoray.cucp.dto.GraphDto;
//
//import biz.uoray.common.entity.Car;
//import biz.uoray.common.entity.CarDetail;
//import biz.uoray.common.entity.Price;
//import biz.uoray.common.repository.implement.GraphRepositoryImpl;
//import biz.uoray.common.util.DateUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Service
//@Transactional(rollbackOn = Exception.class)
//public class GraphService {
//
//    @Autowired
//    GraphRepositoryImpl graphRepositoryImpl;
//
//    /**
//     * 車種一覧を取得する
//     *
//     * @return
//     */
//    public List<Car> searchAll() {
//        List<Car> carList = graphRepositoryImpl.findAll().stream()
//                .filter(carEntity -> carEntity.getDeletedAt() == null)
//                .collect(Collectors.toList());
//        return carList;
//    }
//
//    /**
//     * 車種詳細や価格データを取得し、整形してDTOに格納する
//     *
//     * @param id
//     * @return
//     */
//    public GraphDto searchDirectCarDetail(int id) {
//        List<CarDetail> carDetailList = graphRepositoryImpl.findByCarId(id);
//        List<Date> dateList = new ArrayList<Date>();
//        Map<String, String[]> priceLabelMap = new HashMap<String, String[]>();
//        GraphDto graphDto = new GraphDto();
//
//        // 日付ラベル生成処理
//        for (CarDetail carDetail : carDetailList) {
//            for (Price price : carDetail.getPriceList()) {
//                if (dateList.indexOf(price.getDate()) < 0) {
//                    dateList.add(price.getDate());
//                }
//            }
//        }
//        Collections.sort(dateList);
//
//        // 価格ラベル生成処理
//        for (CarDetail carDetail : carDetailList) {
//
//            String[] stringDateList = new String[dateList.size()];
//            // 車名を「車種コード 販売店名_id（car_detail）」
//            String codeName = carDetail.getCar().getCode() + " " + carDetail.getStore().getName() + "_" + carDetail.getId();
//
//            // 価格が存在する日付を判定
//            for (Price price : carDetail.getPriceList()) {
//                int index = dateList.indexOf(price.getDate());
//                if (index >= 0) {
//                    stringDateList[index] = String.valueOf(price.getPrice());
//                }
//            }
//            priceLabelMap.put(codeName, stringDateList);
//        }
//
//        graphDto.setDateLabelList(DateUtil.getLDTList(dateList));
//        graphDto.setPriceLabelMap(priceLabelMap);
//
//        return graphDto;
//    }
//
//}
