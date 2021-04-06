package biz.uoray.cucp.controller;

import biz.uoray.cucp.exception.CucpNotFoundException;
import biz.uoray.cucp.request.RequestColor;
import biz.uoray.cucp.response.ResponseColor;
import biz.uoray.cucp.response.ResponseColorList;
import biz.uoray.cucp.service.ColorService;
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
@RequestMapping(path = "/v1/colors")
@Api(value = "カラーAPI", tags = "ColorApi", produces = "application/json")
public class ColorController {

    @Autowired
    ColorService colorService;

    /**
     * カラーコード一覧表示
     */
    @ResponseBody
    @ApiOperation(value = "カラー一覧を取得する", nickname = "getColors")
    @GetMapping(produces = "application/json")
    public ResponseColorList getColor() {
        Pageable pageable = PageRequest.of(0, 20);
        return new ResponseColorList(colorService.getAll(pageable));
    }

    /**
     * カラーコード新規登録
     */
    @ResponseBody
    @ApiOperation(value = "カラーコード情報を登録する", nickname = "createColor")
    @PostMapping("/create")
    public ResponseColor postColor(@Validated @RequestBody RequestColor requestColor) {
        return new ResponseColor(colorService.createColor(requestColor));
    }

    /**
     * カラーコード編集
     */
    @ResponseBody
    @ApiOperation(value = "カラーコード情報を編集する", nickname = "updateColor")
    @PutMapping("/update")
    public ResponseColor putColor(@Validated @RequestBody RequestColor requestColor) {
        return new ResponseColor(colorService.updateColor(requestColor));
    }

    /**
     * 車種１件削除
     */
    @ResponseBody
    @ApiOperation(value = "カラーコード情報を削除する", nickname = "deleteColor")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteColor(@PathVariable("id") Integer id) throws CucpNotFoundException {
        colorService.deleteColor(id);
        return ResponseEntity.ok().build();
    }
}
