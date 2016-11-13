package wai.waiswebscraper.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @author Wai
 *
 */
public class ResultRepresentation {
	private final List<Item> results;
	private final BigDecimal total;
	
	public ResultRepresentation(ResultRepresentationBuilder builder) {
		this.results = builder.results;
		this.total = builder.total;
	}
	
	public List<Item> getResults() {
		return results;
	}
	
	
	public BigDecimal getTotal() {
		return total;
	}
	
	
	public static class ResultRepresentationBuilder {
		private List<Item> results;
		private BigDecimal total;
		
		public ResultRepresentationBuilder results(List<Item> results) {
			this.results = results;
			return this;
		}
		
		public ResultRepresentationBuilder total() {
			BigDecimal calculateTotal = new BigDecimal(0);
			for(Item item: results) {
				calculateTotal = calculateTotal.add(item.getUnitPrice());
			}
			total = calculateTotal;
			return this;
		}
		
		public ResultRepresentation build() {
			return new ResultRepresentation(this);
		}
	}
	
}
