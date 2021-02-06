package biz.uoray.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RequestCar {

    @Size(min = 1, max = 64)
    @JsonProperty("code")
    @ApiModelProperty("車種コード")
    String code;

    @Size(max = 64)
    @JsonProperty("name")
    @ApiModelProperty("車種和名")
    String name;
}
