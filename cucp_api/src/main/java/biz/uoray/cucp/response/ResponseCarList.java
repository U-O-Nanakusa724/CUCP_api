package biz.uoray.cucp.response;

import biz.uoray.cucp.entity.Car;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel("車種一覧")
public class ResponseCarList {

    public ResponseCarList(Page<Car> cars) {
        this.carList = cars.getContent().stream().map(ResponseCar::new).collect(Collectors.toList());
        this.responsePageable = new ResponsePageable(cars);

    }

    @JsonProperty("cars")
    @ApiModelProperty("車種リスト")
    List<ResponseCar> carList = new ArrayList<>();

    @JsonProperty("paging")
    @ApiModelProperty("ページング")
    ResponsePageable responsePageable;

}
