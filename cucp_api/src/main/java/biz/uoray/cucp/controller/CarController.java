package biz.uoray.cucp.controller;

import biz.uoray.cucp.exception.CucpNotFoundException;
import biz.uoray.cucp.request.RequestCar;
import biz.uoray.cucp.response.ResponseCar;
import biz.uoray.cucp.response.ResponseCarList;
import biz.uoray.cucp.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping(path = "/v1/cars")
@Api(value = "車種API", tags = "CarApi", produces = "application/json")
public class CarController {

    @Autowired
    CarService carService;

    /**
     * 車種一覧表示
     */
    @ResponseBody
    @ApiOperation(value = "車種一覧を取得する", nickname = "getCars")
    @GetMapping(produces = "application/json")
    public ResponseCarList getCar() {
        Pageable pageable = PageRequest.of(0, 20);
        return new ResponseCarList(carService.getAll(pageable));
    }

    /**
     * 車種新規登録
     */
    @ResponseBody
    @ApiOperation(value = "車種情報を登録する", nickname = "createCar")
    @PostMapping("/create")
    public ResponseCar postCar(@Validated @RequestBody RequestCar requestCar) {
        return new ResponseCar(carService.createCar(requestCar));
    }

    /**
     * 車種編集
     */
    @ResponseBody
    @ApiOperation(value = "車種情報を編集する", nickname = "updateCar")
    @PutMapping("/update")
    public ResponseCar putCar(@Validated @RequestBody RequestCar requestCar) throws CucpNotFoundException {
        return new ResponseCar(carService.updateCar(requestCar));
    }

    /**
     * 車種１件削除
     */
    @ResponseBody
    @ApiOperation(value = "車種情報を削除する", nickname = "deleteCar")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteCar(@PathVariable("id") Integer id) throws CucpNotFoundException {
        carService.deleteCar(id);
        return ResponseEntity.ok().build();
    }

    /**
     * 車種検索
     */
    @ResponseBody
    @ApiOperation(value = "車種を検索する", nickname = "searchCars")
    @GetMapping(value = "/search", produces = "application/json")
    public ResponseCarList searchCar(@RequestParam("keyword") String keyword) {
        Pageable pageable = PageRequest.of(0, 20);
        return new ResponseCarList(carService.searchCar(pageable, keyword));
    }
}
