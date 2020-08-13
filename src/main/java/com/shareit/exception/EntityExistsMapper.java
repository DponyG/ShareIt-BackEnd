package com.shareit.exception;

import javax.persistence.EntityExistsException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EntityExistsMapper implements ExceptionMapper<EntityExistsException>{

	public Response toResponse(EntityExistsException exception) {
		return Response.status(Response.Status.FORBIDDEN).entity("{\"error\":\"Device cannot be null or empty.\"}").build();
	}
}
