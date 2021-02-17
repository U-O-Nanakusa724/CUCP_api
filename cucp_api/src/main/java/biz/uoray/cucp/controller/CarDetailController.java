package biz.uoray.cucp.controller;

import biz.uoray.cucp.request.RequestCarDetail;
import biz.uoray.cucp.response.ResponseCarDetail;
import biz.uoray.cucp.response.ResponseCarDetailList;
import biz.uoray.cucp.service.CarDetailService;
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
@RequestMapping("/v1/cardetails")
@Api(value = "車種詳細コントローラ", tags = "CarDetailController", produces = "application/json")
public class CarDetailController {

    @Autowired
    CarDetailService carDetailService;

    /**
     * 車種詳細一覧表示
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "車種詳細一覧を取得する", nickname = "getCarDetails")
    @GetMapping(produces = "application/json")
    public ResponseCarDetailList getCarDetail() {
        Pageable pageable = PageRequest.of(0, 20);
        return new ResponseCarDetailList(carDetailService.getAll(pageable));
    }

    @ResponseBody
    @ApiOperation(value = "車種詳細を登録する", nickname = "createCarDetail")
    @PostMapping("/create")
    public ResponseCarDetail postCarDetail(@Validated @RequestBody RequestCarDetail requestCarDetail) throws Exception {
        return new ResponseCarDetail(carDetailService.createCarDetail(requestCarDetail));
    }

    @ResponseBody
    @ApiOperation(value = "車種詳細を編集する", nickname = "updateCarDetail")
    @PutMapping("/{id}/update")
    public ResponseCarDetail putCar() throws Exception {
        return null;
    }

    @ResponseBody
    @ApiOperation(value = "車種詳細を削除する", nickname = "deleteCarDetail")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteCarDetail(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().build();
    }


}