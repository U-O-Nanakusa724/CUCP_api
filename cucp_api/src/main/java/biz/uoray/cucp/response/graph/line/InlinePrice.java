package biz.uoray.cucp.response.graph.line;

import biz.uoray.cucp.entity.Price;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel("価格")
public class InlinePrice {

    public InlinePrice(Price price){
        this.label = price.getDate();
        this.value = String.valueOf(price.getPrice());
    }

    @JsonFormat(pattern = "yyyy/MM/dd")
    @JsonProperty("x")
    private Date label;

    @JsonProperty("y")
    private String value;

}
