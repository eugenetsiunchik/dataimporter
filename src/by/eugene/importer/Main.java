package by.eugene.importer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.eugene.importer.csv.ClassificationCsvProcessor;
import by.eugene.importer.database.dao.ClassificationDAO;
import by.eugene.importer.database.dao.DAOFactory;
import by.eugene.importer.database.dao.EssentialDAO;
import by.eugene.importer.models.ClassificationDataModel;
import by.eugene.importer.models.interfaces.DataModel;
import by.eugene.importer.tasks.ClassificationFileCleaner;
import by.eugene.importer.util.PropertiesConstant;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	private static String tempPath;
	private static String database;
	private static String preClassificationFile;
	private static String zipFilePath;
	private static String urlFile;
	private static String classificationFile;
	private static String essentialData;

	public static void main(String[] args) {

		initProperties();

		try {

			// Downloader.download(urlFile, tempPath);

			// UnZip.unzip(zipFilePath, tempPath);

			String classificationFilePath = tempPath + classificationFile;
			String preClassificationFilePath = tempPath + preClassificationFile;
			//ClassificationFileCleaner.cleanAndSaveClassificationCsvFile(preClassificationFilePath, classificationFilePath);
			//List<DataModel> listClassificationData = new ArrayList<>();

			//new ClassificationCsvProcessor().saveDataModelInList(classificationFilePath, listClassificationData);

			ClassificationDAO classificationDAO = DAOFactory.getInstance().getClassificationDAOMongoImpl(database);
			EssentialDAO essentialDAO = DAOFactory.getInstance().getEssentialDAOMongoImpl(database);

			//classificationDAO.deleteClassificationDataAll();
			essentialDAO.deleteEssentialDataAll();

			//insertClassificationData(classificationDAO, listClassificationData);

			insertEssentialData(classificationDAO, essentialDAO);

		} catch (Exception e) {
			logger.error(e.toString());
		}

	}

	/**
	 * Insert essential data to database
	 * 
	 * @param classificationDAO
	 * @param essentialDAO
	 * @throws IOException
	 */
	private static void insertEssentialData(ClassificationDAO classificationDAO, EssentialDAO essentialDAO)
			throws IOException {
		Iterator<?> cursor = classificationDAO.getClassificationDataAll();
		
		String tempPathEssentialData = tempPath + essentialData;
		essentialDAO.createEssentialDataAll(cursor, tempPathEssentialData );
		
	}

	/**
	 * Insert classification data to database
	 * 
	 * @param classificationDAO
	 * @param listClassificationData
	 * @throws UnknownHostException
	 */
	private static void insertClassificationData(ClassificationDAO classificationDAO,
			List<DataModel> listClassificationData) throws UnknownHostException {
		logger.info("Start insert classification data");
		for (DataModel classificationDataModel : listClassificationData) {
			classificationDAO.createClassificationData((ClassificationDataModel) classificationDataModel);
		}
		logger.info("Done insert classification data");
	}

	/**
	 * Initialization properties values
	 */
	private static void initProperties() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(new File(PropertiesConstant.PATH_PROPERTY)));
			tempPath = props.getProperty(PropertiesConstant.TEMP_PROPERTY);
			database = props.getProperty(PropertiesConstant.DATABASE_PROPERTY);
			preClassificationFile = props.getProperty(PropertiesConstant.PRE_CLASSIFICATION_FILE_PROPERTY);
			zipFilePath = props.getProperty(PropertiesConstant.ZIP_FILE_PROPERTY);
			urlFile = props.getProperty(PropertiesConstant.URL_FILE_PROPERTY);
			classificationFile = props.getProperty(PropertiesConstant.CLASSIFICATION_FILE_PROPERTY);
			essentialData = props.getProperty(PropertiesConstant.ESSENTIAL_DATA);

		} catch (FileNotFoundException e) {
			logger.error(e.toString());
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
}
