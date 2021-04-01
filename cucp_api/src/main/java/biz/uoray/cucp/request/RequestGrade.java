package biz.uoray.cucp.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RequestGrade {

    @ApiModelProperty("ID")
    int id;

    @ApiModelProperty("車種ID")
    @NotNull
    int carId;

    @Size(min = 1, max = 64)
    @NotNull
    @NotBlank
    @ApiModelProperty("グレード")
    String grade;
}
