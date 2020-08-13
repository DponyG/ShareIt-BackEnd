package com.shareit.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

	@Override
	public Response toResponse(ServiceException exception) {
		return Response.status(exception.getStatusCode()).entity(exception.getMessage()).build();
	}
	
}
