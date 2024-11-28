package com.gymesc.infrastructure.jwt;

import java.util.Date;

import com.google.gson.JsonObject;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JWTRequest {

	private String issuer;
	private String secret;
	private String audience;
	private String type;
	private Date issuedAt;
	private Date expiration;
	private JsonObject request;
}
