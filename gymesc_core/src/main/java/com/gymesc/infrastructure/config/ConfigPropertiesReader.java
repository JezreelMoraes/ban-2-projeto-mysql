package com.gymesc.infrastructure.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigPropertiesReader {

	private static ConfigPropertiesReader instance;
	private Properties props;

	private ConfigPropertiesReader() {
		try {
			this.loadProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized static ConfigPropertiesReader getInstance() {
		if (instance == null) {
			instance = new ConfigPropertiesReader();
		}

		return instance;
	}

	/**
	 * Carrega o arquivo de propriedades
	 * 
	 */
	private void loadProperties() throws IOException {
		this.props = new Properties();
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
		this.props.load(input);
		input.close();
	}

	public Properties getProperties() {
		return props;
	}

	public String getProperty(String propertyName) {
		String result = null;
		if (this.props != null) {
			result = props.getProperty(propertyName);
		}
		return result;
	}
}