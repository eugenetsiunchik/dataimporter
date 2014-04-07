package by.eugene.importer.csv;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import by.eugene.importer.models.interfaces.DataModel;

public abstract class BaseCsvProcessor {

	private final static Logger logger = LoggerFactory.getLogger(BaseCsvProcessor.class);

	/**
	 * Create cell processor for write/read operations
	 * 
	 * @return
	 */
	protected abstract CellProcessor[] getProcessors();

	/**
	 * Create model by type
	 * 
	 * @param map
	 * @return
	 */
	protected abstract DataModel createModel(Map<String, Object> map);

	/**
	 * Reads data from file and writes to List
	 * 
	 * @param source
	 * @param listData
	 * @throws IOException
	 */
	public void saveDataModelInList(String source, List<DataModel> listData) throws IOException {
		ICsvMapReader mapReader = null;
		try {
			mapReader = new CsvMapReader(new FileReader(source), CsvPreference.STANDARD_PREFERENCE);

			final String[] header = mapReader.getHeader(true);
			Map<String, Object> customerMap;
			logger.info("Start read csv file");
			while ((customerMap = mapReader.read(header, getProcessors())) != null) {
				logger.debug(String.format("lineNo=%s, rowNo=%s, customerMap=%s", mapReader.getLineNumber(),
						mapReader.getRowNumber(), customerMap));
				listData.add(createModel(customerMap));

			}
		} catch (Exception e) {
			logger.error(e.toString());
		}

		finally {
			if (mapReader != null) {
				mapReader.close();
				logger.info("Done read csv file");
			}
		}
	}

	/**
	 * Write csv data in file from list
	 * 
	 * @param target
	 *            path to save file with data
	 * @param csvLinesList
	 *            list comprising list with object have type Object
	 * @throws Exception
	 */
	public void writeCsvInFile(String target, List<List<Object>> csvLinesList) throws Exception {

		ICsvListWriter listWriter = null;
		try {
			listWriter = new CsvListWriter(new FileWriter(target), CsvPreference.STANDARD_PREFERENCE);
			logger.info("Start write classification csv file");
			for (List<Object> item : csvLinesList) {
				listWriter.write(item, getProcessors());
			}
			logger.info("Done write classification file and save to " + target);
		} catch (Exception e) {
			logger.error(e.toString());
		} finally {
			if (listWriter != null) {
				listWriter.close();
			}
		}
	}

}
