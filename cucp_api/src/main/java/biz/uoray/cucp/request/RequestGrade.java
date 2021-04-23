package biz.uoray.cucp.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RequestGrade {

    @ApiModelProperty("ID")
    Integer gradeId;

    @ApiModelProperty("車種ID")
    @NotNull
    Integer carId;

    @Size(min = 1, max = 64)
    @NotNull
    @NotBlank
    @ApiModelProperty("グレード")
    String grade;
}
