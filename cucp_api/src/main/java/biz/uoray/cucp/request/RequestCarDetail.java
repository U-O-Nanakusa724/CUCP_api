package biz.uoray.cucp.request;

import biz.uoray.cucp.validation.PositiveDouble;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RequestCarDetail {

    @ApiModelProperty("ID")
    private int detailId;

    @ApiModelProperty("車種ID")
    private int carId;

    @ApiModelProperty("販売店")
    private int storeId;

    @Size(max = 64)
    @ApiModelProperty("カラー")
    private String color;

    @PositiveDouble
    @ApiModelProperty("走行距離")
    private String distance;

    @Size(max = 8)
    @ApiModelProperty("ミッション")
    private String mission;

    @Size(max = 8)
    @ApiModelProperty("年式")
    private String modelYear;

    @Size(max = 255)
    @ApiModelProperty("URL")
    private String url;

    @Size(max = 64)
    @ApiModelProperty("備考")
    private String note;

    @ApiModelProperty("成約フラグ")
    private boolean soldFlag;
}
