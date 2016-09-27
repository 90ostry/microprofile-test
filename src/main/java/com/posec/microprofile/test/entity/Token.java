package com.posec.microprofile.test.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Token {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	Long id;
	
	String deviceId;
	String token;
	Date expirationDate;
		
	public Token(String deviceId, String token, Date expirationDate) {
		this.deviceId = deviceId;
		this.token = token;
		this.expirationDate = expirationDate;
	}

	public Token() {
	}	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}
	public String getToken() {
		return token;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}	
		
}
