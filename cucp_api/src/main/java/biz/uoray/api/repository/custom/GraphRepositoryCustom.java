package biz.uoray.api.repository.custom;

import biz.uoray.api.entity.CarDetail;
import biz.uoray.api.entity.Car;

import java.util.List;

public interface GraphRepositoryCustom {


    /**
     * carsの一覧を取得
     *
     * @return
     */
    public List<Car> findAll();

    /**
     * car_detailsの一覧を取得
     *
     * @param carId
     */
    public List<CarDetail> findByCarId(int carId);

}
