package by.eugene.importer.database.dao.impl;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.eugene.importer.csv.EssentialCsvProcessor;
import by.eugene.importer.database.dao.EssentialDAO;
import by.eugene.importer.models.EssentialDataModel;
import by.eugene.importer.models.interfaces.DataModel;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class EssentialDAOMongoImpl extends BaseDAOMongoImpl implements EssentialDAO {

	private static final String TABLE_NAME = "essential";
	private static final String DATE = "date";
	private static final String VALUE = "value";
	private static final String TYPE_ID = "type_id";
	private static final Logger logger = LoggerFactory.getLogger(EssentialDAOMongoImpl.class);
	private BasicDBObject basicDBObject;

	public EssentialDAOMongoImpl(String propertyName) throws UnknownHostException {
		super(propertyName);
	}

	@Override
	public void createEssentialData(EssentialDataModel model) {

		DBCollection table = db.getCollection(TABLE_NAME);
		table.insert(getEssentialDBObject(model));
		logger.debug(getEssentialDBObject(model).toString());
	}

	@Override
	public void createEssentialDataAll(List<EssentialDataModel> list) {
		for (EssentialDataModel essentialDataModel : list) {
			createEssentialData(essentialDataModel);
		}
	}

	@Override
	public void deleteEssentialDataAll() {
		DBCollection table = db.getCollection(TABLE_NAME);
		table.drop();
	}

	private DBObject getEssentialDBObject(EssentialDataModel model) {
		BasicDBObject document = new BasicDBObject();
		document.put(TYPE_ID, model.getType_id());
		document.put(DATE, model.getDate());
		document.put(VALUE, model.getValue());
		return document;
	}

	@Override
	public void createEssentialDataAll(Iterator<?> cursor, String tempPathEssentialData) throws IOException {
		int iterator = 0;
		EssentialCsvProcessor essentialCsvProcessor = new EssentialCsvProcessor();
		List<DataModel> listEssentialData = new ArrayList<>();

		while (cursor.hasNext()) {
			createBasicDBObject((Map<?,?>) cursor.next());

			String stringClassificationId = getStringClassificationId();
			String essentialLocalPath = getEssentialLocalPath();

			String essentialFilePath = tempPathEssentialData + essentialLocalPath;
			essentialCsvProcessor.saveDataModelInList(essentialFilePath, listEssentialData);
			logger.info("Essential file path: " + essentialLocalPath + "\n Number iteration " + ++iterator);
			for (DataModel dataItem : listEssentialData) {
				((EssentialDataModel) dataItem).setType_id(stringClassificationId);
				createEssentialData((EssentialDataModel) dataItem);
			}
			listEssentialData.clear();
			clearBasicDBObject();

		}
	}

	private void createBasicDBObject(Map<?,?> next) {
		basicDBObject = new BasicDBObject();
		basicDBObject.putAll(next);
	}

	private void clearBasicDBObject() {
		basicDBObject.clear();

	}

	private String getEssentialLocalPath() {
		return basicDBObject.getString("file");
	}

	private String getStringClassificationId() {
		return basicDBObject.getObjectId("_id").toString();
	}

}
