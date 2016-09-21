package com.posec.microprofile.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/test")
public class TestEndpoint {
	
	@GET 
	@Produces("text/plain")
	public Response testGet()
	{
		return Response.ok("Test get method!").build();
	}

}
