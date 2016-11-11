package wai.waiswebscraper;


import org.jsoup.*;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.*; // Only needed if scraping a local File.

public class Scraper {


	public Scraper() {

		Document doc = null;

		try {
			String url = "http://www.geog.leeds.ac.uk/courses/other/programming/practicals/general/web/scraping-intro/table.html";
			String json = Jsoup.connect(url).ignoreContentType(true).execute().body();
			doc = Jsoup.connect("http://www.geog.leeds.ac.uk/courses/other/programming/practicals/general/web/scraping-intro/table.html").get();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		Element table = doc.getElementById("datatable");
		Elements rows = table.getElementsByTag("TR");
		
		for (Element row : rows) {
			Elements tds = row.getElementsByTag("TD");
			for (int i = 0; i < tds.size(); i++) {
				if (i == 1) System.out.println(tds.get(i).text());
			}
		}
	
	}
	
	public static void main (String args[]) {

		new Scraper();
	
	}
	
}
	