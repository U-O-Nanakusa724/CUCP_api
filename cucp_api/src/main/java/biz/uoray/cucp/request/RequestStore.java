package biz.uoray.cucp.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RequestStore {

    @ApiModelProperty("ID")
    int id;

    @Size(max = 64)
    @ApiModelProperty("販売店名")
    String name;
}
