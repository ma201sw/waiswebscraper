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
		return productListService.productListJson("http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/SearchDisplayView?msg=&catalogId=10241&langId=44&storeId=10151&krypto=dQB09jgAXQmHsl4zSwNGnMmQGyoU%2F46xP3bcL0cNR5ldiBVRsYCvGj36%2FsA%2BG%2FhD56XmbXTrSeLD3uQ2%2FcRZshMuuXazlJmjLAzmWyFcUxlp1Y9ussSSqvBcAfeccDBoZBobzawOrXY%2Fn8%2BkDzfepIjUqp9ZsqXCy4uwuvbSxxjiRNhLDUAAwbMWP2FQK8%2B%2Ba%2B1vinUTge1o%2FgE44m6gQw%3D%3D#langId=44&storeId=10151&catalogId=10241&categoryId=&parent_category_rn=&top_category=&pageSize=30&orderBy=RELEVANCE&searchTerm=chocolate&beginIndex=0&categoryFacetId1=");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
}
