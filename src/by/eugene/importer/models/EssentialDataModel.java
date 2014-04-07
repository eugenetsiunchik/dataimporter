package by.eugene.importer.models;

import by.eugene.importer.models.interfaces.DataModel;

public class EssentialDataModel implements DataModel {
	private String date;
	private String value;
	private String type_id;
	

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String id) {
		this.type_id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
