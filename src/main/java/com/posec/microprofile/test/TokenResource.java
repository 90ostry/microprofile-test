package com.posec.microprofile.test;

import java.net.URI;
import java.util.Collection;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.posec.microprofile.test.entity.Token;


@Path("/tokens")
public class TokenResource {
	
	@Context
	UriInfo info;
	
	@Inject
	JsonConverter converter;
	
	@Inject
	TokenService service;
	
	@GET
	public Response allTokens()
	{
		Collection<Token> tokens = service.allTokens();
		JsonArray array = converter.convert(tokens);
				
		if(null == tokens || tokens.isEmpty())
		{
			return Response.noContent().build();
		}
		return Response.ok(array).build();
	}
	
	@GET
	@Path("{id}")
	public Token findToken()
	{
		return null;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response generateToken(Token aToken)
	{
		Token token = service.generateToken(aToken.getDeviceId());
		JsonObject result = converter.convert(token);
	
		String value = result.getString("token");
		URI uri = info.getAbsolutePathBuilder().path("/" + value ).build();
		return Response.created(uri).entity(result).build();
	}
	
	@PUT
	public Token update(Token token)
	{
		return null;
	}
	
	@DELETE
	public void remove()
	{
		// TODO: to implement!
	}
	
}
