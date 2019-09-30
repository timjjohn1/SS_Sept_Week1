/**
 * 
 */
package com.ss.rmdbs.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ss.rmdbs.objs.Publisher;

/**
 * @author tj
 * PublisherDAO is the data access object class
 * Interacts with the Publisher.csv file to read/write pojos to it
 */
public class PublisherDAO {

	public PublisherDAO() {

    	publisherFileCheck();

    	setPublishers(readPublishers());
	}
	
	
    private List<Publisher> publishers;

	/**
	 * @return the publishers
	 */
	public List<Publisher> getPublishers() {
		return publishers;
	}

	/**
	 * @param publishers the publishers to set
	 */
	public void setPublishers(List<Publisher> publishers) {
		this.publishers = publishers;
	}
	
	//Reading the publishers from the file
	//Returns the list of publishers found
    public List<Publisher> readPublishers(){
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
	    	inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return publisherList;
    }
    

	//Takes in a list of books and creates a string of them in CSV format
    public StringBuilder convertPublishersCSV(List<Publisher> publisherList) {
    	StringBuilder csvString = new StringBuilder();
    	
    	if(publisherList != null) {
    		for(Publisher publisher : publisherList) {
    			csvString.append(publisher.getID() + "," + publisher.getName() + "," + publisher.getAddress() + "\n");
    		}
    	}
    	return csvString;
    }

	//Checks to see if the file exists and if it does it won't act
	//If file DNE then it will created it with the fields as a header
	public boolean publisherFileCheck(){
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
		         return false;
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
		return true;
	}
	
	//Writes to the file according to an updated publisherList
	public void resetPublishers(List<Publisher> publisherList){
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
	
	//Functions to write a list of publishers to the file
	public void writePublisher(Publisher publisher){
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
