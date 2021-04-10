package biz.uoray.cucp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import biz.uoray.cucp.exception.CucpNotFoundException;
import biz.uoray.cucp.request.RequestGrade;
import biz.uoray.cucp.response.ResponseGrade;
import biz.uoray.cucp.response.ResponseGradeList;
import biz.uoray.cucp.service.GradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@CrossOrigin
@Controller
@RequestMapping("/v1/grades")
@Api(value = "グレードAPI", tags = "GradeApi", produces = "application/json")
public class GradeController {

    @Autowired
    GradeService gradeService;

    /**
     * グレード一覧取得
     */
    @ResponseBody
    @ApiOperation(value = "グレード一覧を取得する", nickname = "getGrades")
    @GetMapping(produces = "application/json")
    public ResponseGradeList getGrade() {
        Pageable pageable = PageRequest.of(0, 20);
        return new ResponseGradeList(gradeService.getAll(pageable));
    }

    /**
     * グレード新規登録
     */
    @ResponseBody
    @ApiOperation(value = "グレード情報を登録する", nickname = "createGrade")
    @PostMapping("/create")
    public ResponseGrade postGrade(@Validated @RequestBody RequestGrade requestGrade) {
        return new ResponseGrade(gradeService.createGrade(requestGrade));
    }

    /**
     * グレード編集
     */
    @ResponseBody
    @ApiOperation(value = "グレードを編集する", nickname = "updateGrade")
    @PutMapping(value="/update")
    public ResponseGrade putGrade(@Validated @RequestBody RequestGrade requestGrade) {
        return new ResponseGrade(gradeService.updateGrade(requestGrade));
    }

    /**
     * グレード削除
     */
    @ResponseBody
    @ApiOperation(value = "グレード情報を削除する", nickname = "deleteGrade")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteCar(@PathVariable("id") Integer gradeId) throws CucpNotFoundException {
        gradeService.deleteGrade(gradeId);
        return ResponseEntity.ok().build();
    }
}
