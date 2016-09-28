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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.posec.microprofile.test.entity.Token;

@Path("/tokens")
@Produces(MediaType.APPLICATION_JSON)
public class TokenResource {

	@Context
	UriInfo info;

	@Inject
	JsonConverter converter;

	@Inject
	TokenService service;

	@GET
	public Response allTokens() {
		Collection<Token> tokens = service.allTokens();
		JsonArray array = converter.convert(tokens);

		if (null == tokens || tokens.isEmpty()) {
			return Response.noContent().build();
		}
		return Response.ok(array).build();
	}

	@GET
	@Path("{id}")
	public Token read(@PathParam("id") long id) {
		return service.find(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response generateToken(Token aToken) {
		Token token = service.generateToken(aToken.getDeviceId());
		JsonObject result = converter.convert(token);

		String value = result.getString("token");
		URI uri = info.getAbsolutePathBuilder().path("/" + value).build();
		return Response.created(uri).entity(result).build();
	}

	@DELETE
	@Path("{id}")
	public Response remove(@PathParam("id") long id) {

		try {
			service.remove(id);
			return Response.ok().build();
		} catch (IllegalArgumentException e) {
			return Response.noContent().build();
		}

	}
}
