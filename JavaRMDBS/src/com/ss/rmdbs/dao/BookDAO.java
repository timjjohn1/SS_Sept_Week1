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

import com.ss.rmdbs.objs.Book;

/**
 * @author tj
 * BookDAO is the data access object class
 * Interacts with the Books.csv file to read/write pojos to it
 */
public class BookDAO {

	public BookDAO() {

    	bookFileCheck();

    	setBooks(readBooks());
	}
	
	private List<Book> books;

	/**
	 * @return the books
	 */
	public List<Book> getBooks() {
		return books;
	}

	/**
	 * @param books the books to set
	 */
	public void setBooks(List<Book> books) {
		this.books = books;
	}

	//Reads the file and puts all of the books into a list
	//Returns the list of books
	public List<Book> readBooks() {
		List<Book> bookList = new ArrayList<Book>();

		File file = new File("Books.csv");

		int id;
		String name;
		int authorID;
		int publisherID;

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
					authorID = Integer.parseInt(values[2]);
					publisherID = Integer.parseInt(values[3]);

					Book book = new Book(id, name, authorID, publisherID);

					bookList.add(book);

				} catch (ArrayIndexOutOfBoundsException f) {
					// f.printStackTrace();
				}
			}

	    	inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bookList;
	}

	//Takes in a list of books and creates a string of them in CSV format
	public StringBuilder convertBooksCSV(List<Book> bookList) {
		StringBuilder csvString = new StringBuilder();

		if (bookList != null) {
			for (Book book : bookList) {
				csvString.append(book.getID() + "," + book.getName() + "," + book.getAuthor() + ","
						+ book.getPublisher() + "\n");
			}
		}
		return csvString;
	}
	
	//Checks to see if the file exists and if it does it won't act
	//If file DNE then it will created it with the fields as a header
	public boolean bookFileCheck()
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
		String fields = "B_id,B_name,B_author,B_publisher";
		record.append(fields +"\n");
		
		pw.write(record.toString());
		pw.close();
		
		return true;
		
	}
	//Resets the file to have the desired books in it
	public void resetBooks(List<Book> bookList)
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
		String fields = "B_id,B_name,B_author,B_publisher\n";
		
		pw.write(fields);
		pw.write(convertBooksCSV(bookList).toString());

			
		pw.close();
	}
	
	//Writes a book to the file
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
		record.append(book.getPublisher());
		record.append("\n");
		
		pw.write(record.toString());
		pw.close();
		
		setBooks(readBooks());
		
		System.out.println("Book has been added to the database.");
	}
}
