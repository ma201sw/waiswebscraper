package wai.waiswebscraper.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import wai.waiswebscraper.domain.Item;

import org.jsoup.*;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

@RestController
public class ScraperController {

	
    @RequestMapping("/")
    @ResponseBody
    public String index() {
    	JSONObject obj = new JSONObject();
		obj.put("success", false);
	    obj.put("message", "PEBKACb");
		
		return obj.toString();
		
//		deserialization
//		org.json.JSONObject obj = new org.json.JSONObject(responseAsString);  
//		obj.optBoolean("success"); // false
//		obj.optString("message"); // bb
		
        //return "Greetings from Spring Boot!";
    	//return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for UUID: " + uuid).build();

    	//return Response.ok("test", MediaType.TEXT_PLAIN_VALUE).build();
    }
    
    @RequestMapping("/result")
    @ResponseStatus(HttpStatus.OK)
    public Item result(@RequestParam(value="name", defaultValue="World") String name) {
        return new Item();
    }
    
    @RequestMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String test(@RequestParam(value="name", defaultValue="World") String name) {
    	Document doc = null;
    	String result = "";
		try {
			String url = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
			String json = Jsoup.connect(url).ignoreContentType(true).execute().body();
			doc = Jsoup.connect(url).get();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		Elements products = doc.select("div.productInfo");

		for (Element link : products) {
			Element firstLink = link.select("a[href]").first();
			result += System.lineSeparator();
			result += firstLink.attr("href");
			result += System.lineSeparator();
			result += firstLink.text(); // a with href
			result += System.lineSeparator();
			
			String url = firstLink.attr("href");
			try {
				Connection con= Jsoup.connect(url);
				Document doc1 = con.get();
				Connection.Response response = con.response();
				Double size = (double) (response.bodyAsBytes().length/1000.0);
				Element t = doc1.select("div.productText").first();
				result += t.select("p").first().text();
				
				result += System.lineSeparator();
				result += System.lineSeparator();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
    }
}
