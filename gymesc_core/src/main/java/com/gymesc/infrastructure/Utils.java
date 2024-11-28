package com.gymesc.infrastructure;

import java.security.InvalidKeyException;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.Instant;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.gymesc.infrastructure.config.ConfigPropertiesReader;
import com.gymesc.infrastructure.jwt.JWTRequest;
import com.gymesc.infrastructure.translation.MessageUtils;

import net.oauth.jsontoken.JsonToken;
import net.oauth.jsontoken.JsonTokenParser;
import net.oauth.jsontoken.crypto.HmacSHA256Signer;
import net.oauth.jsontoken.crypto.HmacSHA256Verifier;
import net.oauth.jsontoken.crypto.SignatureAlgorithm;
import net.oauth.jsontoken.crypto.Verifier;
import net.oauth.jsontoken.discovery.VerifierProvider;
import net.oauth.jsontoken.discovery.VerifierProviders;

public class Utils {

    private static final Logger logger = LogManager.getLogger(Utils.class);

    private static final Pattern LOWERCASE_PATTERN = Pattern.compile(".*[a-z].*");
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile(".*[A-Z].*");
    private static final Pattern DIGIT_PATTERN = Pattern.compile(".*\\d.*");
    private static final Pattern SPECIAL_CHARACTER_PATTERN = Pattern.compile(".*[^a-zA-Z0-9].*");

    public static String encodePassword(String password) {
        GymescCypher encrypter = new GymescCypher(ConfigPropertiesReader.getInstance().getProperty("cypher.secret.key"));
        return encrypter.encrypt(password);
    }

    public static String decodePassword(String password) {
        GymescCypher encrypter = new GymescCypher(ConfigPropertiesReader.getInstance().getProperty("cypher.secret.key"));
        return encrypter.decrypt(password);
    }

    public static boolean isValidEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public static String validatePassword(String password) {
        if (password == null || password.length() < 8) {
            return MessageUtils.getMessage("validator.message.password.min.size");
        }
        if (!LOWERCASE_PATTERN.matcher(password).find()) {
            return MessageUtils.getMessage("validator.message.password.need.lowercase");
        }
        if (!UPPERCASE_PATTERN.matcher(password).find()) {
            return MessageUtils.getMessage("validator.message.password.need.uppercase");
        }
        if (!DIGIT_PATTERN.matcher(password).find()) {
            return MessageUtils.getMessage("validator.message.password.need.number");
        }
        if (!SPECIAL_CHARACTER_PATTERN.matcher(password).find()) {
            return MessageUtils.getMessage("validator.message.password.need.special.char");
        }
        return null; // Senha é forte o suficiente
    }

    public static JsonObject jwtToPayloadJsonObject(String jwt) {

        VerifierProviders locators = new VerifierProviders();

        JsonTokenParser parser = new JsonTokenParser(locators, jsonObject -> {});
        JsonToken jt = parser.deserialize(jwt);

        return jt.getPayloadAsJsonObject();
    }

    public static boolean validateJWT(String jwt, String secret) {

        try {
            final Verifier hmacVerifier = new HmacSHA256Verifier(secret.getBytes());
            VerifierProvider hmacLocator = (id, key) -> Lists.newArrayList(hmacVerifier);
            VerifierProviders locators = new VerifierProviders();
            locators.setVerifierProvider(SignatureAlgorithm.HS256, hmacLocator);

            // Ignore Audience does not mean that the Signature is ignored
            JsonTokenParser parser = new JsonTokenParser(locators, jsonObject -> {});
            boolean isValid = parser.signatureIsValid(jwt, Lists.newArrayList(hmacVerifier));

            if (isValid) {
                JsonToken jt = parser.deserialize(jwt);
                isValid = parser.expirationIsValid(jt, new Instant());
            }

            return isValid;

        } catch (InvalidKeyException e) {
            logger.error("Chave inválida " + e.getMessage(), e);
        }

        return false;
    }

    public static String convertToJWT(JWTRequest jwt) {
        try {
            HmacSHA256Signer signer = new HmacSHA256Signer(jwt.getIssuer(), null, jwt.getSecret().getBytes());

            JsonToken token = configureJsonToken(jwt, signer);

            // Configure request object
            JsonObject payload = token.getPayloadAsJsonObject();
            payload.add("request", jwt.getRequest());

            return token.serializeAndSign();

        } catch (Exception e) {
            logger.error("Erro ao converter para JWT " + e.getMessage(), e);
        }

        return null;
    }

    private static JsonToken configureJsonToken(JWTRequest jwt, HmacSHA256Signer signer) {
        JsonToken token = new JsonToken(signer);
        token.setAudience(jwt.getAudience());
        token.setParam("typ", jwt.getType());

        if (jwt.getIssuedAt() == null) {
            token.setIssuedAt(new Instant(new Date().getTime()));
        } else {
            token.setIssuedAt(new Instant(jwt.getIssuedAt().getTime()));
        }

        if (jwt.getExpiration() == null) {
            token.setExpiration(new Instant(new Date().getTime()));
        } else {
            token.setExpiration(new Instant(jwt.getExpiration().getTime()));
        }

        return token;
    }

    public static void main(String[] args) {
        System.out.println(encodePassword("1"));
        System.out.println(decodePassword("oPY/FNHRZPA="));
    }
}
