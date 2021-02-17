package biz.uoray.cucp.response;

import biz.uoray.cucp.entity.Store;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel("販売店")
public class ResponseStore {

    public ResponseStore(Store store){
        this.id = store.getId();
        this.name = store.getName();
    }

    @ApiModelProperty("販売店ID")
    int id;

    @ApiModelProperty("販売店名")
    String name;

}
