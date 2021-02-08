//package biz.uoray.cucp.controller;
//
//import biz.uoray.cucp.form.CarDetailForm;
//import biz.uoray.cucp.form.StoreForm;
//import biz.uoray.cucp.service.CarDetailService;
//import biz.uoray.cucp.service.CarService;
//import biz.uoray.cucp.service.StoreService;
//import biz.uoray.common.entity.CarDetail;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/v1/cardetails")
//public class CarDetailController {
//
//    @Autowired
//    CarDetailService carDetailService;
//
//    @Autowired
//    CarService carService;
//
//    @Autowired
//    StoreService storeService;
//
//    /**
//     * 車種一覧を取得する
//     *
//     * @param model
//     * @return
//     */
//    @GetMapping()
//    public String getCarDetail(Model model) {
//        model.addAttribute("carList", carDetailService.getActiveCarList());
//        return "car_detail/index";
//    }
//
//    /**
//     * 指定された車種の詳細一覧を取得する
//     *
//     * @param id
//     * @param model
//     * @return
//     */
//    @GetMapping("/list")
//    public String showCarListByCarID(@RequestParam("carId") Integer id,
//                                     Model model) {
//        model.addAttribute("carId", id);
//        model.addAttribute("carDetailList", carDetailService.getCarDetailListByCarId(id));
//        return "car_detail/list";
//    }
//
//    @GetMapping("/{id}/show")
//    public String showCarDetailByCarID(@PathVariable("id") Integer id,
//                                       Model model) {
//        model.addAttribute("carDetailDto", carDetailService.getCarDetailListByCarDetailId(id));
//        return "car_detail/show";
//    }
//
//    @GetMapping("/{id}/new")
//    public String createCarDetail(@PathVariable("id") Integer id,
//                                  CarDetailForm carDetailForm, StoreForm storeForm,
//                                  Model model) {
//        model.addAttribute("car", carService.getActiveCarById(id));
//        model.addAttribute("storeList", storeService.getActiveStoreList());
//        return "car_detail/new";
//    }
//
//    @PostMapping("/{id}/create")
//    public String insertCarDetail(@PathVariable("id") Integer id,
//                                  @Validated CarDetailForm carDetailForm,
//                                  @Validated StoreForm storeForm,
//                                  BindingResult bindingResult,
//                                  Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("car", carService.getActiveCarById(id));
//            model.addAttribute("storeList", storeService.getActiveStoreList());
//            return "car_detail/new";
//        }
//
//        int storeId = 0;
//        // 販売店が新規作成の場合
//        if (!storeForm.getName().equals("none")) {
//            storeService.createStore(storeForm.getName());
//            storeId = storeService.getStoreIdByName(storeForm.getName());
//            carDetailForm.setStoreId(storeId);
//        }
//
//        carDetailService.createCarDetail(carDetailForm);
//        return "redirect:/cardetails";
//
//    }
//
//    @GetMapping("/{id}/edit")
//    public String editCarDetail(@PathVariable("id") Integer id,
//                                Model model,
//                                CarDetailForm carDetailForm,
//                                StoreForm storeForm) {
//        CarDetail carDetail = carDetailService.getCarDetailOneByID(id);
//        carDetailForm.setCarId(carDetail.getCar().getId());
//        carDetailForm.setStoreId(carDetail.getStore().getId());
//        carDetailForm.setColor(carDetail.getColor());
//        carDetailForm.setDistance(carDetail.getDistance());
//        carDetailForm.setTransmission(carDetail.getTransmission());
//        carDetailForm.setModelYear(carDetail.getModelYear());
//        carDetailForm.setUrl(carDetail.getUrl());
//        carDetailForm.setNote(carDetail.getNote());
//        model.addAttribute("car", carDetail.getCar());
//        model.addAttribute("carDetailId", carDetail.getId());
//        model.addAttribute("carDetail", carDetailForm);
//        model.addAttribute("storeList", storeService.getActiveStoreList());
//        return "car_detail/edit";
//    }
//
//    @PutMapping("/{id}/update")
//    public String updateCarDetail(@PathVariable("id") Integer id,
//                                  @Validated CarDetailForm carDetailForm,
//                                  @Validated StoreForm storeForm,
//                                  BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("car", carService.getActiveCarById(carDetailForm.getCarId()));
//            model.addAttribute("carDetailId", id);
//            model.addAttribute("carDetail", carDetailForm);
//            model.addAttribute("storeList", storeService.getActiveStoreList());
//            return "car_detail/edit";
//        }
//
//        int storeId = 0;
//        // 販売店が新規作成の場合
//        if (!storeForm.getName().equals("none")) {
//            storeService.createStore(storeForm.getName());
//            storeId = storeService.getStoreIdByName(storeForm.getName());
//            carDetailForm.setStoreId(storeId);
//        }
//
//        carDetailService.updateCarDetail(id, carDetailForm);
//        return "redirect:/cardetails";
//    }
//
//    @DeleteMapping("/{id}/delete")
//    public String deleteCarDetail(@PathVariable("id") Integer id) {
//        carDetailService.deleteCarDetail(id);
//        return "redirect:/cardetails";
//    }
//}