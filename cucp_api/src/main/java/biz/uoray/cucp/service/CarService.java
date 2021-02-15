package biz.uoray.cucp.service;

import biz.uoray.cucp.entity.Car;
import biz.uoray.cucp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    /**
     * 有効な車種一覧を取得する
     *
     * @param pageable
     * @return
     */
    public Page<Car> getAll(Pageable pageable) {
        return carRepository.findActive(pageable);
    }

    /**
     * 有効な車種を１件IDで取得する
     *
     * @param carId
     * @return
     */
    public Car getActiveCarById(Integer carId) {
        return carRepository.getOne(carId);
    }

    /**
     * 車種を１台登録する
     *
     * @param code
     * @param name
     */
    public Car createCar(String code, String name) {
        return carRepository.save(new Car(code, name));
    }

    /**
     * 車種を１件更新する
     *
     * @param carId
     * @param code
     * @param name
     */
    public Car updateCar(Integer carId, String code, String name) {
        Car car = carRepository.getOne(carId);
        if (car != null) {
            car.setCode(code);
            car.setName(name);
        }
        return carRepository.save(car);
    }

    /**
     * 対象の車種の削除日を設定する
     *
     * @param carId
     */
    public void deleteCar(Integer carId) {
        Car car = carRepository.getOne(carId);
        if (car != null) {
            car.setDeletedAt(new Date());
            carRepository.save(car);
        }
    }
}