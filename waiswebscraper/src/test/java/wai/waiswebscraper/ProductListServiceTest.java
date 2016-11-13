package wai.waiswebscraper;

import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import junit.framework.TestCase;
import wai.waiswebscraper.services.ProductListService;

/**
 * 
 * @author Wai
 *
 */


public class ProductListServiceTest extends TestCase{
	
	public void testProductListJsonReturnsCorrectJson() {
		
		ProductListService productListService = new ProductListService();
		
		String url = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
		String jsonString = productListService.productListJson(url);
		
		assertEquals(jsonString, 
				"{\"results\":[{\"title\":\"Sainsbury's Apricot Ripe & Ready x5\",\"size\":\"39.185kb\",\"unitPrice\":3.50,\"description\":\"Apricots\"},{\"title\":\"Sainsbury's Avocado Ripe & Ready XL Loose 300g\",\"size\":\"39.597kb\",\"unitPrice\":1.50,\"description\":\"Avocados\"},{\"title\":\"Sainsbury's Avocado, Ripe & Ready x2\",\"size\":\"44.479kb\",\"unitPrice\":1.80,\"description\":\"Avocados\"},{\"title\":\"Sainsbury's Avocados, Ripe & Ready x4\",\"size\":\"39.61kb\",\"unitPrice\":3.20,\"description\":\"Avocados\"},{\"title\":\"Sainsbury's Conference Pears, Ripe & Ready x4 (minimum)\",\"size\":\"39.465kb\",\"unitPrice\":1.50,\"description\":\"Conference\"},{\"title\":\"Sainsbury's Golden Kiwi x4\",\"size\":\"39.485kb\",\"unitPrice\":1.80,\"description\":\"Gold Kiwi\"},{\"title\":\"Sainsbury's Kiwi Fruit, Ripe & Ready x4\",\"size\":\"39.911kb\",\"unitPrice\":1.80,\"description\":\"Kiwi\"}],\"total\":15.10}");
	
	}
	
	public void testProcessJsonReturnsCorrectJson() {
		
		ProductListService productListService = new ProductListService();
		
		
	}
	
	
	public void testProcessProductReturnsCorrectItem() {
		

	}
	
	
}
