package wai.waiswebscraper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import wai.waiswebscraper.services.ProductListService;

@RestController
public class ScraperController {

	@Autowired
	private ProductListService productListService;
	
    @RequestMapping("/")
    @ResponseBody
    public String index() {
    	return null;
    }
    
    @RequestMapping("/result")
    @ResponseStatus(HttpStatus.OK)
    public String test(@RequestParam(value="name", defaultValue="World") String name) {
    	try {
    		return productListService.productListJson("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
}
