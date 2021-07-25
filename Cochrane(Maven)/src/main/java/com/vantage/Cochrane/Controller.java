package com.vantage.Cochrane;
import java.util.List;
import java.util.Scanner;

public class Controller {

	public static void main(String[] args) {
		
		// Storing topics and their respective url
		List<List<String>> topicAndUrl  = Scraper.getTopics();
		
		
		System.out.println("<<================LIST OF TOPICS================>>");
		for (int i = 0; i < topicAndUrl.size(); i++) 
		{
			System.out.println((i+1)+ "\t"+ topicAndUrl.get(i).get(0));
		}
		
		System.out.println("================================================================================");
		System.out.println("Enter a number from 1 to "+ topicAndUrl.size() +" to extract for a specific topic.");
		System.out.println("Any other number will extract the data from all the topics");
		System.out.println("================================================================================");
		
		Scanner sc = new Scanner(System.in);
		int topicIndex = sc.nextInt();
		
		// Creates the output File
		Scraper.fileCreate();
		
		if(topicIndex>=1 && topicIndex<=topicAndUrl.size())
		{
			
			// Sends the url for specific topic and save it to the file
			System.out.println("Extracting for "+topicAndUrl.get(topicIndex-1).get(0));
			Scraper.getFirstPage(topicAndUrl.get(topicIndex-1).get(1));
		}
		else
		{
			//Sends the Url for each topic and maintains the session to hold cookies
			// Uncomment below for loop for extracting data from all the topics
			System.out.println("Extracting for All");
			for(List<String> li: topicAndUrl )
			{
				System.out.println("Extracting for "+li.get(0));
				Scraper.getFirstPage(li.get(1));
			}
			
		}
		
		System.out.println("Extraction done");
	}

}
