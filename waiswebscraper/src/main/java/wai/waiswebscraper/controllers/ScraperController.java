package wai.waiswebscraper.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import wai.waiswebscraper.domain.Item;
import wai.waiswebscraper.domain.ListOfResults;

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
        return new Item(null, null, null, null);
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

		List<Item> results = new ArrayList<Item>();
		for (Element link : products) {
			String title = null;
			String size = null;
			BigDecimal unitPrice = null;
			String description = null;
			
			
			Element firstLink = link.select("a[href]").first();
			result += System.lineSeparator();
			
			result += firstLink.attr("href");
			result += System.lineSeparator();
			
			title = firstLink.text(); 
			result += title; 
			result += System.lineSeparator();
			
			
			
			String url = firstLink.attr("href");
			try {
				Connection con= Jsoup.connect(url);
				Document doc1 = con.get();
				Connection.Response response = con.response();
				size = Double.toString(response.bodyAsBytes().length/1000.0) + "kb";
				
				description = doc1.select("div.productText").first().text();
				
				Element t = doc1.select("div.productText").first();
				result += t.select("p").first().text();
				result += System.lineSeparator();
				String price = doc1.select("p.pricePerUnit").first().text();
				
				unitPrice = new BigDecimal(price.substring(price.indexOf("£")+1, price.indexOf(("/unit"))));
				
				result += price.substring(price.indexOf("£")+1, price.indexOf(("/unit")));
				
				result += System.lineSeparator();
				result += System.lineSeparator();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Item item = new Item(title, size, unitPrice, description);
			results.add(item);
			

		}
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			ListOfResults listOfResults = new ListOfResults(results);
			listOfResults.setTotal();
			jsonString = mapper.writeValueAsString(listOfResults);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
    }
}
