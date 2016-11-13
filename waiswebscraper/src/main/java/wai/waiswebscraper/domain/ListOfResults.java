package wai.waiswebscraper.domain;

import java.math.BigDecimal;
import java.util.List;


public class ListOfResults {
	private final List<Item> results;
	private final BigDecimal total;
	
	public ListOfResults(ListOfResultsBuilder builder) {
		this.results = builder.results;
		this.total = builder.total;
	}
	
	public List<Item> getResults() {
		return results;
	}
	
	
	public BigDecimal getTotal() {
		return total;
	}
	
	
	public static class ListOfResultsBuilder {
		private List<Item> results;
		private BigDecimal total;
		
		public ListOfResultsBuilder results(List<Item> results) {
			this.results = results;
			return this;
		}
		
		public ListOfResultsBuilder total() {
			BigDecimal calculateTotal = new BigDecimal(0);
			for(Item item: results) {
				calculateTotal = calculateTotal.add(item.getUnitPrice());
			}
			total = calculateTotal;
			return this;
		}
		
		public ListOfResults build() {
			return new ListOfResults(this);
		}
	}
	
}
