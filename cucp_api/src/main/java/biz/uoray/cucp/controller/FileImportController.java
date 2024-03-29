package biz.uoray.cucp.controller;

import biz.uoray.cucp.dto.CsvResultDto;
import biz.uoray.cucp.request.RequestCsvResult;
import biz.uoray.cucp.response.ResponseCarDetailList;
import biz.uoray.cucp.response.ResponseReadResult;
import biz.uoray.cucp.service.FileImportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@Controller
@RequestMapping(path = "/v1/files")
@Api(value = "ファイル取り込みAPI", tags = "FileImportApi", produces = "application/json")
public class FileImportController {

    @Autowired
    FileImportService fileImportService;

    @ResponseBody
    @ApiOperation(value = "CSVファイルを読み込む", nickname = "readCSV")
    @PostMapping("/readCSV")
    public ResponseReadResult read(@RequestParam("csv") MultipartFile csv) {
        CsvResultDto csvResultDto= fileImportService.readCsvAndReturnDataList(csv);
        return new ResponseReadResult(fileImportService.createNewDetails(csvResultDto));
    }

    @ResponseBody
    @ApiOperation(value = "CSVから取り込み編集し終えた車種詳細を登録する", nickname = "saveCSV")
    @PostMapping("/saveCSV")
    public ResponseCarDetailList postCsv(@RequestBody RequestCsvResult requestCsvResult) {
        return new ResponseCarDetailList(fileImportService.saveNewDetails(requestCsvResult));
    }

}
