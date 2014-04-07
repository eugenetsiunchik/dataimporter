package by.eugene.importer.database.dao;

import java.net.UnknownHostException;

import by.eugene.importer.database.dao.impl.ClassificationDAOMongoImpl;
import by.eugene.importer.database.dao.impl.EssentialDAOMongoImpl;

public class DAOFactory {

	private static DAOFactory instance;

	public static DAOFactory getInstance() throws Exception {

		if (instance == null) {
			instance = new DAOFactory();
		}
		return instance;
	}

	/**
	 * Get to work with the implementation classification data on the mongo
	 * database
	 */
	public ClassificationDAO getClassificationDAOMongoImpl(String propertyName) throws UnknownHostException {
		return new ClassificationDAOMongoImpl(propertyName);
	}

	/**
	 * Get to work with the implementation essential data on the mongo database
	 */
	public EssentialDAO getEssentialDAOMongoImpl(String propertyName) throws UnknownHostException {
		return new EssentialDAOMongoImpl(propertyName);
	}

}