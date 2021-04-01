package biz.uoray.cucp.response;

import biz.uoray.cucp.entity.Car;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel("車種")
public class ResponseCar {

    public ResponseCar(Car car) {
        this.id = car.getId();
        this.name = car.getName();
    }

    @ApiModelProperty("車種ID")
    int id;

    @ApiModelProperty("車種和名")
    String name;

}
