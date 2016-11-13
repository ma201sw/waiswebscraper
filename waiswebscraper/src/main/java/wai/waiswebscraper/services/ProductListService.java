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
public class ProductListService {

	@PostConstruct
	public void init() {
		
	}
	
	public String productListJson(String url) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
			Elements products = doc.select("div.productInfo");
			List<Item> results = new ArrayList<Item>();
			
		for (Element product : products) {
			results.add(processProduct(product));
		}
		
		ListOfResults listOfResults = new ListOfResults.ListOfResultsBuilder()
				.results(results)
				.total()
				.build();
		
		return convertToJson(listOfResults);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} 
		
		return null;
	}
	
	public String convertToJson(ListOfResults listOfResults) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(listOfResults);
			return jsonString;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * processes product and returns item
	 * @param product
	 * @return
	 * 
	 */
	private Item processProduct(Element product) {
		
		Element productLinkTag = product.select("a[href]").first();
		String title = product.select("a[href]").first().text(); 
		String productLink = productLinkTag.attr("href");
		
		Connection con= Jsoup.connect(productLink);
		Document innerDoc;
		try {
			innerDoc = con.get();
			Connection.Response response = con.response();
			String size = Double.toString(response.bodyAsBytes().length/1000.0) + "kb";
			String uncleanPrice = innerDoc.select("p.pricePerUnit").first().text();
			BigDecimal unitPrice = new BigDecimal(uncleanPrice.substring(uncleanPrice.indexOf("£")+1, uncleanPrice.indexOf(("/unit"))));
			String description = innerDoc.select("div.productText").first().text();
			Item item = new Item.ItemBuilder()
					.title(title)
					.size(size)
					.unitPrice(unitPrice)
					.description(description)
					.build();
			
			return item;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
