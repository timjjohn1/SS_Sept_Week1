/**
 * 
 */
package com.ss.rmdbs;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author sj Implements the CRUD of the Database
 */
public class Service {

	private static Scanner scanner;
	private static Manager manager;
	
	public Service() {
		scanner = new Scanner(System.in);
		manager = new Manager();
//		manager.authorFileCheck();
//		manager.bookFileCheck();
//		manager.publisherFileCheck();
	}
	
	//
	// Creates
	//

	public void createAnAuthor(String authorName) {
		Author author = new Author(findNextAuthorID(), authorName);
		manager.writeAuthor(author);

	}

	public void createABook(String bookName, String a_id, String p_id){
		Book book = new Book(findNextBookID(), bookName, Integer.parseInt(a_id), Integer.parseInt(p_id));
		manager.writeBook(book);
	}

	public void createAPublisher(String publisherName, String publisherAddress) {
		Publisher publisher = new Publisher(findNextPublisherID(), publisherName, publisherAddress);
		manager.writePublisher(publisher);
	}
	
	public static int findNextAuthorID() {
		String[][] records = manager.getAuthorTable();
		//System.out.println("We are in fNAID: " + Arrays.deepToString(manager.getAuthorTable()));
		if (records == null) {
			return 1;
		}
		String id = null;
		int max = 0;
		for (String[] record : records) {
			try {
				id = record[0];
			} catch (NullPointerException e) {
			}
			// System.out.println("The parsed ID is: " + Integer.parseInt(id) + " for
			// Author: " + record[1]);
			if (Integer.parseInt(id) > max) {
				max = Integer.parseInt(id);
			}
		}
		// System.out.println("The max is: " + max);

		return max + 1;
	}

