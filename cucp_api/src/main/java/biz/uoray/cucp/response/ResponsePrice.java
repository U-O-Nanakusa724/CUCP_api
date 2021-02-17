package biz.uoray.cucp.response;

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
        this.price = price.getPrice();
        this.date = price.getDate();
    }

    @ApiModelProperty("価格")
    private double price;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @ApiModelProperty("日時")
    private Date date;

}
