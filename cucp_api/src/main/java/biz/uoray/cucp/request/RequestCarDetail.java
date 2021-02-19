package biz.uoray.cucp.request;

import biz.uoray.cucp.validation.PositiveDouble;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RequestCarDetail {

    @JsonProperty("car")
    @ApiModelProperty("車種ID")
    RequestCar requestCar;

    @JsonProperty("store")
    @ApiModelProperty("販売店")
    RequestStore requestStore;

    @Size(max = 64)
    @ApiModelProperty("カラー")
    String color;

    @PositiveDouble
    @ApiModelProperty("走行距離")
    double distance;

    @Size(max = 8)
    @ApiModelProperty("ミッション")
    String mission;

    @Size(max = 8)
    @ApiModelProperty("年式")
    String modelYear;

    @Size(max = 255)
    @ApiModelProperty("URL")
    String url;

    @Size(max = 64)
    @ApiModelProperty("備考")
    String note;

}
