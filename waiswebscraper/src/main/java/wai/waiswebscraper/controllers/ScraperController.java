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
    		return productListService.productListJson("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html");
		//return productListService.productListJson("http://www.sainsburys.co.uk/shop/gb/groceries/fruit-veg/ripe---ready#langId=44&storeId=10151&catalogId=10241&categoryId=185749&parent_category_rn=12518&top_category=12518&pageSize=20&orderBy=FAVOURITES_ONLY%7CTOP_SELLERS&searchTerm=&beginIndex=0");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
}
