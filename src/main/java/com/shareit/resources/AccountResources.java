package com.shareit.resources;

import javax.ws.rs.Path;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.cxf.rs.security.cors.CorsHeaderConstants;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.apache.cxf.rs.security.cors.LocalPreflight;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.shareit.entity.Account;
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
		Account[] account = new Account[] {queryService.findAccountById(id)};
		return Response.ok(account).build();
		
	}
		
	@POST
	@Path("register")
	@Produces("application/json")
	public void addAccount(Account account) {
		//TODO TRY CATCH
		persistenceService.saveAccount(account);
	   // return Response.created(null).status(Response.Status.CREATED).build();
		//return Response.ok().build();
	}
	
//	@POST
//	@Path("login1")
//    @Consumes(MediaType.APPLICATION_JSON)
//	public Response login12(@FormParam("accountname") String accountName, @FormParam("password") String password, @Context HttpHeaders headers ) {
//		System.out.println(accountName);
//		System.out.println(password);
//		if(!securityUtil.authenticateAccount(accountName, password)){
//			throw new SecurityException("Username or password is incorrect");
//		}
//		String token = getToken(accountName);
//		return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
//		//applicationState.se
//	}
	
	@POST
	@Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Account account ) {
	
		if(!securityUtil.authenticateAccount(account.getAccountName(), account.getPassword())){
			throw new SecurityException("Username or password is incorrect");
		}
		String token = getToken(account.getAccountName());
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", token);
		String[] tokenVal = new String[] {token};
		return Response.ok(tokenMap).build();
		//applicationState.se
	}
	
	private String getToken(String username) {
		Key key = securityUtil.generateKey(username);
		String token = Jwts.builder().setSubject(username).setIssuer(uriInfo.getAbsolutePath().toString()).setIssuedAt(new Date()).setExpiration(securityUtil.toDate(LocalDateTime.now().plusMinutes(15))).signWith(SignatureAlgorithm.HS512, key).setAudience(uriInfo.getBaseUri().toString()).compact();
		System.out.println(token);
		return token;
	}
	

}
