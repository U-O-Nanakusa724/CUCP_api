package biz.uoray.cucp.controller;

import biz.uoray.cucp.exception.CucpBadRequestException;
import biz.uoray.cucp.exception.CucpNotFoundException;
import biz.uoray.cucp.exception.CucpSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import biz.uoray.cucp.response.ResponseException;

import java.util.Locale;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler{

	@Autowired
	MessageSource messageSource;

	/**
	 * NotFound系
	 */
	@ExceptionHandler(CucpNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseException handleNotFoundException(CucpNotFoundException e, Locale locale) {
		String message = messageSource.getMessage(e.getMessage(),null, locale);
		return new ResponseException(message);
	}

	/**
	 * BadRequest系
	 */
	@ExceptionHandler(CucpBadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseException handleBadRequestException(CucpBadRequestException e, Locale locale) {
		String message = messageSource.getMessage(e.getMessage(),null, locale);
		return new ResponseException(message);
	}

	/**
	 * SystemError系
	 */
	@ExceptionHandler(CucpSystemException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseException handleSystemException(CucpSystemException e, Locale locale) {
		String message = messageSource.getMessage(e.getMessage(),null, locale);
		return new ResponseException(message);
	}

}
