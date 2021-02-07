//package biz.uoray.api.service;
//
//import biz.uoray.api.dto.CarDetailDto;
//
//import biz.uoray.api.form.CarDetailForm;
//
//import biz.uoray.common.entity.Car;
//import biz.uoray.common.entity.CarDetail;
//import biz.uoray.common.entity.Price;
//import biz.uoray.common.entity.Store;
//import biz.uoray.common.repository.CarDetailRepository;
//import biz.uoray.common.repository.CarRepository;
//import biz.uoray.common.repository.StoreRepository;
//import biz.uoray.common.repository.implement.CarDetailRepositoryImpl;
//import biz.uoray.common.util.DateUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Service
//public class CarDetailService {
//
//    @Autowired
//    CarDetailRepositoryImpl carDetailRepositoryImpl;
//
//    @Autowired
//    CarRepository carRepository;
//
//    @Autowired
//    StoreRepository storeRepository;
//
//    @Autowired
//    CarDetailRepository carDetailRepository;
//
//    /**
//     * 有効な車種一覧を取得する
//     *
//     * @return
//     */
//
//    public List<Car> getActiveCarList() {
//        return carDetailRepositoryImpl.findAll().stream()
//                .filter(carEntity -> carEntity.getDeletedAt() == null)
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * 選択されたcar_idの車種詳細一覧を取得する
//     *
//     * @param carId
//     * @return
//     */
//
//    public List<CarDetail> getCarDetailListByCarId(int carId) {
//        return carDetailRepositoryImpl.findCarDetailByCarId(carId).stream()
//                .filter(carDetailEntity -> carDetailEntity.getDeletedAt() == null)
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * 選択されたcarDetailIdの車種詳細を取得する
//     *
//     * @param carDetailId
//     * @return
//     */
//    public CarDetailDto getCarDetailListByCarDetailId(int carDetailId) {
//        CarDetail carDetail = carDetailRepositoryImpl.findCarDetailByCarDetailId(carDetailId);
//        List<Price> priceEntityList = carDetail.getPriceList();
//        Map<String, Price> priceList = new HashMap<>();
//        priceEntityList.stream().filter(priceEntity -> priceEntity.getDeletedAt() == null)
//                .forEach(priceEntity -> {
//                    priceList.put(DateUtil.getLDT(priceEntity.getDate()), priceEntity);
//                });
//
//        return new CarDetailDto(carDetail, priceList);
//    }
//
//    /**
//     * 車種詳細を新規登録する
//     *
//     * @param carDetailForm
//     */
//    public void createCarDetail(CarDetailForm carDetailForm) {
//        Car car = carRepository.getOne(carDetailForm.getCarId());
//        Store store = storeRepository.getOne(carDetailForm.getStoreId());
//        CarDetail carDetail = new CarDetail();
//
//        carDetail.setCar(car);
//        carDetail.setStore(store);
//        carDetail.setColor(carDetailForm.getColor());
//        carDetail.setDistance(carDetailForm.getDistance());
//        carDetail.setTransmission(carDetailForm.getTransmission());
//        carDetail.setModelYear(carDetailForm.getModelYear());
//        carDetail.setUrl(carDetailForm.getUrl());
//        carDetail.setNote(carDetailForm.getNote());
//
//        carDetailRepository.save(carDetail);
//
//    }
//
//    /**
//     * 更新用のcar_detailのレコードを取得する
//     *
//     * @param carDetailId
//     * @return
//     */
//    public CarDetail getCarDetailOneByID(int carDetailId) {
//        return carDetailRepository.getOne(carDetailId);
//    }
//
//    /**
//     * 車種詳細を1件更新する
//     *
//     * @param carDetailId
//     * @param carDetailForm
//     */
//    public void updateCarDetail(int carDetailId, CarDetailForm carDetailForm) {
//        CarDetail carDetail = carDetailRepository.getOne(carDetailId);
//        if (carDetail != null) {
//            Store store = storeRepository.getOne(carDetailForm.getStoreId());
//            carDetail.setStore(store);
//            carDetail.setColor(carDetailForm.getColor());
//            carDetail.setDistance(carDetailForm.getDistance());
//            carDetail.setTransmission(carDetailForm.getTransmission());
//            carDetail.setModelYear(carDetailForm.getModelYear());
//            carDetail.setUrl(carDetailForm.getUrl());
//            carDetail.setNote(carDetailForm.getNote());
//
//            carDetailRepository.save(carDetail);
//        }
//    }
//
//    /**
//     * 対象の車種詳細の削除日を設定する
//     *
//     * @param carDetailId
//     */
//    public void deleteCarDetail(int carDetailId) {
//        CarDetail carDetail = carDetailRepository.getOne(carDetailId);
//        if (carDetail != null) {
//            carDetail.setDeletedAt(new Date());
//            carDetailRepository.save(carDetail);
//        }
//    }
//}
