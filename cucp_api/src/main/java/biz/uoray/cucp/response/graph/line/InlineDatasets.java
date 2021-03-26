package biz.uoray.cucp.response.graph.line;

import biz.uoray.cucp.dto.DatasetDto;
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

    public InlineDatasets(DatasetDto datasetDto) {
        this.label = datasetDto.getLabel();
        this.priceList = datasetDto.getData().stream().map(InlinePrice::new).collect(Collectors.toList());
        this.borderWidth = datasetDto.getBorderWidth();
        this.pointRadius = datasetDto.getPointRadius();
        this.pointHoverRadius = datasetDto.getPointHoverRadius();
        this.pointStyle = datasetDto.getPointStyle();
        this.fill = datasetDto.isFill();
        this.lineTension = datasetDto.getLineTension();
    }

    @ApiModelProperty("車種+販売店名")
    private String label;

    @ApiModelProperty("車種詳細データ")
    @JsonProperty("data")
    private List<Object> priceList;

    @ApiModelProperty("線の太さ")
    @JsonProperty("borderWidth")
    private int borderWidth;

    @ApiModelProperty("点の大きさ")
    @JsonProperty("pointRadius")
    private int pointRadius;

    @ApiModelProperty("マウスオーバー時の点の大きさ")
    @JsonProperty("pointHoverRadius")
    private int pointHoverRadius;

    @ApiModelProperty("点の形")
    @JsonProperty("pointStyle")
    private String pointStyle;

    @ApiModelProperty("下の塗りつぶし")
    private boolean fill;

    @ApiModelProperty("曲線度合")
    @JsonProperty("lineTension")
    private double lineTension;
}
