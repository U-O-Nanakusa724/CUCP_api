package biz.uoray.cucp.dto;

import biz.uoray.cucp.entity.CarDetail;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GraphDto {

    // 元データ
    private List<CarDetail> carDetailList;

    // 折れ線グラフの横軸になる日付
    private List<Date> labelList;

}
