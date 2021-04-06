package biz.uoray.cucp.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RequestColor {

    @ApiModelProperty("ID")
    private int colorId;

    @Size(max = 16)
    @ApiModelProperty("色名")
    private String label;

    @Size(max = 8)
    @ApiModelProperty("カラーコード")
    private String colorCode;
}
