package wai.waiswebscraper;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import junit.framework.TestCase;
import wai.waiswebscraper.domain.Item;
import wai.waiswebscraper.domain.ResultRepresentation;

/**
 * 
 * @author Wai
 *
 */
public class ResultRepresentationTest extends TestCase {

	public void testCalculateTotalReturnsCorrectValue() {
		
		List<Item> results = new ArrayList<Item>();
		results.add(new Item.ItemBuilder().unitPrice(new BigDecimal("7.7")).build());
		results.add(new Item.ItemBuilder().unitPrice(new BigDecimal("8.8")).build());
		
		ResultRepresentation resultRepresentation = new ResultRepresentation.ResultRepresentationBuilder()
				.results(results)
				.calculateTotal()
				.build();
		
		BigDecimal total = resultRepresentation.getTotal();

		assertEquals(total, new BigDecimal("16.5"));
	}

	
}
