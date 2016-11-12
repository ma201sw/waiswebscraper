package wai.waiswebscraper.domain;

import java.math.BigDecimal;

public class Item {
	private String title;
	private String size;
	private BigDecimal unitPrice;
	private String description;
	
	public Item(String title, String size, BigDecimal unitPrice, String description) {
		this.title = title;
		this.size = size;
		this.unitPrice = unitPrice;
		this.description = description;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	public String getSize() {
		return size;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

}
