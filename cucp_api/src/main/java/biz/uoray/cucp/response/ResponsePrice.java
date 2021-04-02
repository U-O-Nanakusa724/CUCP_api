package biz.uoray.cucp.response;

import biz.uoray.cucp.constant.Constants;
import biz.uoray.cucp.entity.Price;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel("販売価格日時")
public class ResponsePrice {

    public ResponsePrice(Price price) {
        this.priceId = price.getId();
        this.detailId = price.getCarDetail().getId();
        this.price = price.getPrice();
        this.date = price.getDate();
    }

    @ApiModelProperty("価格ID")
    int priceId;

    @ApiModelProperty("車種詳細ID")
    int detailId;

    @ApiModelProperty("価格")
    private double price;

    @JsonFormat(pattern = Constants.PRICE_FORMAT, timezone = Constants.JST)
    @ApiModelProperty("日時")
    private Date date;

}
