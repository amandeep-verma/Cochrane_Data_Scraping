package com.vantage.Cochrane;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper {

	static String baseUrl = "https://www.cochranelibrary.com/";
	static String topic="";
	static FileWriter myWriter;
	
	
	public static void fileCreate()
	{
		try {
			File myObj = new File("cochrane_reviews.txt");
			myObj.createNewFile();
			myWriter = new FileWriter("cochrane_reviews.txt");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<List<String>> getTopics()
	{
		String url = "https://www.cochranelibrary.com/cdsr/reviews/topics";

		List<List<String>> topicAndUrl = new ArrayList<>();
		
		try {
			Document document = Jsoup.connect(url).get();

			for (Element list : document.select("li.browse-by-list-item")) {
				
				List<String> topic = Arrays.asList(list.select("button.btn-link.browse-by-list-item-link").text(),
						list.select("a").attr("href"));
				topicAndUrl.add(topic);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return topicAndUrl;
	}

	public static void getNextPages(String url,Connection.Response response) throws IOException
	{
		Document document = Jsoup.connect(url).cookies(response.cookies()).get();
		
		for(Element el : document.select("div.search-results-item-body"))
		{
			String link = el.select("a").attr("href");
			String title = el.select("h3.result-title").text();
			String author = el.select("div.search-result-authors").text();
			String date =  el.select("div.search-result-date").text();
//			System.out.println(baseUrl+link+"|"+topic+"|"+title+"|"+author+"|"+date);
			myWriter.write(baseUrl+link+"|"+topic+"|"+title+"|"+author+"|"+date+"\n");
			myWriter.flush();
			
		}
		
		Elements e = document.select("div.pagination-next-link");
		String nextPage=null;
		
		for (Element list : e)
			nextPage = list.select("a").attr("href");

		if(nextPage.length()==0)
			return;

		getNextPages(nextPage,response);
	}


	public static void getFirstPage(String url)
	{
		try {
			Connection.Response response = Jsoup.connect(url).method(Connection.Method.GET).execute();

			Document document = Jsoup.connect(url).cookies(response.cookies()).get();

			topic = document.select("span.facet-pill.secondary").text();
			
			String firstPageUrl = "";

			for(Element el : document.select("li.pagination-page-list-item.active"))
				firstPageUrl = el.select("a").attr("href");

			if(firstPageUrl.length()==0)
				getNextPages(url,response);
			else
				getNextPages(firstPageUrl,response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
