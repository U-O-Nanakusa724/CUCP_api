package biz.uoray.api.controller;

import biz.uoray.api.request.RequestCar;
import biz.uoray.api.response.ResponseCar;
import biz.uoray.api.response.ResponseCarList;
import biz.uoray.api.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/v1/cars")
@Api(value = "車種コントローラ", tags = "CarController", produces = "application/json")
public class CarController {

    @Autowired
    CarService carService;

    /**
     * 車種一覧表示
     *
     * @return 車種一覧Json
     */
    @ResponseBody
    @ApiOperation(value = "車種一覧を取得する", nickname = "getCars")
    @GetMapping(produces = "application/json")
    public ResponseCarList getCar() throws Exception {
        Pageable pageable = PageRequest.of(0, 20);
        return new ResponseCarList(carService.getAll(pageable));
    }

    /**
     * 車種新規登録
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "車種情報を登録する", nickname = "createCar")
    @PostMapping("/create")
    public ResponseCar postCar(@RequestBody RequestCar requestCar) throws Exception {
        return new ResponseCar(carService.createCar(requestCar.getCode(), requestCar.getName()));
    }

    /**
     * 車種編集
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "車種情報を編集する", nickname = "updateCar")
    @PutMapping("/{id}/update")
    public ResponseCar putCar(@PathVariable("id") Integer id,
                              @Validated @RequestBody RequestCar requestCar) throws Exception {
        return new ResponseCar(carService.updateCar(id, requestCar.getCode(), requestCar.getName()));
    }

    /**
     * 車種１件削除
     *
     * @param id
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "車種情報を削除する", nickname = "deleteCar")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteCar(@PathVariable("id") Integer id) {
        carService.deleteCar(id);
        return ResponseEntity.ok().build();
    }
}
