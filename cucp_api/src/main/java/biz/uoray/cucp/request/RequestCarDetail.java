package biz.uoray.cucp.request;

import biz.uoray.cucp.constant.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RequestCarDetail {

    @ApiModelProperty("ID")
    private Integer detailId;

    @ApiModelProperty("グレードID")
    private Integer gradeId;

    @ApiModelProperty("販売店")
    private Integer storeId;

    @ApiModelProperty("カラー")
    private Integer colorId;

    @Size(max = 16)
    @ApiModelProperty("走行距離")
    private String distance;

    @Size(max = 8)
    @ApiModelProperty("ミッション")
    private String mission;

    @ApiModelProperty("年式")
    @JsonFormat(pattern = Constants.MODEL_YEAR_FORMAT, timezone = Constants.JST)
    private Date modelYear;

    @Size(max = 255)
    @ApiModelProperty("URL")
    private String url;

    @Size(max = 64)
    @ApiModelProperty("備考")
    private String note;

    @ApiModelProperty("最新価格")
    private Double lastPrice;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @ApiModelProperty("最終確認日")
    private Date lastDate;
}
