package com.posec.microprofile.test;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class TimeResource {

	@Inject
	LocalDateTime time;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getTime()
	{
		return LocalDateTime.now().toString();
	}
	
	@GET
	@Path("/datetime")
	@Produces(MediaType.TEXT_PLAIN)
	public JsonObject currentDateTime()
	{
		return Json.createObjectBuilder()
				.add("date", Json.createObjectBuilder()
						.add("dayOfWeek", time.getDayOfWeek().name())
						.add("day", time.getDayOfMonth())
						.add("month", time.getMonth().name())
						.add("year", time.getYear()))
				.add("time", Json.createObjectBuilder()
						.add("seconds", time.getSecond())
						.add("minutes", time.getMinute())
						.add("hour",time.getHour()))
				.build();
	}
	
}
