package biz.uoray.cucp.service;

import biz.uoray.cucp.entity.Car;
import biz.uoray.cucp.repository.CarRepository;
import biz.uoray.cucp.request.RequestCar;
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
     * 車種を１台登録する
     *
     * @param requestCar
     */
    public Car createCar(RequestCar requestCar) {
        return carRepository.save(new Car(requestCar.getCode(), requestCar.getName()));
    }

    /**
     * 車種を１件更新する
     *
     * @param requestCar
     */
    public Car updateCar(RequestCar requestCar) {
        Car car = carRepository.getOne(requestCar.getId());
        if (car != null) {
            car.setCode(requestCar.getCode());
            car.setName(requestCar.getName());
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

    /**
     * キーワードを用いて検索した結果をページングで返す
     *
     * @param pageable
     * @param select
     * @param keyword
     * @return
     */
    public Page<Car> searchCar(Pageable pageable, String select, String keyword) {

        if (select.equals("code")) {
            return carRepository.searchByCode(pageable, keyword);
        } else if (select.equals("name")) {
            return carRepository.searchByName(pageable, keyword);
        } else {
            return carRepository.findActive(pageable);
        }
    }

}
