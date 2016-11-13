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
import wai.waiswebscraper.services.WebScraperService;

import org.jsoup.*;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

@RestController
public class ScraperController {

	@Autowired
	private WebScraperService webScraperService;
	
    @RequestMapping("/")
    @ResponseBody
    public String index() {
    	return null;
    }
    
    @RequestMapping("/result")
    @ResponseStatus(HttpStatus.OK)
    public Item result(@RequestParam(value="name", defaultValue="World") String name) {
        return new Item(null, null, null, null);
    }
    
    @RequestMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String test(@RequestParam(value="name", defaultValue="World") String name) {
    	return webScraperService.productListJson("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html");
    }
}
