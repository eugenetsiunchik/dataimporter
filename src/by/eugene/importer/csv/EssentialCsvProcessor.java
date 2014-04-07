package by.eugene.importer.csv;

import java.util.Map;

import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

import by.eugene.importer.models.EssentialDataModel;
import by.eugene.importer.models.interfaces.DataModel;

public class EssentialCsvProcessor extends BaseCsvProcessor {

	private static final String DATE = "DATE";
	private static final String VALUE = "VALUE";
	
	@Override
	protected CellProcessor[] getProcessors() {
		final CellProcessor[] processors = new CellProcessor[] { 
				new NotNull(), // Date
				new NotNull(), // Value
		};

		return processors;
	}

	@Override
	protected DataModel createModel(Map<String, Object> map) {
		EssentialDataModel essentialDataModel = new EssentialDataModel();
		essentialDataModel.setDate(map.get(DATE).toString());
		essentialDataModel.setValue(map.get(VALUE).toString());
		return essentialDataModel;
	}

}
