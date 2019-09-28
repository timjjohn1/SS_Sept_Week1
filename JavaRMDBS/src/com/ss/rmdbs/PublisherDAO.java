/**
 * 
 */
package com.ss.rmdbs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author sj
 *
 */
public class PublisherDAO {

	public PublisherDAO() {

    	publisherFileCheck();

    	setPublishers(readPublishers());
	}
	
	
    private static List<Publisher> publishers;

	/**
	 * @return the publishers
	 */
	public static List<Publisher> getPublishers() {
		return publishers;
	}

	/**
	 * @param publishers the publishers to set
	 */
	public static void setPublishers(List<Publisher> publishers) {
		PublisherDAO.publishers = publishers;
	}
	
	   
    public static List<Publisher> readPublishers(){
    	List<Publisher> publisherList = new ArrayList<Publisher>();
    	
		File file = new File("Publishers.csv");

//		String[][] publisherTable = new String[getNumberOfRecords("publishers.csv")][];
		
		
		int id;
		String name;
		String address;
		
		Scanner inputStream;
		
		try {
			inputStream = new Scanner(file);
			inputStream.nextLine();
			while (inputStream.hasNext()) {
				String line = inputStream.nextLine();
				// System.out.println(line);
				String[] values = line.split(",");
				try {
					id = Integer.parseInt(values[0]);
					name = values[1];
					address = values[2];

					Publisher publisher = new Publisher(id, name, address);
					
					publisherList.add(publisher);
					
				} catch (ArrayIndexOutOfBoundsException f) {
					// f.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("This is the publisher table: " + Arrays.deepToString(publisherTable));
    	
    	//printpublisherList();
		//System.out.println("contents of LIST to CSV");
    	//convertpublishersCSV();
    	
    	return publisherList;
    }
    
//    public static void printPublisherList() {
//    	if(publishers != null) {
//	    	for(Publisher publisher : publishers) {
//	    		System.out.print(publisher.getID());
//	    		System.out.println(publisher.getName());
//	    		System.out.println(publisher.getAddress());
//	    	}
//    	}
//    }
    public static StringBuilder convertPublishersCSV(List<Publisher> publisherList) {
    	StringBuilder csvString = new StringBuilder();
    	
    	if(publishers != null) {
    		for(Publisher publisher : publisherList) {
    			csvString.append(publisher.getID() + "," + publisher.getName() + "," + publisher.getAddress() + "\n");
    		}
    	}
    	return csvString;
    }

	public static void publisherFileCheck()
	{
		FileWriter fw = null;
		try {
			File file = new File("Publishers.csv");
	        boolean fvar = file.createNewFile();

			fw = new FileWriter("Publishers.csv", true);
		    if (fvar){
		         System.out.println("File has been created successfully.");
		    }
		    else{
		         System.out.println("File already present.");
		         fw.close();
		         return;
		    }
		}
		catch (IOException e)
		{
			System.out.println("Exception Occurred:");
	        e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		
		StringBuilder record = new StringBuilder();
		String fields = "P_id,P_name,P_address";
		record.append(fields +"\n");
		
		pw.write(record.toString());
		pw.close();
	}
	
	public void resetPublishers(List<Publisher> publisherList)
	{
		FileWriter fw = null;
		try {
			fw = new FileWriter("Publishers.csv", false);
			fw.flush();
		}
		catch (IOException e)
		{
			System.out.println("Exception Occurred:");
	        e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		String fields = "P_id,P_name,P_Address\n";
		
		pw.write(fields);
		pw.write(convertPublishersCSV(publisherList).toString());

			
		pw.close();
	}
	
	public void writePublisher(Publisher publisher)
	{
		FileWriter fw = null;
		try {
			fw = new FileWriter("Publishers.csv", true);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		
		StringBuilder record = new StringBuilder();
		record.append(publisher.getID() + ",");
		record.append(publisher.getName() + ",");
		record.append(publisher.getAddress());
		record.append("\n");
		
		pw.write(record.toString());
		pw.close();
		
		setPublishers(readPublishers());
		//publisherTable = updatePublisherArray();
		
		System.out.println("Publisher has been added to the database.");
	}
}
