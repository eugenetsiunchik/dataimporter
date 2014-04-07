package by.eugene.importer.database.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DAOProperties {
	private static final Logger logger = LoggerFactory.getLogger(DAOProperties.class);
	private static final String PROPERTIES_FILE = "dao.properties";
	private static final Properties PROPERTIES = new Properties();
	/**
	 * Load properties file from file system
	 */
	static {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);

		if (propertiesFile == null) {
			logger.info("propertiesFile == null");
		}

		try {
			PROPERTIES.load(propertiesFile);
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

	private String specificKey;

	public DAOProperties(String specificKey) {
		this.specificKey = specificKey;
	}

	/**
	 * Get property value with specific key
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		String fullKey = specificKey + "." + key;
		String property = PROPERTIES.getProperty(fullKey);

		if (property == null || property.trim().length() == 0) {
			logger.info("Required property '" + fullKey + "'" + " is missing in properties file '" + PROPERTIES_FILE
					+ "'.");
		}
		return property;
	}

}
