package biz.uoray.cucp.response;

import biz.uoray.cucp.entity.Car;
import com.fasterxml.jackson.annotation.JsonProperty;
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
        this.code = car.getCode();
        this.name = car.getName();
    }

    @JsonProperty("id")
    @ApiModelProperty("車種ID")
    int id;

    @JsonProperty("code")
    @ApiModelProperty("車種コード")
    String code;

    @JsonProperty("name")
    @ApiModelProperty("車種和名")
    String name;

}
