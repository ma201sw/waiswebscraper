package wai.waiswebscraper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import wai.waiswebscraper.services.ProductListService;

/**
 * 
 * @author Wai
 *
 */
@RestController
public class ScraperController {

	@Autowired
	private ProductListService productListService;
	
    @RequestMapping("/")
    @ResponseBody
    public String index() {
    	return null;
    }
    
    /**
     * rest mapping of the result
     * @return
     */
    @RequestMapping(value = "/result", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String result() {
    	try {
    		//return json string of the products
    		//return productListService.productListJson("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html");
		return productListService.productListJson("http://www.sainsburys.co.uk/shop/CatalogSearchResultView?listView=true&orderBy=RELEVANCE&parent_category_rn=&top_category=&langId=44&beginIndex=0&pageSize=20&catalogId=10241&searchTerm=chocolate&categoryId=&listId=&storeId=10151&promotionId=#langId=44&storeId=10151&catalogId=10241&categoryId=&parent_category_rn=&top_category=&pageSize=20&orderBy=RELEVANCE&searchTerm=chocolate&beginIndex=0&categoryFacetId1=");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
}
