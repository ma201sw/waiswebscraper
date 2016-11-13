package wai.waiswebscraper.domain;

import java.math.BigDecimal;


public class Item {
	private final String title;
	private final String size;
	private final BigDecimal unitPrice;
	private final String description;
	
	public Item(ItemBuilder builder) {
		this.title = builder.title;
		this.size = builder.size;
		this.unitPrice = builder.unitPrice;
		this.description = builder.description;
	}

	public String getTitle() {
		return title;
	}

	public String getSize() {
		return size;
	}
	
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	
	public String getDescription() {
		return description;
	}
	
	public static class ItemBuilder {
		private String title;
		private String size;
		private BigDecimal unitPrice;
		private String description;
		
		public ItemBuilder title(String title) {
			this.title = title;
			return this;
		}
		
		public ItemBuilder size(String size) {
			this.size = size;
			return this;
		}
		
		public ItemBuilder unitPrice(BigDecimal unitPrice) {
			this.unitPrice = unitPrice;
			return this;
		}
		
		public ItemBuilder description(String description) {
			this.description = description;
			return this;
		}
		
		public Item build() {
			return new Item(this);
		}
	}

}
