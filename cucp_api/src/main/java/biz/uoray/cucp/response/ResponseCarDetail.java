package biz.uoray.cucp.response;

import biz.uoray.cucp.entity.CarDetail;
import biz.uoray.cucp.entity.Price;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel("車種詳細")
public class ResponseCarDetail {

    public ResponseCarDetail(CarDetail carDetail) {
        this.detailId = carDetail.getId();
        this.responseGrade = new ResponseGrade(carDetail.getGrade());
        this.responseStore = new ResponseStore(carDetail.getStore());
        this.color = carDetail.getColor();
        this.distance = carDetail.getDistance();
        this.mission = carDetail.getMission();
        this.modelYear = carDetail.getModelYear();
        this.url = carDetail.getUrl();
        this.note = carDetail.getNote();
        this.priceList = carDetail.getPriceList() == null ? null : carDetail.getPriceList().stream()
                .sorted(Comparator.comparing(Price::getDate).reversed())
                .map(ResponsePrice::new)
                .collect(Collectors.toList());
    }

    @ApiModelProperty("車種詳細ID")
    private int detailId;

    @JsonProperty("grade")
    @ApiModelProperty("グレード")
    private ResponseGrade responseGrade;

    @JsonProperty("store")
    @ApiModelProperty("販売店")
    private ResponseStore responseStore;

    @ApiModelProperty("カラー")
    private String color;

    @ApiModelProperty("走行距離")
    private String distance;

    @ApiModelProperty("ミッション")
    private String mission;

    @ApiModelProperty("年式")
    private String modelYear;

    @ApiModelProperty("URL")
    private String url;

    @ApiModelProperty("備考")
    private String note;

    @ApiModelProperty("成約フラグ")
    private boolean soldFlag;

    @JsonProperty("prices")
    @ApiModelProperty("価格")
    private List<ResponsePrice> priceList;
}
