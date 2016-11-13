package wai.waiswebscraper.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import wai.waiswebscraper.domain.Item;
import wai.waiswebscraper.domain.ListOfResults;

@Service
public class WebScraperService {

	@PostConstruct
	public void init() {
		
	}
	
	public String productListJson(String url) {
		Document doc = null;
		try {
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
			title = firstLink.text(); 

			String innerLink = firstLink.attr("href");
			try {
				Connection con= Jsoup.connect(innerLink);
				Document innerDoc = con.get();
				Connection.Response response = con.response();
				size = Double.toString(response.bodyAsBytes().length/1000.0) + "kb";
				
				description = innerDoc.select("div.productText").first().text();
				String price = innerDoc.select("p.pricePerUnit").first().text();
				unitPrice = new BigDecimal(price.substring(price.indexOf("£")+1, price.indexOf(("/unit"))));


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
