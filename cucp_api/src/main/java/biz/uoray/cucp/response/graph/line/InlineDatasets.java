package biz.uoray.cucp.response.graph.line;

import biz.uoray.cucp.dto.GraphDto;
import biz.uoray.cucp.entity.CarDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel("車種詳細データ")
public class InlineDatasets {

    public InlineDatasets(CarDetail carDetail) {
        this.label = carDetail.getCar().getCode() + " " + carDetail.getStore().getName();
        this.priceList = carDetail.getPriceList().stream()
                .map(InlinePrice::new)
                .collect(Collectors.toList());
        this.borderColor = "#CFD8DC";
        this.fill = false;
        this.lineTension = 0.0;
    }

    @ApiModelProperty("車種+販売店名")
    private String label;

    @ApiModelProperty("車種詳細データ")
    @JsonProperty("data")
    private List<InlinePrice> priceList;

    @ApiModelProperty("線の色")
    @JsonProperty("borderColor")
    private String borderColor;

    @ApiModelProperty("下の塗りつぶし")
    private boolean fill;

    @ApiModelProperty("曲線度合")
    @JsonProperty("lineTension")
    private double lineTension;
}
