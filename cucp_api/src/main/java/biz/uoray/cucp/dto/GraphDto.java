package biz.uoray.cucp.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GraphDto {

    // 折れ線グラフの横軸になる日付
    private List<Date> labelList;

    // 元データ
    private List<DatasetDto> datasetDto;

}
