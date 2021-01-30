package biz.uoray.api.controller;

import biz.uoray.api.entity.Car;
import biz.uoray.api.form.CarForm;
import biz.uoray.api.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1/cars")
@Api(value = "車種コントローラ", tags = "CarController", produces = "application/json")
public class CarController {

    @Autowired
    CarService carService;

    /**
     * 車種一覧表示
     *
     * @param model
     * @return
     */
    @ApiOperation(value = "車種一覧を取得する", nickname = "getCars")
    @GetMapping()
    public String getCar(Model model, Pageable pageable) {
        Page<Car> carEntityPage = carService.getAll(pageable);
        model.addAttribute("pages", carEntityPage);
        model.addAttribute("carList", carEntityPage.getContent());
        model.addAttribute("url", "/cars");
        return "car/index";
    }

    /**
     * 車種新規登録画面表示
     *
     * @return
     */
    @ApiOperation(value = "車種情報を登録する", nickname = "newCar")
    @GetMapping("/new")
    public String createCar(CarForm carForm) {
        return "car/new";
    }

    /**
     * 車種新規登録実行
     *
     * @param carForm
     * @param bindingResult
     * @return
     */
    @ApiOperation(value = "車種情報を登録する", nickname = "createCar")
    @PostMapping("/create")
    public String insertCar(@Validated CarForm carForm,
                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "car/new";
        }

        carService.createCar(carForm.getCode(), carForm.getName());
        return "redirect:/cars";
    }

    /**
     * 車種編集画面表示
     *
     * @param model
     * @param carForm
     * @param id
     * @return
     */
    @ApiOperation(value = "車種情報を編集する", nickname = "createCar")
    @GetMapping("/{id}/edit")
    public String editCar(Model model,
                          CarForm carForm,
                          @PathVariable("id") Integer id) {
        Car car = carService.getActiveCarById(id);
        carForm.setCode(car.getCode());
        carForm.setName(car.getName());
        model.addAttribute("carId", car.getId());
        model.addAttribute("car", carForm);
        return "car/edit";
    }

    /**
     * 車種編集実行
     *
     * @param id
     * @param carForm
     * @param bindingResult
     * @return
     */
    @ApiOperation(value = "車種情報を編集する", nickname = "createCar")
    @PutMapping("/{id}/update")
    public String updateCar(Model model,
                            @PathVariable("id") Integer id,
                            @Validated CarForm carForm,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("carId", id);
            model.addAttribute("car", carForm);
            return "car/edit";
        }

        carService.updateCar(id, carForm.getCode(), carForm.getName());
        return "redirect:/cars";
    }

    /**
     * 車種１件削除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "車種情報を削除する", nickname = "createCar")
    @DeleteMapping("/{id}/delete")
    public String deleteCar(@PathVariable("id") Integer id) {

        carService.deleteCar(id);
        return "redirect:/cars";
    }
}
