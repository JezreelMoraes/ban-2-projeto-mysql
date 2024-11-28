package com.gymesc.infrastructure;

import java.nio.charset.StandardCharsets;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GymescCypher {
	Logger logger = LogManager.getLogger(GymescCypher.class);

	Cipher ecipher;
	Cipher dcipher;

	byte[] salt = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03 };

	int iterationCount = 19;

	public GymescCypher(String passPhrase) {
		try {
			KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
			SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
			ecipher = Cipher.getInstance(key.getAlgorithm());
			dcipher = Cipher.getInstance(key.getAlgorithm());

			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

			ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public String encrypt(String str) {
		try {
			byte[] utf8 = str.getBytes(StandardCharsets.UTF_8);
			byte[] enc = ecipher.doFinal(utf8);

			return Base64.encodeBase64String(enc);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public String decrypt(String str) {
		try {
			byte[] dec = Base64.decodeBase64(str);
			byte[] utf8 = dcipher.doFinal(dec);

			return new String(utf8, StandardCharsets.UTF_8);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
}