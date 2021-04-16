package biz.uoray.cucp.response;

import biz.uoray.cucp.dto.CsvResultDto;
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

    public ResponseReadResult(CsvResultDto csvResultDto) {

        this.newCarList = csvResultDto.getNewCarList() == null ? null :
                csvResultDto.getNewCarList().stream().map(ResponseCar::new).collect(Collectors.toList());
        this.newGradeList = csvResultDto.getNewGradeList() == null ? null :
                csvResultDto.getNewGradeList().stream().map(ResponseGrade::new).collect(Collectors.toList());
        this.newStoreList = csvResultDto.getNewStoreList() == null ? null :
                csvResultDto.getNewStoreList().stream().map(ResponseStore::new).collect(Collectors.toList());
        this.newColorList = csvResultDto.getNewColorList() == null ? null :
                csvResultDto.getNewColorList().stream().map(ResponseColor::new).collect(Collectors.toList());
        this.newCarDetailList = csvResultDto.getNewCarDetailList() == null ? null :
                csvResultDto.getNewCarDetailList().stream().map(ResponseCarDetail::new).collect(Collectors.toList());
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

    @JsonProperty("new_colors")
    @ApiModelProperty("色リスト")
    private List<ResponseColor> newColorList;

    @JsonProperty("new_details")
    @ApiModelProperty("詳細リスト")
    private List<ResponseCarDetail> newCarDetailList;

}
