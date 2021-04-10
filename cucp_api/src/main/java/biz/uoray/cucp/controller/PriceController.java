package biz.uoray.cucp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import biz.uoray.cucp.request.RequestPrice;
import biz.uoray.cucp.response.ResponsePrice;
import biz.uoray.cucp.service.PriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@Controller
@RequestMapping(path = "/v1/price")
@Api(value = "価格API", tags = "PriceApi", produces = "application/json")
public class PriceController {

    @Autowired
    PriceService priceService;

    /**
     * 車種新規登録
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "価格を登録する", nickname = "createPrice")
    @PostMapping("/create")
    public ResponsePrice postPrice(@Validated @RequestBody RequestPrice requestPrice) throws Exception {
        return new ResponsePrice(priceService.createPrice(requestPrice));
    }

    @ResponseBody
    @ApiOperation(value = "価格を編集する", nickname = "updatePrice")
    @PutMapping("/update")
    public ResponsePrice putPrice(@Validated @RequestBody RequestPrice requestPrice) throws Exception {
        return new ResponsePrice(priceService.updatePrice(requestPrice));
    }

    @ResponseBody
    @ApiOperation(value = "価格を削除する", nickname = "deletePrice")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deletePrice(@PathVariable("id") Integer priceId) {
        priceService.deletePrice(priceId);
        return ResponseEntity.ok().build();
    }
}
