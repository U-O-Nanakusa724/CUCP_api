//package biz.uoray.cucp.controller;
//
//import biz.uoray.cucp.dto.GraphDto;
//import biz.uoray.cucp.service.GraphService;
//import biz.uoray.common.entity.Car;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/v1/graph")
//public class GraphController {
//
//    @Autowired
//    GraphService graphService;
//
//    /**
//     * 車種の選択肢表示
//     *
//     * @param model
//     * @return
//     */
//    @GetMapping()
//    public String getCar(Model model) {
//        List<Car> cars = graphService.searchAll();
//        model.addAttribute("carList", cars);
//        return "graph/index";
//    }
//
//    @PostMapping()
//    public String getCarGraph(@RequestParam("carId") Integer id, Model model) {
//        List<Car> cars = graphService.searchAll();
//        GraphDto car_details = graphService.searchDirectCarDetail(id);
//        model.addAttribute("carList", cars);
//        model.addAttribute("dateLabel", car_details.getDateLabelList());
//        model.addAttribute("priceLabel", car_details.getPriceLabelMap());
//        return "graph/index";
//    }
//}
//
//
