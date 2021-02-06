package biz.uoray.api.form;

import javax.validation.constraints.Size;

public class CarDetailForm {

	private int carId;

	private int storeId;

	@Size(max = 64)
	private String color;

	private double distance;

	@Size(max = 8)
	private String transmission;

	@Size(max = 8)
	private String modelYear;

	@Size(max = 255)
	private String url;

	@Size(max = 64)
	private String note;

	public int getCarId() {
		return carId;
	}

	public void setCarId(int car_id) {
		this.carId = car_id;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int store_id) {
		this.storeId = store_id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getModelYear() {
		return modelYear;
	}

	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}



}
