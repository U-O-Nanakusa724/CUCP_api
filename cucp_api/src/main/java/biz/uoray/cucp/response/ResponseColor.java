package biz.uoray.cucp.response;

import biz.uoray.cucp.entity.Color;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel("カラー")
public class ResponseColor {

    public ResponseColor(Color color) {
        this.colorId = color.getId();
        this.label = color.getLabel();
        this.colorCode = color.getColorCode();
    }

    @ApiModelProperty("ID")
    private int colorId;

    @ApiModelProperty("色名")
    private String label;

    @ApiModelProperty("カラーコード")
    private String colorCode;
}
