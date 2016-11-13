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
import wai.waiswebscraper.domain.ResultRepresentation;

/**
 * 
 * @author Wai
 *
 */

@Service
public class ProductListService {

	@PostConstruct
	public void init() {
		
	}
	
	/**
	 * Processes the list of products and returns json string
	 * @param url
	 * @return
	 */
	public String productListJson(String url) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
			
			//selects the list of products
			Elements products = doc.select("div.productInfo");
			List<Item> results = new ArrayList<Item>();
			
		for (Element product : products) {
			//processes each product and adds it to results
			results.add(processProduct(product));
		}
		
		//build results
		ResultRepresentation resultrepresentation = new ResultRepresentation.ResultRepresentationBuilder()
				.results(results)
				.calculateTotal()
				.build();
		
		//return ResultRepresentation type as json
		return processJson(resultrepresentation);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} 
		
		return null;
	}
	
	/**
	 * Processes reference type ResultRepresentation to json and returns the json string
	 * @param resultRepresentation
	 * @return
	 */
	private String processJson(ResultRepresentation resultRepresentation) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(resultRepresentation);
			return jsonString;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * processes product and returns the Item
	 * @param product
	 * @return
	 * 
	 */
	private Item processProduct(Element product) {
		
		Element productLinkTag = product.select("a[href]").first();
		String title = product.select("a[href]").first().text(); 
		String productLink = productLinkTag.attr("href");
		
		return processProductLink(productLink, title);
		
	}
	
	/**
	 * processes inner product link
	 * @param productLink
	 * @return
	 */
	private Item processProductLink(String productLink, String title) {
		try {
			//connect to the product link
			Connection con= Jsoup.connect(productLink);
			Document innerDoc = con.get();
			Connection.Response response = con.response();
			String size = Double.toString(response.bodyAsBytes().length/1000.0) + "kb";
			String uncleanPrice = innerDoc.select("p.pricePerUnit").first().text();
			
			//just get the number
			BigDecimal unitPrice = new BigDecimal(uncleanPrice.substring(uncleanPrice.indexOf("£")+1, uncleanPrice.indexOf(("/unit"))));
			String description = innerDoc.select("div.productText").first().text();
			
			//build item
			Item item = new Item.ItemBuilder()
					.title(title)
					.size(size)
					.unitPrice(unitPrice)
					.description(description)
					.build();
			
			return item;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
