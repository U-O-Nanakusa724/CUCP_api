package biz.uoray.cucp.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel("例外")
public class ResponseException {

	public ResponseException(String message) {
		this.message = message;
	}

	@ApiModelProperty("メッセージ")
	String message;

}
