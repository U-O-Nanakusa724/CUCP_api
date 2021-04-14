package biz.uoray.cucp.response;

import biz.uoray.cucp.entity.Car;
import biz.uoray.cucp.entity.CarDetail;
import biz.uoray.cucp.entity.Grade;
import biz.uoray.cucp.entity.Store;
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
@ApiModel("CSV取り込み結果")
public class ResponseReadResult {

    public ResponseReadResult(List<Car> newCarList,
                              List<Grade> newGradeList,
                              List<Store> newStoreList,
                              List<CarDetail> newDetailList) {

        this.newCarList = newCarList == null ? null :
                newCarList.stream().map(ResponseCar::new).collect(Collectors.toList());
        this.newGradeList = newGradeList == null ? null :
                newGradeList.stream().map(ResponseGrade::new).collect(Collectors.toList());
        this.newStoreList = newStoreList == null ? null :
                newStoreList.stream().map(ResponseStore::new).collect(Collectors.toList());
        this.newCarDetailList = newDetailList == null ? null :
                newDetailList.stream().map(ResponseCarDetail::new).collect(Collectors.toList());
    }

    @JsonProperty("new_cars")
    @ApiModelProperty("車種リスト")
    private List<ResponseCar> newCarList;

    @JsonProperty("new_grades")
    @ApiModelProperty("グレードリスト")
    private List<ResponseGrade> newGradeList;

    @JsonProperty("new_stores")
    @ApiModelProperty("販売店リスト")
    private List<ResponseStore> newStoreList;

    @JsonProperty("new_details")
    @ApiModelProperty("詳細リスト")
    private List<ResponseCarDetail> newCarDetailList;

}
