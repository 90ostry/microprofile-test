package com.posec.microprofile.test;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collector;

import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import com.posec.microprofile.test.entity.Token;

@RequestScoped
public class JsonConverter {
	
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public JsonObject convert(Token token)
	{
		return Json.createObjectBuilder()
				.add("id", token.getId())
				.add("deviceId", token.getDeviceId() )
				.add("token", token.getToken())
				.add("validTo", 
						parseDate(token.getExpirationDate()))
				.build();
	}
	
	private String parseDate(Date validTo) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		return format.format(validTo);
	}

	public JsonArray convert(Collection<Token> tokens) {
		Collector<JsonObject, ? , JsonArrayBuilder > collector =
				Collector.of(Json::createArrayBuilder, JsonArrayBuilder::add, 
					(left, right) -> { 
						left.add(right);
						return left;
					} );
		
		return tokens.stream().map(this::convert).collect(collector).build();
	}	
}
