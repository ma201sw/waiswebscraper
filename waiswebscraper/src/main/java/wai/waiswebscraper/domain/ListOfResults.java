package wai.waiswebscraper.domain;

import java.math.BigDecimal;
import java.util.List;

public class ListOfResults {
	List<Item> results;
	BigDecimal total;
	
	public ListOfResults(List<Item> results) {
		this.results = results;
	}
	
	public List<Item> getResults() {
		return results;
	}
	
	public void setResults(List<Item> results) {
		this.results = results;
	}
	
	public BigDecimal getTotal() {
		return total;
	}
	
	public void setTotal() {
		BigDecimal calculateTotal = new BigDecimal(0);
		for(Item item: results) {
			calculateTotal = calculateTotal.add(item.getUnitPrice());
		}
		total = calculateTotal;
	}
	
}
