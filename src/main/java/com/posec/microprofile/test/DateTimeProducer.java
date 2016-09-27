package com.posec.microprofile.test;

import java.time.LocalDateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class DateTimeProducer {
	
	@Produces
	LocalDateTime currentDateTime()
	{
		return LocalDateTime.now();
	}
}

