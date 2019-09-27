/**
 * 
 */
package com.ss.rmdbs;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.Arrays;
//import java.util.Arrays;
import java.util.Scanner;

/**
 * @author sj
 * Will be used for the back-end(Reads/Writes) of Author, Book, Publisher
 */
public class Manager {

	
    private int author_id;
    private int book_id;
    private int publisher_id;
    private String[][] authorTable;
    private String[][] bookTable;
    private String[][] publisherTable;
    
    public Manager()
    {
    	author_id = 1;
    	book_id = 1;
    	publisher_id = 1;
    	authorFileCheck();
    	bookFileCheck();
    	publisherFileCheck();
    	authorTable = updateAuthorArray();
    	bookTable = updateBookArray();
    	publisherTable = updatePublisherArray();
    }
    
    public int getPublisherID()
    {
    	return this.publisher_id;
    }
    public int getBookID()
    {
    	return this.book_id;
    }
    public int getAuthorID()
    {
    	return this.author_id;
    }
    public String[][] getAuthorTable()
    {
    	authorTable = updateAuthorArray();
    	//System.out.println("We are in gAT: " + authorTable);
    	return this.authorTable;
    }
    public String[][] getBookTable()
    {
    	bookTable = updateBookArray();
    	return this.bookTable;
    }
    public String[][] getPublisherTable()
    {
    	publisherTable = updatePublisherArray();
    	return this.publisherTable;
    }
    
    public void authorFileCheck()
	{
		try {
			File file = new File("Authors.csv");
	        boolean fvar = file.createNewFile();
		    if (fvar){
		         System.out.println("File has been created successfully.");
		    }
		    else{
		         System.out.println("File already present.");
		         updateAuthorArray();
		         return;
		    }
		}
		catch (IOException e)
		{
			System.out.println("Exception Occurred:");
	        e.printStackTrace();
		}
		FileWriter fw = null;
		try {
			fw = new FileWriter("Authors.csv", true);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);

		StringBuilder record = new StringBuilder();
		String fields = "A_id,A_name";
		record.append(fields +"\n");
		
		pw.write(record.toString());
		pw.close();
	}
    
