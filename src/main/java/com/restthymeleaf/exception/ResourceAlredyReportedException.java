package com.restthymeleaf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.IM_USED)
public class ResourceAlredyReportedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceAlredyReportedException(String errorMessage) {
		super(errorMessage);
	}
}
