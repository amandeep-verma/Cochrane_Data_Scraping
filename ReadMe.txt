Cochrane Library Crawler (Executing Instructions)

The application aims at extracting the specific information from the Cochrane Library website.
The application is using Java8, Maven, jsoup (dependency). 



There are 2 folder in the present directory.
1. Cochrane(Jar) - contains the jar file
2. Cochrane(Maven) - contains the maven project

------------------------------------------------------------------------
To run the jar file 
Change directory to the folder Cochrane(Jar).
Run the following command on command line

	java -jar Cochrane-0.0.1-SNAPSHOT-jar-with-dependencies.jar 


------------------------------------------------------------------------
To run the mvn project from command line

Make Sure you have maven installed.
Change directory to the folder Cochrane(Maven).
Run the following command on command line

	mvn clean compile exec:java

------------------------------------------------------------------------

After executing, the program displays the list of the topics with number assigned to them.

The program asks the user for input to chose which topic you want to extract the data from.
If user chose a number from the list, the program extracts the data from that topic. 
A number out of the range of those displayed in the list will make the program to extract from 
all the topics.

The output is stored in the file cochrane_reviews.txt

------------------------------------------------------------------------
