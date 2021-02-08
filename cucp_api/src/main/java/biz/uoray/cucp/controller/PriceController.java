//package biz.uoray.cucp.controller;
//
//import biz.uoray.cucp.form.PriceForm;
//import biz.uoray.common.entity.Price;
//import biz.uoray.cucp.service.PriceService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/v1/prices")
//public class PriceController {
//
//    @Autowired
//    PriceService priceService;
//
//    /**
//     * 販売価格の新規登録
//     */
//    @GetMapping("/{id}/new")
//    public String createPrice(@PathVariable("id") Integer id, Model model, PriceForm priceForm) {
//        model.addAttribute("carDetailId", id);
//        return "price/new";
//    }
//
//    /**
//     * 販売価格の登録実行
//     *
//     * @param priceForm
//     * @param bindingResult
//     * @param model
//     * @return
//     */
//    @PostMapping("/create")
//    public String insertPrice(@Validated PriceForm priceForm, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("carDetailId", priceForm.getPriceId());
//            return "price/new";
//        }
//
//        priceService.createPrice(priceForm);
//        return "redirect:/cardetails";
//    }
//
//    /**
//     * 販売価格を１件編集する
//     *
//     * @param model
//     * @param priceForm
//     * @param id
//     * @return
//     */
//    @GetMapping("/{id}/edit")
//    public String editPrice(Model model, PriceForm priceForm, @PathVariable("id") Integer id) {
//        Price price = priceService.getActivePriceById(id);
//        priceForm.setDate(price.getDate());
//        priceForm.setPrice(price.getPrice());
//        model.addAttribute("priceId", id);
//        model.addAttribute("price", priceForm);
//        return "price/edit";
//    }
//
//    /**
//     * 販売価格の編集実行
//     *
//     * @param id
//     * @param priceForm
//     * @param bindingResult
//     * @param model
//     * @return
//     */
//    @PutMapping("/{id}/update")
//    public String updatePrice(@PathVariable("id") Integer id, @Validated PriceForm priceForm, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("priceId", id);
//            model.addAttribute("price", priceService.getActivePriceById(id));
//            return "price/edit";
//        }
//
//        priceService.updatePrice(priceForm);
//        return "redirect:/cardetails";
//    }
//
//    /**
//     * 販売価格を１件削除する
//     *
//     * @param id
//     * @return
//     */
//    @DeleteMapping("/{id}/delete")
//    public String deletePrice(@PathVariable("id") Integer id) {
//        priceService.deletePrice(id);
//        return "redirect:/cardetails";
//    }
//}
