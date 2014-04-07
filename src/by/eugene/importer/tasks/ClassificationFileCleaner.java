package by.eugene.importer.tasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.eugene.importer.csv.ClassificationCsvProcessor;

public class ClassificationFileCleaner {

	private static final Logger logger = LoggerFactory.getLogger(ClassificationFileCleaner.class);
	private static final String separator = ";";
	private static final int parameter = 6;

	private static List<List<Object>> csvLinesList;

	/**
	 * Clean and save classification file on file system
	 * 
	 * @param source
	 * @param target
	 * @throws Exception
	 */
	public static void cleanAndSaveClassificationCsvFile(String source, String target) throws Exception {

		csvLinesList = new ArrayList<>();
		ClassificationCsvProcessor classificationProcessor = new ClassificationCsvProcessor();
		logger.info("Start clean preclassification file");

		try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
			while (true) {
				String line = reader.readLine();

				if (line == null) {
					break;
				}

				String[] stringFields = line.split(separator);
				List<Object> listItemsRecord = new ArrayList<>();

				if (!isValidate(stringFields)) {
					continue;
				}
				writeCleanFields(listItemsRecord, stringFields);
				csvLinesList.add(listItemsRecord);
			}
			classificationProcessor.writeCsvInFile(target, csvLinesList);
			logger.info("Done clean preclassification file");

		}
	}

	/**
	 * Trim whitespace and save in List separated fields
	 * 
	 * @param listItemsRecord
	 * @param stringFields
	 */
	private static void writeCleanFields(List<Object> listItemsRecord, String[] stringFields) {

		for (int i = 0; i < stringFields.length; i++) {
			logger.debug(stringFields[i] + " ");
			listItemsRecord.add(stringFields[i].trim());
		}

	}

	/**
	 * Check line in file and validate the parameter
	 * 
	 * @param fields
	 * @return
	 */
	private static boolean isValidate(String[] fields) {
		return fields.length == parameter;
	}
}
