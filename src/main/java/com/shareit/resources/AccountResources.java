package com.shareit.resources;

import javax.ws.rs.Path;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import com.shareit.entityinterface.AccountManager;
import com.shareit.service.PersistenceService;
import com.shareit.service.QueryService;

@Path("account")
@Produces("application/json")
@Consumes("application/json")
public class AccountResources {

	@Inject
	AccountManager accountManager;
	@Inject
	QueryService queryService;
	@Inject PersistenceService persistenceService;

	@GET
	@Path("{id:[0-9]*}") 
	public Response getAccount(@PathParam("id") @DefaultValue("0") Long id) {	
		return Response.ok(queryService.findAccountById(id)).status(Response.Status.FOUND).build();
		
	}
		
	@POST
	public void addAccount() {
		
		persistenceService.saveAccount();
	//	return Response.created(null).status(Response.Status.CREATED).build();

	}
}
