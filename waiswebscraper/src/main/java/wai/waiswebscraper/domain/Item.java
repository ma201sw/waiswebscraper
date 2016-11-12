package wai.waiswebscraper.domain;

import java.math.BigDecimal;

public class Item {
	private String title;
	private Double size;
	private BigDecimal unitPrice;
	private String description;

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
