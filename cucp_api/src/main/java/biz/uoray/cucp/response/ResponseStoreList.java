package biz.uoray.cucp.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import biz.uoray.cucp.entity.Store;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel("販売店一覧")
public class ResponseStoreList {

	public ResponseStoreList(Page<Store> stores) {
		this.storeList = stores.getContent().stream().map(ResponseStore::new).collect(Collectors.toList());
		this.responsePageable = new ResponsePageable(stores);
	}

	@JsonProperty("stores")
	@ApiModelProperty("販売店リスト")
	List<ResponseStore> storeList;

	@JsonProperty("paging")
	@ApiModelProperty("ページング")
	ResponsePageable responsePageable;

}