	public static int findNextBookID() {
		String[][] records = manager.getBookTable();
		try {
			if (records == null) {
				return 1;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
		String id = null;
		int max = 0;
		for (String[] record : records) {
			try {
				id = record[0];
			} catch (NullPointerException e) {
			}

			// System.out.println("The parsed ID is: " + Integer.parseInt(id) + " for Book:
			// " + record[1]);
			if (Integer.parseInt(id) > max) {
				max = Integer.parseInt(id);
			}
		}
		// System.out.println("The max is: " + max);
		return max + 1;
	}

	public static int findNextPublisherID() {
		String[][] records = manager.getPublisherTable();
		// System.out.println(Arrays.deepToString(records));
		if (records == null) {
			return 1;
		}
		String id = null;
		int max = 0;
		for (String[] record : records) {
			try {
				id = record[0];
			} catch (NullPointerException e) {
			}

			// System.out.println("The parsed ID is: " + Integer.parseInt(id) + " for
			// Publisher: " + record[1]);
			if (Integer.parseInt(id) > max) {
				max = Integer.parseInt(id);
			}
		}
		// System.out.println("The max is: " + max);
		return max + 1;
	}

	public boolean bookKeyCheckAP(int a_id, int p_id) {
		// System.out.println("Author array: " + Arrays.deepToString(authorTable));
		// System.out.println("Publisher array: " +
		// Arrays.deepToString(publisherTable));
		if (authorKeyCheck(a_id) && publisherKeyCheck(p_id))
			return true;
		else
			return false;
	}
	public boolean bookKeyCheck(int b_id){
		String[][] bookTable = manager.getBookTable();
		
		try {
			for(String[] record : bookTable) {
				if(Integer.parseInt(record[0]) == b_id) {
					return true;
				}
			}
		}catch (NullPointerException e) {
			System.out.println("Book ID not found.");
		}
		return false;
	}
	
	public boolean authorKeyCheck(int a_id) {
		String[][] authorTable = manager.getAuthorTable();
		// System.out.println("Author array: " + Arrays.deepToString(authorTable));
		// System.out.println("Publisher array: " +
		// Arrays.deepToString(publisherTable));
		try {
			for (String[] record : authorTable) {

				if (Integer.parseInt(record[0]) == a_id) {
					return true;
				}
			}
		} catch (NullPointerException err) {
			System.out.println("Author ID not found.");
		}
		return false;
	}
	public boolean publisherKeyCheck(int p_id) {
		String[][] publisherTable = manager.getPublisherTable();
		// System.out.println("Author array: " + Arrays.deepToString(authorTable));
		// System.out.println("Publisher array: " +
		// Arrays.deepToString(publisherTable));
		try {
			for (String[] record : publisherTable) {
				if (Integer.parseInt(record[0]) == p_id) {
					return true;
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Publisher ID not found.");
		}
		return false;
	}
	
	public static boolean readAuthorByName(String authorName)
	{
		boolean valid = false;
		String[][] authorTable = manager.getAuthorTable();
		for(String[] record : authorTable){
			//System.out.println("The name being checked is: '" + record[1] + "' against : '" + authorName + "'");
			if (record[1].compareTo(authorName) == 0) {
				System.out.println("ID: " + record[0]);
				System.out.println("Title: " + record[1]);
				valid = true;
			}
		}
		return valid;
	}
	public static boolean readAuthorByID(String authorID)
	{
		boolean valid = false;
		String[][] authorTable = manager.getAuthorTable();
		for(String[] record : authorTable){
			//System.out.println("The ID being checked is: '" + record[0] + "' against : '" + authorID + "'");
			if (record[0].compareTo(authorID) == 0) {
				System.out.println("ID: " + record[0]);
				System.out.println("Title: " + record[1]);
				valid = true;
			}
		}
		return valid;
	}
	
	
	public static boolean readBookByID(String bookID) {
		boolean valid = false;
		String[][] bookTable = manager.getBookTable();
		for(String[] record : bookTable){
			//System.out.println("The ID being checked is: '" + record[0] + "' against : '" + bookID + "'");
			if (record[0].compareTo(bookID) == 0) {
				System.out.println("ID: " + record[0]);
				System.out.println("Title: " + record[1]);
				System.out.println("Author ID: " + record[2]);
				System.out.println("Publisher ID: " + record[3]);
				valid = true;
			}
		}
		return valid;
	}
	public static boolean readBookByName(String bookName) {
		boolean valid = false;
		String[][] bookTable = manager.getBookTable();
		for(String[] record : bookTable){
			//System.out.println("The Title being checked is: '" + record[1] + "' against : '" + bookName + "'");
			if (record[1].compareTo(bookName) == 0) {
				System.out.println("ID: " + record[0]);
				System.out.println("Title: " + record[1]);
				System.out.println("Author ID: " + record[2]);
				System.out.println("Publisher ID: " + record[3]);
				valid = true;
			}
		}
		return valid;
	}
	public static boolean readBookByAuthorID(String authorID) {
		boolean valid = false;
		String[][] bookTable = manager.getBookTable();
		for(String[] record : bookTable){
			//System.out.println("The ID being checked is: '" + record[2] + "' against : '" + authorID + "'");
			if (record[2].compareTo(authorID) == 0) {
				System.out.println("ID: " + record[0]);
				System.out.println("Title: " + record[1]);
				System.out.println("Author ID: " + record[2]);
				System.out.println("Publisher ID: " + record[3]);
				valid = true;
			}
		}
		return valid;
	}
	public static boolean readBookByPublisherID(String publisherID) {
		boolean valid = false;
		String[][] bookTable = manager.getBookTable();
		for(String[] record : bookTable){
			//System.out.println("The ID being checked is: '" + record[3] + "' against : '" + publisherID + "'");
			if (record[3].compareTo(publisherID) == 0) {
				System.out.println("ID: " + record[0]);
				System.out.println("Title: " + record[1]);
				System.out.println("Author ID: " + record[2]);
				System.out.println("Publisher ID: " + record[3]);
				valid = true;
			}
		}
		return valid;
	}
	
	public static boolean readPublisherByID(String publisherID) {
		boolean valid = false;
		String[][] bookTable = manager.getPublisherTable();
		for(String[] record : bookTable){
			//System.out.println("The ID being checked is: '" + record[0] + "' against : '" + publisherID + "'");
			if (record[0].compareTo(publisherID) == 0) {
				System.out.println("Publisher ID: " + record[0]);
				System.out.println("Publisher Name: " + record[1]);
				System.out.println("Publisher Address: " + record[2]);
				valid = true;
			}
		}
		return valid;
	}
	public static boolean readPublisherByName(String publisherName) {
		boolean valid = false;
		String[][] bookTable = manager.getPublisherTable();
		for(String[] record : bookTable){
			//System.out.println("The Name being checked is: '" + record[1] + "' against : '" + publisherName + "'");
			if (record[1].compareTo(publisherName) == 0) {
				System.out.println("Publisher ID: " + record[0]);
				System.out.println("Publisher Name: " + record[1]);
				System.out.println("Publisher Address: " + record[2]);
				valid = true;
			}
		}
		return valid;
	}
	public static boolean readPublisherByAddress(String publisherAddress) {
		boolean valid = false;
		String[][] bookTable = manager.getPublisherTable();
		//System.out.println(Arrays.deepToString(bookTable));
		for(String[] record : bookTable){
			//System.out.println("The Address being checked is: '" + record[2] + "' against : '" + publisherAddress + "'");
			if (record[2].compareTo(publisherAddress) == 0) {
				System.out.println("Publisher ID: " + record[0]);
				System.out.println("Publisher Name: " + record[1]);
				System.out.println("Publisher Address: " + record[2]);
				valid = true;
			}
		}
		return valid;
	}
	
	public boolean updateAnAuthor(String authorID, String authorName) {
		boolean updated = false;
		String[][] authorTable = manager.getAuthorTable();
		int i = 0;
		for(String[] record : authorTable){
			if(record[0].compareTo(authorID) == 0) {
				authorTable[i][1] = authorName;
				updated = true;
			}
			i++;
		}
		manager.resetAuthors(authorTable);
		return updated;
	}

	public boolean updateABook(String bookID, String bookName, String authorID, String publisherID) {
		boolean updated = false;
		String[][] bookTable = manager.getBookTable();
		int i = 0;
		if(bookKeyCheckAP(Integer.parseInt(authorID), Integer.parseInt(publisherID)) == false) {
			return updated;
		}
		for(String[] record : bookTable){
			if(record[0].compareTo(bookID) == 0) {
				bookTable[i][1] = bookName;
				bookTable[i][2] = authorID;
				bookTable[i][3] = publisherID;
				updated = true;
			}
			i++;
		}
		manager.resetBooks(bookTable);
		return updated;
	}

	public boolean updateAPublisher(String publisherID, String publisherName, String publisherAddress) {
		boolean updated = false;
		String[][] publisherTable = manager.getPublisherTable();
		int i = 0;
		for(String[] record : publisherTable){
			if(record[0].compareTo(publisherID) == 0) {
				publisherTable[i][1] = publisherName;
				publisherTable[i][2] = publisherAddress;
				updated = true;
			}
			i++;
		}
		manager.resetPublishers(publisherTable);
		return updated;
	}

	public boolean deleteAnAuthor(String authorID) {
		boolean updated = false;
		int newNumRecords = Manager.getNumberOfRecords("Authors.csv")-1;
		String[][] authorTable = manager.getAuthorTable();
		String[][] newAuthorTable = new String[newNumRecords][];
		int i = 0;
		for(String[] record : authorTable){
			if(record[0].compareTo(authorID) != 0) {
				newAuthorTable[i] = record;
				updated = true;
				i++;
			}
		}
		if(i == newNumRecords) {
			updated = true;
		}
		
		
		//Get books associated with this author
		newNumRecords = Manager.getNumberOfRecords("Books.csv");
		String[][] bookTable = manager.getBookTable();

		int k = 0;
		for(String[] record : bookTable) {
			if(record[2].compareTo(authorID) == 0) {
				k++;
			}
		}
		
		String[][] newBookTable = new String[newNumRecords - k][];
		
		int j = 0;
		for(String[] record : bookTable){
			if(record[2].compareTo(authorID) != 0) {
				newBookTable[j] = record;
				updated = true;
				j++;
			}
		}
		manager.resetBooks(newBookTable);
		manager.resetAuthors(newAuthorTable);
		return updated;
	}

	public boolean deleteABook(String bookID) {
		boolean updated = false;
		int numRecords = Manager.getNumberOfRecords("books.csv")-1;
		String[][] bookTable = manager.getBookTable();
		String[][] newbookTable = new String[numRecords][];
		int i = 0;
		for(String[] record : bookTable){
			if(record[0].compareTo(bookID) != 0) {
				newbookTable[i] = record;
				updated = true;
				i++;
			}
		}
		if(i == numRecords) {
			updated = true;
		}
		return updated;
	}

	public boolean deleteAPublisher(String publisherID) {
		return false;
	}

}
