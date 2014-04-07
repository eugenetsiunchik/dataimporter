package by.eugene.importer.database.dao.impl;

import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.eugene.importer.database.dao.ClassificationDAO;
import by.eugene.importer.models.ClassificationDataModel;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class ClassificationDAOMongoImpl extends BaseDAOMongoImpl implements ClassificationDAO {

	private static final String TABLE_NAME = "classification";
	private static final String FILE = "file";
	private static final String TITLE = "title";
	private static final String UNITS = "units";
	private static final String FREQUENCY = "frequency";
	private static final String SESONAL_ADJUSTMENT = "sesonal_adjustment";
	private static final String LAST_UPDATE = "last_update";

	private static final Logger logger = LoggerFactory.getLogger(ClassificationDAOMongoImpl.class);

	public ClassificationDAOMongoImpl(String propertyName) throws UnknownHostException {
		super(propertyName);
	}

	@Override
	public void deleteClassificationDataAll() throws UnknownHostException {

		DBCollection table = db.getCollection(TABLE_NAME);
		table.drop();

	}

	private BasicDBObject getClassificationDBObject(ClassificationDataModel model) {

		BasicDBObject document = new BasicDBObject();
		document.put(FILE, model.getFile());
		document.put(TITLE, model.getTitle());
		document.put(UNITS, model.getUnits());
		document.put(FREQUENCY, model.getFrequency());
		document.put(SESONAL_ADJUSTMENT, model.getSeasonalAdjustment());
		document.put(LAST_UPDATE, model.getLastUpdate());
		return document;

	}

	@Override
	public void createClassificationData(ClassificationDataModel model) throws UnknownHostException {
		DBCollection table = db.getCollection(TABLE_NAME);
		table.insert(getClassificationDBObject(model));
		logger.debug(getClassificationDBObject(model).toString());
	}

	@Override
	public void createClassificationDataAll(List<ClassificationDataModel> list) throws UnknownHostException {
		for (ClassificationDataModel generalModel : list) {
			createClassificationData(generalModel);
		}
	}

	@Override
	public Iterator<?> getClassificationDataAll() {
		DBCollection table = db.getCollection(TABLE_NAME);
		Iterator<DBObject> cursor = table.find();
		return cursor;
	}

}