	public void bookFileCheck()
	{
		FileWriter fw = null;
		try {
			File file = new File("Books.csv");
	        boolean fvar = file.createNewFile();

			fw = new FileWriter("Books.csv", true);
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
		String fields = "B_id,B_name,B_author,B_publisher";
		record.append(fields +"\n");
		
		pw.write(record.toString());
		pw.close();
		
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
    
	public void resetAuthors(String[][] records)
	{
		FileWriter fw = null;
		try {
			fw = new FileWriter("Authors.csv", false);
			fw.flush();
		}
		catch (IOException e)
		{
			System.out.println("Exception Occurred:");
	        e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		StringBuilder record = new StringBuilder();
		String fields = "A_id,A_name";
		record.append(fields +"\n");
		
		pw.write(record.toString());
		for(String[] author : records) {
			StringBuilder record1 = new StringBuilder();
			record1.append(author[0] + ",");
			record1.append(author[1]);
			record1.append("\n");
			//System.out.println("This is the appended string: " + record1 + " , ok.");
			pw.write(record1.toString());
		}
			
		pw.close();
	}
	
	public void resetBooks(String[][] records)
	{
		FileWriter fw = null;
		try {
			fw = new FileWriter("Books.csv", false);
			fw.flush();
		}
		catch (IOException e)
		{
			System.out.println("Exception Occurred:");
	        e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		StringBuilder record = new StringBuilder();
		String fields = "B_id,B_name,B_author,B_publisher";
		record.append(fields +"\n");
		
		pw.write(record.toString());
		for(String[] author : records) {
			StringBuilder record1 = new StringBuilder();
			record1.append(author[0] + ",");
			record1.append(author[1] + ",");
			record1.append(author[2] + ",");
			record1.append(author[3]);
			record1.append("\n");
			System.out.println("This is the appended string: " + record1 + " , ok.");
			pw.write(record1.toString());
		}
			
		pw.close();
	}
	
	public void resetPublishers(String[][] records)
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
		StringBuilder record = new StringBuilder();
		String fields = "P_id,P_name,P_Address";
		record.append(fields +"\n");
		
		pw.write(record.toString());
		for(String[] publisher : records) {
			StringBuilder record1 = new StringBuilder();
			record1.append(publisher[0] + ",");
			record1.append(publisher[1] + ",");
			record1.append(publisher[2] + ",");
			record1.append("\n");
			System.out.println("This is the appended string: " + record1 + " , ok.");
			pw.write(record1.toString());
		}
			
		pw.close();
	}
	
	//Needs an ID and a name
	public void writeAuthor(Author author)
	{
		FileWriter fw = null;
		try {
			fw = new FileWriter("Authors.csv", true);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		
		StringBuilder record = new StringBuilder();
		record.append(author.getID() + ",");
		record.append(author.getName());
		record.append("\n");
		
		pw.write(record.toString());
		pw.close();
		
		authorTable = updateAuthorArray();
		
		System.out.println("Author has been added to the database.");
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
		record.append(publisher.getAddress() + ",");
		record.append("\n");
		
		pw.write(record.toString());
		pw.close();
		
		publisherTable = updatePublisherArray();
		
		System.out.println("Publisher has been added to the database.");
	}
	
	public void writeBook(Book book)
	{
		FileWriter fw = null;
		try {
			fw = new FileWriter("Books.csv", true);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		
		StringBuilder record = new StringBuilder();
		record.append(book.getID() + ",");
		record.append(book.getName() + ",");
		record.append(book.getAuthor() + ",");
		record.append(book.getPublisher() + ",");
		record.append("\n");
		
		pw.write(record.toString());
		pw.close();
		
		bookTable = updateBookArray();
		
		System.out.println("Book has been added to the database.");
	}
	
	public static String[][] updateAuthorArray() {
		File file = new File("Authors.csv");

		String[][] authorTable = new String[getNumberOfRecords("Authors.csv")][];

		Scanner inputStream;
		int i = 0;
		try {
			inputStream = new Scanner(file);
			inputStream.nextLine();
			while (inputStream.hasNext()) {
				String line = inputStream.nextLine();
				// System.out.println(line);
				String[] values = line.split(",");
				try {
					authorTable[i] = values;
				} catch (ArrayIndexOutOfBoundsException f) {
					// f.printStackTrace();
				}
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("This is the author table: " + Arrays.deepToString(authorTable));
		return authorTable;
	}

	public static String[][] updateBookArray() {
		File file = new File("Books.csv");

		String[][] bookTable = new String[getNumberOfRecords("Books.csv")][];

		Scanner inputStream;
		int i = 0;
		try {
			inputStream = new Scanner(file);
			inputStream.nextLine();
			while (inputStream.hasNext()) {
				String line = inputStream.nextLine();
				// System.out.println(line);
				String[] values = line.split(",");
				try {
					bookTable[i] = values;
				} catch (ArrayIndexOutOfBoundsException f) {
					// f.printStackTrace();
				}
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bookTable;
	}

	public static String[][] updatePublisherArray() {
		File file = new File("Publishers.csv");

		String[][] publisherTable = new String[getNumberOfRecords("Publishers.csv")][];

		Scanner inputStream;
		int i = 0;
		try {
			inputStream = new Scanner(file);
			inputStream.nextLine();
			while (inputStream.hasNext()) {
				String line = inputStream.nextLine();
				// System.out.println(line);
				String[] values = line.split("\\s*,\\s*");
				// System.out.println("Updating publisher array to this: " +
				// Arrays.deepToString(values));
				try {
					publisherTable[i] = values;
				} catch (ArrayIndexOutOfBoundsException f) {
					// f.printStackTrace();
				}
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		//System.out.println(Arrays.deepToString(publisherTable));
		return publisherTable;
	}
	
	public void reWrite(String[][] records){
		
	}
	
	public static int getNumberOfRecords(String fileName) {

		int linenumber = 0;
		try {
			File file = new File(fileName);

			if (file.exists()) {
				FileReader fr = new FileReader(file);
				LineNumberReader lnr = new LineNumberReader(fr);

				while (lnr.readLine() != null) {
					linenumber++;
				}

				// System.out.println("Total number of lines : " + linenumber);

				lnr.close();

			} else {
				System.out.println("File does not exists!");
				publisherFileCheck();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return linenumber - 1;
	}
}
