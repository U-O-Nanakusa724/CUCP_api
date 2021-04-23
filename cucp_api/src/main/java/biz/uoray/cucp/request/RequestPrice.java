package biz.uoray.cucp.request;

import biz.uoray.cucp.validation.PositiveDouble;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RequestPrice {

    @ApiModelProperty("価格ID")
    private Integer priceId;

    @ApiModelProperty("車種詳細ID")
    private Integer detailId;

    @PositiveDouble
    @ApiModelProperty("価格")
    private Double price;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @ApiModelProperty("日付")
    private Date date;
}
