package biz.uoray.cucp.service;

import biz.uoray.cucp.entity.Car;
import biz.uoray.cucp.entity.CarDetail;
import biz.uoray.cucp.entity.Store;
import biz.uoray.cucp.repository.CarDetailRepository;
import biz.uoray.cucp.repository.CarRepository;
import biz.uoray.cucp.repository.StoreRepository;
import biz.uoray.cucp.request.RequestCarDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CarDetailService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    CarDetailRepository carDetailRepository;

    /**
     * 有効な車種一覧を取得する
     *
     * @return
     */
    public Page<CarDetail> getAll(Pageable pageable) {
        return carDetailRepository.findActive(pageable);
    }

    /**
     * 車種詳細を１件登録する
     *
     * @param requestCarDetail
     * @return
     */
    @Transactional
    public CarDetail createCarDetail(RequestCarDetail requestCarDetail) {

        // マスタ存在確認
        Car car = carRepository.getOne(requestCarDetail.getRequestCar().getId());
        Store store = storeRepository.getOne(requestCarDetail.getRequestStore().getId());

        // レコード作成
        CarDetail carDetail = new CarDetail();
        carDetail.setCar(car);
        carDetail.setStore(store);
        carDetail.setColor(requestCarDetail.getColor());
        carDetail.setDistance(requestCarDetail.getDistance());
        carDetail.setMission(requestCarDetail.getMission());
        carDetail.setModelYear(requestCarDetail.getModelYear());
        carDetail.setUrl(requestCarDetail.getUrl());
        carDetail.setNote(requestCarDetail.getNote());

        return carDetailRepository.save(carDetail);
    }

    /**
     * 車種詳細を１件更新する
     *
     * @param requestCarDetail
     * @return
     */
    public CarDetail updateCarDetail(RequestCarDetail requestCarDetail) {

        // マスタ存在確認
        Car car = carRepository.getOne(requestCarDetail.getRequestCar().getId());
        Store store = storeRepository.getOne(requestCarDetail.getRequestStore().getId());

        // レコード作成
        CarDetail carDetail = carDetailRepository.getOne(requestCarDetail.getId());
        carDetail.setCar(car);
        carDetail.setStore(store);
        carDetail.setColor(requestCarDetail.getColor());
        carDetail.setDistance(requestCarDetail.getDistance());
        carDetail.setMission(requestCarDetail.getMission());
        carDetail.setModelYear(requestCarDetail.getModelYear());
        carDetail.setUrl(requestCarDetail.getUrl());
        carDetail.setNote(requestCarDetail.getNote());

        return carDetailRepository.save(carDetail);
    }

    /**
     * 車種詳細を１件削除する
     *
     * @param id
     */
    public void deleteCarDetail(Integer id) {
        CarDetail carDetail = carDetailRepository.getOne(id);
        carDetail.setDeletedAt(new Date());
        carDetailRepository.save(carDetail);
    }
}
