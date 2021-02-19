package biz.uoray.cucp.service;

import biz.uoray.cucp.entity.CarDetail;
import biz.uoray.cucp.repository.CarDetailRepository;
import biz.uoray.cucp.repository.CarRepository;
import biz.uoray.cucp.repository.StoreRepository;
import biz.uoray.cucp.request.RequestCarDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public CarDetail createCarDetail(RequestCarDetail requestCarDetail) {
        CarDetail carDetail = new CarDetail();
        carDetail.setCar(carRepository.getOne(requestCarDetail.getRequestCar().getId()));
        carDetail.setStore(storeRepository.getOne(requestCarDetail.getRequestStore().getId()));
        carDetail.setColor(requestCarDetail.getColor());
        carDetail.setDistance(requestCarDetail.getDistance());
        carDetail.setMission(requestCarDetail.getMission());
        carDetail.setModelYear(requestCarDetail.getModelYear());
        carDetail.setUrl(carDetail.getUrl());
        carDetail.setNote(carDetail.getNote());

        return carDetailRepository.save(carDetail);
    }

}
