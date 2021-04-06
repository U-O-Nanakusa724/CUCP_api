package biz.uoray.cucp.response;

import biz.uoray.cucp.entity.Color;
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
@ApiModel("カラー一覧")
public class ResponseColorList {

    public ResponseColorList(Page<Color> colors) {
        this.colorList = colors.getContent().stream().map(ResponseColor::new).collect(Collectors.toList());
        this.responsePageable = new ResponsePageable(colors);
    }

    @JsonProperty("colors")
    @ApiModelProperty("カラーリスト")
    List<ResponseColor> colorList;

    @JsonProperty("paging")
    @ApiModelProperty("ページング")
    ResponsePageable responsePageable;

}
