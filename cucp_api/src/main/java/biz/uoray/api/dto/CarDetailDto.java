package biz.uoray.api.dto;

import biz.uoray.api.entity.CarDetail;
import biz.uoray.api.entity.Car;
import biz.uoray.api.entity.Price;
import biz.uoray.api.entity.Store;

import java.util.Map;

public class CarDetailDto {

    private int id;

    private Car car;

    private Store store;

    private String color;

    private double distance;

    private String transmission;

    private String modelYear;

    private String url;

    private String note;

    private Map<String, Price> priceList;

    public CarDetailDto(CarDetail carDetail, Map<String, Price> priceList) {
        this.id = carDetail.getId();
        this.car = carDetail.getCar();
        this.store = carDetail.getStore();
        this.color = carDetail.getColor();
        this.distance = carDetail.getDistance();
        this.transmission = carDetail.getTransmission();
        this.modelYear = carDetail.getModelYear();
        this.url = carDetail.getUrl();
        this.note = carDetail.getNote();
        this.priceList = priceList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCarEntity() {
        return car;
    }

    public void setCarEntity(Car car) {
        this.car = car;
    }

    public Store getStoreEntity() {
        return store;
    }

    public void setStoreEntity(Store store) {
        this.store = store;
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

    public Map<String, Price> getPriceList() {
        return priceList;
    }

    public void setPriceList(Map<String, Price> priceList) {
        this.priceList = priceList;
    }

}
