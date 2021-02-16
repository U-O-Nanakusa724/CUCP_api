package biz.uoray.cucp.response;

import biz.uoray.cucp.entity.CarDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel("車種詳細")
public class ResponseCarDetail {

    public ResponseCarDetail(CarDetail carDetail) {
        this.id = carDetail.getId();
        this.responseCar = new ResponseCar(carDetail.getCar());
        this.color = carDetail.getColor();
        this.distance = carDetail.getDistance();
        this.mission = carDetail.getMission();
        this.modelYear = carDetail.getModelYear();
        this.url = carDetail.getUrl();
        this.note = carDetail.getNote();
    }

    @ApiModelProperty("車種詳細ID")
    private int id;

    @JsonProperty("car")
    @ApiModelProperty("車種")
    private ResponseCar responseCar;

//    @JsonProperty("store")
//    @ApiModelProperty("販売店")
//    private ResponseStore responseStore;

    @ApiModelProperty("カラー")
    private String color;

    @ApiModelProperty("走行距離")
    private double distance;

    @ApiModelProperty("ミッション")
    private String mission;

    @ApiModelProperty("年式")
    private String modelYear;

    @ApiModelProperty("URL")
    private String url;

    @ApiModelProperty("備考")
    private String note;

//    @OneToMany(mappedBy = "carDetail", fetch = FetchType.LAZY)
//    private List<Price> priceList;
}
