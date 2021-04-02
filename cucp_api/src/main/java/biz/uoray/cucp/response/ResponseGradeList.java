package biz.uoray.cucp.response;

import biz.uoray.cucp.entity.Grade;
import biz.uoray.cucp.entity.Store;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ResponseGradeList {

    public ResponseGradeList(Page<Grade> grades) {
        this.gradeList = grades.getContent().stream().map(ResponseGrade::new).collect(Collectors.toList());
        this.responsePageable = new ResponsePageable(grades);
    }

    @JsonProperty("grades")
    @ApiModelProperty("グレードリスト")
    List<ResponseGrade> gradeList;

    @JsonProperty("paging")
    @ApiModelProperty("ページング")
    ResponsePageable responsePageable;
}
