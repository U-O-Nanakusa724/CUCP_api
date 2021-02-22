package biz.uoray.cucp.response.graph;

import biz.uoray.cucp.dto.GraphDto;
import biz.uoray.cucp.response.graph.line.InlineDatasets;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel("グラフ親レスポンス")
public class ResponseChart {

    public ResponseChart(GraphDto graphDto) {
        this.labels = graphDto.getLabelList();
        this.inlineDatasetsList = graphDto.getCarDetailList().stream()
                .map(InlineDatasets::new)
                .collect(Collectors.toList());
    }

    @JsonFormat(pattern = "yyyy/MM/dd")
    @JsonProperty("labels")
    private List<Date> labels;

    @JsonProperty("datasets")
    private List<InlineDatasets> inlineDatasetsList;

//    TODO オプションは後日設計・実装
//    @JsonProperty("options")
//    private InlineOptions inlineOptions;

}
