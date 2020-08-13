package com.shareit.resources;

import javax.ws.rs.Path;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.shareit.entity.Account;
import com.shareit.entity.jpa.JpaAccount;
import com.shareit.entityinterface.AccountManager;
import com.shareit.service.PersistenceService;
import com.shareit.service.QueryService;
import com.shareit.service.SecurityUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("account")
@Produces("application/json")
@Consumes("application/json")
public class AccountResources {


	@Inject
	AccountManager accountManager;
	@Inject
	QueryService queryService;
	@Inject PersistenceService persistenceService;	
	@Inject SecurityUtil securityUtil;
	
	@Context
	private UriInfo uriInfo;

	@GET
	@Path("{id:[0-9]*}") 
	@Produces("application/json")
	public Response getAccount(@PathParam("id") @DefaultValue("0") Long id) {	
		JpaAccount[] account = new JpaAccount[] {queryService.findAccountById(id)};
		return Response.ok(account).build();
		
	}
		
	@POST
	@Path("register")
	@Produces("application/json")
	public Response addAccount(JpaAccount account) {
		if(queryService.doesAccountNameExist(account)) {
			throw new EntityExistsException();
		}
		persistenceService.saveAccount(account);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Long.toString(account.getId()));
	    return Response.created(builder.build()).build();
	}
	

	@POST
	@Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Account account ) {
		if(!securityUtil.authenticateAccount(account.getAccountName(), account.getPassword())){
			throw new SecurityException();
		}
		String token = getToken(account.getAccountName());
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", token);
		return Response.ok(tokenMap).build();
	}
	
	private String getToken(String username) {
		Key key = securityUtil.generateKey(username);
		String token = Jwts.builder().setSubject(username).setIssuer(uriInfo.getAbsolutePath().toString()).setIssuedAt(new Date()).setExpiration(securityUtil.toDate(LocalDateTime.now().plusMinutes(15))).signWith(SignatureAlgorithm.HS512, key).setAudience(uriInfo.getBaseUri().toString()).compact();
		System.out.println(token);
		return token;
	}
	

}
