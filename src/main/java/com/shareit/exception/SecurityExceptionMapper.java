package com.shareit.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SecurityExceptionMapper implements ExceptionMapper<SecurityException> {
	public Response toResponse(SecurityException exception) {
		return Response.status(Response.Status.FORBIDDEN).entity("invalid username or password").header("Application-Error", "Computer Says No").build();
	}
}
