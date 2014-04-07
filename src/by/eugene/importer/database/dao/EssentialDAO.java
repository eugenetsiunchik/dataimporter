package by.eugene.importer.database.dao;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import by.eugene.importer.models.EssentialDataModel;

public interface EssentialDAO {
	/**
	 * Create one record with essential data
	 * 
	 * @param model
	 */
	public void createEssentialData(EssentialDataModel model);

	/**
	 * Create all records essential data from List
	 * 
	 * @param list
	 */
	public void createEssentialDataAll(List<EssentialDataModel> list);
	
	public void createEssentialDataAll (Iterator<?> cursor, String tempPathEssentialData) throws IOException;

	/**
	 * Delete all records in table
	 */
	public void deleteEssentialDataAll();

}
