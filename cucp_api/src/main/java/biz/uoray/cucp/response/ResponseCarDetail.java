package biz.uoray.cucp.response;

import biz.uoray.cucp.constant.Constants;
import biz.uoray.cucp.entity.CarDetail;
import biz.uoray.cucp.entity.Price;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel("車種詳細")
public class ResponseCarDetail {

    public ResponseCarDetail(CarDetail carDetail) {
        this.detailId = carDetail.getId();
        this.responseGrade = new ResponseGrade(carDetail.getGrade());
        this.responseStore = new ResponseStore(carDetail.getStore());
        this.responseColor = new ResponseColor(carDetail.getColor());
        this.distance = carDetail.getDistance();
        this.mission = carDetail.getMission();
        this.modelYear = carDetail.getModelYear();
        this.url = carDetail.getUrl();
        this.note = carDetail.getNote();
        Optional.ofNullable(carDetail.getPriceList()).ifPresent(prices -> {
            this.priceList = prices.stream()
                    .sorted(Comparator.comparing(Price::getDate).reversed())
                    .map(ResponsePrice::new)
                    .collect(Collectors.toList());
            if (this.priceList.size() > 0) {
                this.lastPrice = this.priceList.get(0).getPrice();
                this.lastDate = this.priceList.get(0).getDate();
            }
        });
    }

    @ApiModelProperty("車種詳細ID")
    private Integer detailId;

    @JsonProperty("grade")
    @ApiModelProperty("グレード")
    private ResponseGrade responseGrade;

    @JsonProperty("store")
    @ApiModelProperty("販売店")
    private ResponseStore responseStore;

    @JsonProperty("color")
    @ApiModelProperty("カラー")
    private ResponseColor responseColor;

    @ApiModelProperty("走行距離")
    private String distance;

    @ApiModelProperty("ミッション")
    private String mission;

    @ApiModelProperty("年式")
    @JsonFormat(pattern = Constants.MODEL_YEAR_FORMAT, timezone = Constants.JST)
    private Date modelYear;

    @ApiModelProperty("URL")
    private String url;

    @ApiModelProperty("備考")
    private String note;

    @ApiModelProperty("最新価格")
    private Double lastPrice;

    @JsonFormat(pattern = Constants.SIMPLE_DATE_FORMAT, timezone = Constants.JST)
    @ApiModelProperty("最新確認日")
    private Date lastDate;

    @ApiModelProperty("成約フラグ")
    private Boolean soldFlag;

    @JsonProperty("prices")
    @ApiModelProperty("価格")
    private List<ResponsePrice> priceList;
}
