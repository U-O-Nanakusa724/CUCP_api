package biz.uoray.cucp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import biz.uoray.cucp.response.ResponseException;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(CucpNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseException handleNotFoundException(CucpNotFoundException cnfe, WebRequest req) {
		return new ResponseException(cnfe.getMessage());
	}

	@ExceptionHandler(CucpBadRequestException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseException handleBadRequestException(CucpBadRequestException cbre, WebRequest req) {
		return new ResponseException(cbre.getMessage());
	}

	@ExceptionHandler(CucpSystemException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseException handleSystemException(CucpSystemException cse, WebRequest req) {
		return new ResponseException(cse.getMessage());
	}

}
