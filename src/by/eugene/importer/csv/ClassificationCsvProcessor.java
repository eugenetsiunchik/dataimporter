package by.eugene.importer.csv;

import java.util.Map;

import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

import by.eugene.importer.models.ClassificationDataModel;
import by.eugene.importer.models.interfaces.DataModel;

public class ClassificationCsvProcessor extends BaseCsvProcessor {
	
	private static final String FILE = "File";
	private static final String TITLE = "Title";
	private static final String UNITS = "Units";
	private static final String FREQUENCY = "Frequency";
	private static final String SESONAL_ADJUSTMENT = "Seasonal Adjustment";
	private static final String LAST_UPDATE = "Last Updated";


	protected CellProcessor[] getProcessors() {

		final CellProcessor[] processors = new CellProcessor[] { 
				new NotNull(), // File
				new NotNull(), // Title
				new NotNull(), // Units
				new NotNull(), // Frequency
				new NotNull(), // Seasonal Adjustment
				new NotNull() // Last Update

		};

		return processors;
	}


	@Override
	protected DataModel createModel(Map<String, Object> customerMap) {
		ClassificationDataModel classificationDataModel = new ClassificationDataModel();
		classificationDataModel.setFile(customerMap.get(FILE).toString());
		classificationDataModel.setTitle(customerMap.get(TITLE).toString());
		classificationDataModel.setUnits(customerMap.get(UNITS).toString());
		classificationDataModel.setFrequency(customerMap.get(FREQUENCY).toString());
		classificationDataModel.setSeasonalAdjustment(customerMap.get(SESONAL_ADJUSTMENT).toString());
		classificationDataModel.setLastUpdate(customerMap.get(LAST_UPDATE).toString());
		return classificationDataModel;
	}

}
