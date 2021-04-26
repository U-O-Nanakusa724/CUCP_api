package biz.uoray.cucp.response;

import biz.uoray.cucp.entity.CarDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel("車種詳細一覧")
public class ResponseCarDetailList {

    /**
     * @param carDetails ページング付コンストラクタ
     */
    public ResponseCarDetailList(Page<CarDetail> carDetails) {
        this.carDetailList = carDetails.getContent().stream().map(ResponseCarDetail::new).collect(Collectors.toList());
        this.responsePageable = new ResponsePageable(carDetails);
    }

    /**
     * @param carDetails ページングなしのコンストラクタ
     */
    public ResponseCarDetailList(List<CarDetail> carDetails) {
        this.carDetailList = carDetails.stream().map(ResponseCarDetail::new).collect(Collectors.toList());
        this.responsePageable = null;
    }

    @JsonProperty("carDetails")
    @ApiModelProperty("車種詳細リスト")
    List<ResponseCarDetail> carDetailList;

    @JsonProperty("paging")
    @ApiModelProperty("ページング")
    ResponsePageable responsePageable;
}
