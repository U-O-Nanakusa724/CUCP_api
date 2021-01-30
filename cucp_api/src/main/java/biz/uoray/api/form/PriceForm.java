package biz.uoray.api.form;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PriceForm {

	private int priceId;

	private double price;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	public int getPriceId() {
		return priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
