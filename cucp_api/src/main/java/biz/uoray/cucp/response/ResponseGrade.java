package biz.uoray.cucp.response;

import biz.uoray.cucp.entity.Grade;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel("グレード")
public class ResponseGrade {

    public ResponseGrade(Grade grade) {
        this.gradeId = grade.getId();
        this.responseCar = new ResponseCar(grade.getCar());
        this.grade = grade.getGrade();
    }

    @ApiModelProperty("グレードID")
    private Integer gradeId;

    @JsonProperty("car")
    @ApiModelProperty("車種")
    private ResponseCar responseCar;

    @ApiModelProperty("グレード名")
    String grade;


}
