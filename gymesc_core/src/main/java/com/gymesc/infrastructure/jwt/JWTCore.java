package com.gymesc.infrastructure.jwt;

import com.google.gson.JsonObject;
import com.gymesc.web.security.auth.UserBearerToken;
import com.gymesc.infrastructure.config.ConfigPropertiesReader;
import com.gymesc.infrastructure.Utils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class JWTCore {

	public static final String USER_BEARER_IDENTIFICATION = "** 01";

	private static String getSecretKeyJWT() {
		return ConfigPropertiesReader.getInstance().getProperty("jwt.secret");
	}

	public static String parseUserBearerTokenToJWT(UserBearerToken token) {
		JsonObject jwtToken = new JsonObject();
		jwtToken.addProperty("id", token.getId());
		jwtToken.addProperty("email", token.getEmail());
		jwtToken.addProperty("name", token.getFirstName());

		Calendar expirationDateCalendar = Calendar.getInstance();
		expirationDateCalendar.add(Calendar.YEAR, 1);
		Date dateCalendar = DateUtils.truncate(expirationDateCalendar.getTime(), Calendar.DATE);

		JWTRequest jwtRequest = new JWTRequest();
		jwtRequest.setIssuedAt(new Date());
		jwtRequest.setExpiration(dateCalendar);
		jwtRequest.setRequest(jwtToken);
		jwtRequest.setSecret(getSecretKeyJWT());

		return Utils.convertToJWT(jwtRequest);
	}

	public static UserBearerToken parseJWTToUserBearerToken(String jwt, boolean validate) {

		if (validate && !Utils.validateJWT(jwt, getSecretKeyJWT())) {
			return null;
		}

		JsonObject jwtObj = Utils.jwtToPayloadJsonObject(jwt);
		JsonObject jsonObject = jwtObj.get("request").getAsJsonObject();

		UserBearerToken userBearerToken = new UserBearerToken();
		userBearerToken.setId(jsonObject.has("id") ? jsonObject.get("id").getAsLong() : null);
		userBearerToken.setEmail(jsonObject.has("email") ? jsonObject.get("email").getAsString() : null);
		userBearerToken.setFirstName(jsonObject.has("name") ? jsonObject.get("name").getAsString() : null);

		return userBearerToken;
	}
}