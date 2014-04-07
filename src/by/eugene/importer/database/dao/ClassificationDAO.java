package by.eugene.importer.database.dao;

import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

import by.eugene.importer.models.ClassificationDataModel;

public interface ClassificationDAO {
	/**
	 * Create one record classification data
	 * 
	 * @param model
	 * @throws UnknownHostException
	 */
	public void createClassificationData(ClassificationDataModel model) throws UnknownHostException;

	/**
	 * Create all records classification data by list
	 * 
	 * @param list
	 * @throws UnknownHostException
	 */
	public void createClassificationDataAll(List<ClassificationDataModel> list) throws UnknownHostException;

	/**
	 * Delete all record on table
	 * 
	 * @throws UnknownHostException
	 */
	public void deleteClassificationDataAll() throws UnknownHostException;

	/**
	 * Get classification data in Array format
	 * 
	 * @return
	 */
	public Iterator<?> getClassificationDataAll();

}
