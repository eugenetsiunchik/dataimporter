package by.eugene.importer.models;

import by.eugene.importer.models.interfaces.DataModel;

public class ClassificationDataModel implements DataModel {

	private String file;
	private String title;
	private String units;
	private String frequency;
	private String seasonalAdjustment;
	private String lastUpdate;
	private String _id;

	public ClassificationDataModel() {

	}
	
	public void set_id(String _id) {
		this._id = _id;
	}

	public String get_id() {
		return _id;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getSeasonalAdjustment() {
		return seasonalAdjustment;
	}

	public void setSeasonalAdjustment(String seasonalAdjustment) {
		this.seasonalAdjustment = seasonalAdjustment;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
