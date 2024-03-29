package biz.uoray.cucp.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RequestCar {

    @ApiModelProperty("ID")
    Integer carId;

    @Size(min = 1, max = 64)
    @ApiModelProperty("車種和名")
    String name;
}
