package com.huynhthanhnha.restExample.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.huynhthanhnha.restExample.entity.ExceptionResponse;

@Provider
public class NotFoundException extends RuntimeException implements ExceptionMapper<NotFoundException> {

	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		super("Not found");
	}
	
	public NotFoundException(String message) {
		super(message);
	}
	
	@Override
	public Response toResponse(NotFoundException exception) {
		// TODO Auto-generated method stub
		return Response.status(404).entity(new ExceptionResponse(404, exception.getMessage()))
				.type(MediaType.APPLICATION_JSON).build();
	}

}
