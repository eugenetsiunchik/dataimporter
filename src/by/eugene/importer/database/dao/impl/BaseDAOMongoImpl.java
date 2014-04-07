package by.eugene.importer.database.dao.impl;

import java.net.UnknownHostException;

import by.eugene.importer.database.dao.DAOProperties;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class BaseDAOMongoImpl {

	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_PORT = "port";
	private static final String PROPERTY_DATABASE = "database";
	protected MongoClient mongo;
	protected DB db;

	/**
	 * Initializing properties values with specific key
	 * 
	 * @param propertyName
	 * @throws UnknownHostException
	 */
	public BaseDAOMongoImpl(String propertyName) throws UnknownHostException {
		DAOProperties properties = new DAOProperties(propertyName);
		String url = properties.getProperty(PROPERTY_URL);
		int port = Integer.parseInt(properties.getProperty(PROPERTY_PORT));
		String database = properties.getProperty(PROPERTY_DATABASE);

		mongo = new MongoClient(url, port);
		db = mongo.getDB(database);
	}
}
