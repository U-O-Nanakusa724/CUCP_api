package biz.uoray.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("page")
    @ApiModelProperty("ページ")
    private long page;

    @JsonProperty("total_pages")
    @ApiModelProperty("総ページ数")
    private long totalPages;

    @JsonProperty("total_elements")
    @ApiModelProperty("総コンテンツ数")
    private long totalElements;

}
