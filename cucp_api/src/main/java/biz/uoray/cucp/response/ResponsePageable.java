package biz.uoray.cucp.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel("ページング")
public class ResponsePageable {

    public ResponsePageable(Page page) {
        this.page = page.getPageable().getOffset();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }

    @ApiModelProperty("ページ")
    private long page;

    @ApiModelProperty("総ページ数")
    private long totalPages;

    @ApiModelProperty("総コンテンツ数")
    private long totalElements;

}
