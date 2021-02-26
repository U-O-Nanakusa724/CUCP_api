package biz.uoray.cucp.request;

import biz.uoray.cucp.validation.PositiveDouble;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RequestPrice {

    @ApiModelProperty("ID")
    private int id;

    @JsonProperty("detail")
    @ApiModelProperty("車種ID")
    RequestCarDetail requestCarDetail;

    @PositiveDouble
    @ApiModelProperty("価格")
    private double price;

    @ApiModelProperty("日付")
    private Date date;
}
