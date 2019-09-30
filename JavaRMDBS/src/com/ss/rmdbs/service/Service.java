/**
 * 
 */
package com.ss.rmdbs.service;

import java.util.ArrayList;
import java.util.List;

import com.ss.rmdbs.dao.AuthorDAO;
import com.ss.rmdbs.dao.BookDAO;
import com.ss.rmdbs.dao.PublisherDAO;
import com.ss.rmdbs.objs.Author;
import com.ss.rmdbs.objs.Book;
import com.ss.rmdbs.objs.Publisher;

//import java.util.Arrays;
//import java.util.Scanner;

/**
 * @author sj Implements the CRUD of the Database
 */
public class Service {

	//private static Scanner scanner;
	private static AuthorDAO authorDAO;
	private static BookDAO bookDAO;
	private static PublisherDAO publisherDAO;
	
	public Service() {
		//scanner = new Scanner(System.in);
		authorDAO = new AuthorDAO();
		bookDAO = new BookDAO();
		publisherDAO = new PublisherDAO();
		//manager.authorFileCheck();
		//manager.bookFileCheck();
		//manager.publisherFileCheck();
	}
	
	//
	// Creates
	//

	public void createAnAuthor(String authorName) {
		Author author = new Author(findNextAuthorID(), authorName);
		authorDAO.writeAuthor(author);
	}

	public void createABook(String bookName, int a_id, int p_id){
		Book book = new Book(findNextBookID(), bookName, a_id, p_id);
		bookDAO.writeBook(book);
	}

	public void createAPublisher(String publisherName, String publisherAddress) {
		Publisher publisher = new Publisher(findNextPublisherID(), publisherName, publisherAddress);
		publisherDAO.writePublisher(publisher);
	}
	
	public static int findNextAuthorID() {
		List<Author> authorList = AuthorDAO.getAuthors();
    	if (authorList == null || authorList.size() == 0) {
			return 1;
		}

		int id = 0;
		int max = 0;
		for (Author author : authorList) {
			try {

//				System.out.println(author.getID());
				id = author.getID();
			} catch (NullPointerException e) {}
			// System.out.println("The parsed ID is: " + Integer.parseInt(id) + " for
			// Author: " + record[1]);
			if (id > max) {
				max = id;
			}
		}
		// System.out.println("The max is: " + max);

		return max+1;
	}

	public static int findNextBookID() {

    	List<Book> bookList = BookDAO.getBooks();
//    	BookDAO.printBookList();
		//System.out.println("We are in fNAID: " + Arrays.deepToString(manager.getBookTable()));
		if (bookList == null || bookList.size() == 0) {
			return 1;
		}
		int id = 0;
		int max = 0;
		for (Book book : bookList) {
			try {

//				System.out.println(book.getID());
				id = book.getID();
			} catch (NullPointerException e) {
			}
			// System.out.println("The parsed ID is: " + Integer.parseInt(id) + " for
			// Book: " + record[1]);
			if (id > max) {
				max = id;
			}
		}
		// System.out.println("The max is: " + max);

		return max+1;
	}

	public static int findNextPublisherID() {

    	List<Publisher> publisherList = PublisherDAO.getPublishers();
//   	System.out.print("Printing publisher list: ");
//    	PublisherDAO.printPublisherList();
    	if (publisherList == null || publisherList.size() == 0) {
			return 1;
		}

		int id = 0;
		int max = 0;
		for (Publisher publisher : publisherList) {
			try {
//				System.out.println(publisher.getID());
				id = publisher.getID();
			} catch (NullPointerException e) {
			}
			if (id > max) {
				max = id;
			}
		}
		// System.out.println("The max is: " + max);

		return max+1;
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
		List<Book> books = BookDAO.getBooks();
		try {
			for(Book book : books) {
				if(book.getID() == b_id) {
					return true;
				}
			}
		}catch (NullPointerException e) {
			System.out.println("Book ID not found.");
		}

		return false;
	}
	
	public boolean authorKeyCheck(int a_id) {
		List<Author> authors = AuthorDAO.getAuthors();
		//manager.printAuthorList();
		try {
			for(Author author : authors) {

				//System.out.println("Checking author id: " + a_id + " against :" + author.getID());
				if(author.getID() == a_id) {
					return true;
				}
			}
		}catch (NullPointerException e) {
			System.out.println("Author ID not found.");
		}
		return false;
	}
	
	public boolean publisherKeyCheck(int p_id) {
		List<Publisher> publishers = PublisherDAO.getPublishers();
		//manager.printPublisherList();
		try {
			for(Publisher publisher : publishers) {

				//System.out.println("Checking publisher id: " + p_id + " against :" + publisher.getID());
				if(publisher.getID() == p_id) {
					return true;
				}
			}
		}catch (NullPointerException e) {
			System.out.println("Publisher ID not found.");
		}
		return false;
	}
	
	public static boolean readAuthorByID(int authorID)
	{
		Boolean[] valid = new Boolean[1];
		valid[0] = false;
		List<Author> authors = AuthorDAO.getAuthors();
		authors.stream()
			.filter(author -> author.getID() == authorID)
			.forEach(author -> 
				{
					System.out.println("Author ID: " + author.getID());
					System.out.println("Title: " + author.getName().replaceAll("~", ","));
					valid[0] = true;
				});
		
//		forEach((author) ->{
//			System.out.println("Author ID: " + author.getID());
//			System.out.println("Title: " + author.getName().replaceAll("~", ","));
//		} );
//		for(Author author : authors){
//			//System.out.println("The name being checked is: '" + record[1] + "' against : '" + authorName + "'");
//			if (author.getID() == authorID) {
//				System.out.println("Author ID: " + author.getID());
//				System.out.println("Title: " + author.getName().replaceAll("~", ","));
//				valid = true;
//			}
//		}
		return valid[0];
	}
	public static boolean readAuthorByName(String authorName)
	{
		Boolean[] valid = new Boolean[1];
		valid[0] = false;
		List<Author> authors = AuthorDAO.getAuthors();
		authors.stream()
		.filter(author -> author.getName() == authorName)
		.forEach(author -> 
			{
				System.out.println("Author ID: " + author.getID());
				System.out.println("Title: " + author.getName().replaceAll("~", ","));
				valid[0] = true;
			});
//		for(Author author : authors){
//			//System.out.println("The name being checked is: '" + author.getName() + "' against : '" + authorName + "'");
//			if (author.getName().compareTo(authorName) == 0) {
//				System.out.println("Author ID: " + author.getID());
//				System.out.println("Title: " + author.getName().replaceAll("~", ","));
//				valid = true;
//			}
//		}
		return valid[0];
	}
	
	
	public static boolean readBookByID(int bookID) {
		Boolean[] valid = new Boolean[1];
		valid[0] = false;
		List<Book> books = BookDAO.getBooks();
		books.stream()
			.filter(book -> book.getID() == bookID)
			.forEach(book -> 
			{
				System.out.println("Book ID: " + book.getID());
				System.out.println("Title: " + book.getName().replaceAll("~", ","));
				System.out.println("Author ID: " + book.getAuthor());
				System.out.println("Publisher ID: " + book.getPublisher());
				valid[0] = true;
			});
		return valid[0];
		
//		boolean valid = false;
//		List<Book> books = BookDAO.getBooks();
//		for(Book book : books){
//			//System.out.println("The name being checked is: '" + record[1] + "' against : '" + bookName + "'");
//			if (book.getID() == bookID) {
//				System.out.println("Book ID: " + book.getID());
//				System.out.println("Title: " + book.getName().replaceAll("~", ","));
//				System.out.println("Author ID: " + book.getAuthor());
//				System.out.println("Publisher ID: " + book.getPublisher());
//				valid = true;
//			}
//		}
//		return valid;
	}
	public static boolean readBookByName(String bookName) {
		Boolean[] valid = new Boolean[1];
		valid[0] = false;
		List<Book> books = BookDAO.getBooks();
		books.stream()
			.filter(book -> book.getName() == bookName)
			.forEach(book -> 
			{
				System.out.println("Book ID: " + book.getID());
				System.out.println("Title: " + book.getName().replaceAll("~", ","));
				System.out.println("Author ID: " + book.getAuthor());
				System.out.println("Publisher ID: " + book.getPublisher());
				valid[0] = true;
			});
		return valid[0];
		
		
//		boolean valid = false;
//		List<Book> books = BookDAO.getBooks();
//		for(Book book : books){
//			//System.out.println("The name being checked is: '" + record[1] + "' against : '" + bookName + "'");
//			if (book.getName().compareTo(bookName) == 0) {
//				System.out.println("Book ID: " + book.getID());
//				System.out.println("Title: " + book.getName().replaceAll("~", ","));
//				System.out.println("Author ID: " + book.getAuthor());
//				System.out.println("Publisher ID: " + book.getPublisher());
//				valid = true;
//			}
//		}
//		return valid;
	}
	public static boolean readBookByAuthorID(int authorID) {
		Boolean[] valid = new Boolean[1];
		valid[0] = false;
		List<Book> books = BookDAO.getBooks();
		books.stream()
			.filter(book -> book.getAuthor() == authorID)
			.forEach(book -> 
			{
				System.out.println("Book ID: " + book.getID());
				System.out.println("Title: " + book.getName().replaceAll("~", ","));
				System.out.println("Author ID: " + book.getAuthor());
				System.out.println("Publisher ID: " + book.getPublisher());
				valid[0] = true;
			});
		return valid[0];
		
		
//		boolean valid = false;
//		List<Book> books = BookDAO.getBooks();
//		for(Book book : books){
//			//System.out.println("The name being checked is: '" + book.getAuthor() + "' against : '" + authorID + "'");
//			if (book.getAuthor() == authorID) {
//				System.out.println("Book ID: " + book.getID());
//				System.out.println("Title: " + book.getName().replaceAll("~", ","));
//				System.out.println("Author ID: " + book.getAuthor());
//				System.out.println("Publisher ID: " + book.getPublisher());
//				valid = true;
//			}
//		}
//		return valid;
	}
	public static boolean readBookByPublisherID(int publisherID) {
		
		Boolean[] valid = new Boolean[1];
		valid[0] = false;
		List<Book> books = BookDAO.getBooks();
		books.stream()
			.filter(book -> book.getPublisher() == publisherID)
			.forEach(book -> 
			{
				System.out.println("Book ID: " + book.getID());
				System.out.println("Title: " + book.getName().replaceAll("~", ","));
				System.out.println("Author ID: " + book.getAuthor());
				System.out.println("Publisher ID: " + book.getPublisher());
				valid[0] = true;
			});
		return valid[0];
//		boolean valid = false;
//		List<Book> books = BookDAO.getBooks();
//		for(Book book : books){
//			//System.out.println("The name being checked is: '" + record[1] + "' against : '" + bookName + "'");
//			if (book.getPublisher() == publisherID) {
//				System.out.println("Book ID: " + book.getID());
//				System.out.println("Title: " + book.getName().replaceAll("~", ","));
//				System.out.println("Author ID: " + book.getAuthor());
//				System.out.println("Publisher ID: " + book.getPublisher());
//				valid = true;
//			}
//		}
//		return valid;
	}
	
	public static boolean readPublisherByID(int publisherID) {
		
		Boolean[] valid = new Boolean[1];
		valid[0] = false;
		List<Publisher> publishers = PublisherDAO.getPublishers();
		publishers.stream()
			.filter(publisher -> publisher.getID() == publisherID)
			.forEach(publisher -> 
			{
				System.out.println("Publisher ID: " + publisher.getID());
				System.out.println("Publisher Name: " + publisher.getName().replaceAll("~", ","));
				System.out.println("Publisher Address: " + publisher.getAddress().replaceAll("~", ","));
				valid[0] = true;
			});
		return valid[0];
//		boolean valid = false;
//		List<Publisher> publishers = PublisherDAO.getPublishers();
//		for(Publisher publisher : publishers){
//			//System.out.println("The ID being checked is: '" + record[0] + "' against : '" + publisherID + "'");
//			if (publisher.getID() == publisherID) {
//				System.out.println("Publisher ID: " + publisher.getID());
//				System.out.println("Publisher Name: " + publisher.getName().replaceAll("~", ","));
//				System.out.println("Publisher Address: " + publisher.getAddress().replaceAll("~", ","));
//				valid = true;
//			}
//		}
//		return valid;
	}
	public static boolean readPublisherByName(String publisherName) {
		Boolean[] valid = new Boolean[1];
		valid[0] = false;
		List<Publisher> publishers = PublisherDAO.getPublishers();
		publishers.stream()
			.filter(publisher -> publisher.getName() == publisherName)
			.forEach(publisher -> 
			{
				System.out.println("Publisher ID: " + publisher.getID());
				System.out.println("Publisher Name: " + publisher.getName().replaceAll("~", ","));
				System.out.println("Publisher Address: " + publisher.getAddress().replaceAll("~", ","));
				valid[0] = true;
			});
		return valid[0];
//		boolean valid = false;
//		List<Publisher> publishers = PublisherDAO.getPublishers();
//		for(Publisher publisher : publishers){
//			//System.out.println("The ID being checked is: '" + record[0] + "' against : '" + publisherID + "'");
//			if (publisher.getName().compareTo(publisherName) == 0) {
//				System.out.println("Publisher ID: " + publisher.getID());
//				System.out.println("Publisher Name: " + publisher.getName().replaceAll("~", ","));
//				System.out.println("Publisher Address: " + publisher.getAddress().replaceAll("~", ","));
//				valid = true;
//			}
//		}
//		return valid;
	}
	public static boolean readPublisherByAddress(String publisherAddress) {
		
		Boolean[] valid = new Boolean[1];
		valid[0] = false;
		List<Publisher> publishers = PublisherDAO.getPublishers();
		publishers.stream()
			.filter(publisher -> publisher.getAddress() == publisherAddress)
			.forEach(publisher -> 
			{
				System.out.println("Publisher ID: " + publisher.getID());
				System.out.println("Publisher Name: " + publisher.getName().replaceAll("~", ","));
				System.out.println("Publisher Address: " + publisher.getAddress().replaceAll("~", ","));
				valid[0] = true;
			});
		return valid[0];
//		boolean valid = false;
//		List<Publisher> publishers = PublisherDAO.getPublishers();
//		for(Publisher publisher : publishers){
//			//System.out.println("The ID being checked is: '" + record[0] + "' against : '" + publisherID + "'");
//			if (publisher.getAddress().compareTo(publisherAddress) == 0) {
//				System.out.println("Publisher ID: " + publisher.getID());
//				System.out.println("Publisher Name: " + publisher.getName().replaceAll("~", ","));
//				System.out.println("Publisher Address: " + publisher.getAddress().replaceAll("~", ","));
//				valid = true;
//			}
//		}
//		return valid;
	}
	
	public boolean updateAnAuthor(int authorID, String authorName) {
		Boolean[] updated = new Boolean[1];
		updated[0] = false;
		List<Author> authors = AuthorDAO.getAuthors();
		authors.stream()
			.filter(author -> author.getID() == authorID)
			.forEach(author -> 
				{
					author.setName(authorName); 
					updated[0] = true;
				}
			);
		AuthorDAO.resetAuthors(authors);
		return updated[0];
//		for(Author author : authors){
//			//System.out.println("The name being checked is: '" + record[1] + "' against : '" + authorName + "'");
//			if (author.getID() == authorID) {
//				author.setName(authorName);
//				valid = true;
//			}
//		}
//		AuthorDAO.resetAuthors(authors);
//		return valid;
	}

	public boolean updateABook(int bookID, String bookName, int authorID, int publisherID) {

		Boolean[] updated = new Boolean[1];
		updated[0] = false;
		List<Book> books = BookDAO.getBooks();
		books.stream()
			.filter(book -> book.getID() == bookID)
			.forEach(book -> 
				{
					book.setName(bookName);
					book.setAuthor(authorID);
					book.setPublisher(publisherID); 
					updated[0] = true;
				}
			);
		BookDAO.resetBooks(books);
		return updated[0];
		
//		boolean updated = false;
//		List<Book> books = BookDAO.getBooks();
//		if(bookKeyCheckAP(authorID, publisherID) == false) {
//			return updated;
//		}
//		for(Book book : books){
//			if(book.getID() == bookID) {
//				book.setName(bookName);
//				book.setAuthor(authorID);
//				book.setPublisher(publisherID);
//				updated = true;
//			}
//		}
//		BookDAO.resetBooks(books);
//		return updated;
	}

	public boolean updateAPublisher(int publisherID, String publisherName, String publisherAddress) {
		Boolean[] updated = new Boolean[1];
		updated[0] = false;
		List<Publisher> publishers = PublisherDAO.getPublishers();
		publishers.stream()
			.filter(publisher -> publisher.getID() == publisherID)
			.forEach(publisher -> 
				{
					publisher.setName(publisherName);
					publisher.setAddress(publisherAddress);
					updated[0] = true;
				}
			);
		PublisherDAO.resetPublishers(publishers);
		return updated[0];
		
//		boolean updated = false;
//		List<Publisher> publishers = PublisherDAO.getPublishers();
//		for(Publisher publisher : publishers){
//			if(publisher.getID() == publisherID) {
//				publisher.setName(publisherName);
//				publisher.setAddress(publisherAddress);
//				updated = true;
//			}
//		}
//		PublisherDAO.resetPublishers(publishers);
//		return updated;
	}

	public boolean deleteAnAuthor(int authorID) {
		boolean updated = false;
//		int newNumRecords = Manager.getNumberOfRecords("Authors.csv")-1;
//		String[][] authorTable = manager.getAuthorTable();
//		String[][] newAuthorTable = new String[newNumRecords][];
		List<Author> authors = AuthorDAO.getAuthors();

		List<Author> toRemoveAuthors = new ArrayList<Author>();
		
		for(Author author : authors){
			if(author.getID() == authorID) {
				toRemoveAuthors.add(author);
				updated = true;
			}
		}
//		System.out.println(toRemoveAuthors.toString());
		
		//Get books associated with this author
		List<Book> books = BookDAO.getBooks();
		List<Book> toRemoveBooks = new ArrayList<Book>();
		//String[][] bookTable = manager.getBookTable();

		for(Book book : books) {
			if(book.getAuthor() == authorID) {
				toRemoveBooks.add(book);
			}
		}
		
		books.removeAll(toRemoveBooks);
		authors.removeAll(toRemoveAuthors);

		BookDAO.resetBooks(books);
		AuthorDAO.resetAuthors(authors);
		return updated;
	}

	public boolean deleteABook(int bookID) {
		boolean updated = false;
		
		List<Book> books = BookDAO.getBooks();

		List<Book> toRemoveBooks = new ArrayList<Book>();
		for(Book book : books){
			if(book.getID() == bookID) {
				toRemoveBooks.add(book);
				updated = true;
			}
		}

		books.removeAll(toRemoveBooks);
		BookDAO.resetBooks(books);
		return updated;
	}

	public boolean deleteAPublisher(int publisherID) {
		boolean updated = false;
		
		List<Publisher> publishers = PublisherDAO.getPublishers();
		//manager.printPublisherList();
		List<Publisher> toRemovePublishers = new ArrayList<Publisher>();
		
		for(Publisher publisher : publishers){
			if(publisher.getID() == publisherID) {
				toRemovePublishers.add(publisher);
				updated = true;
			}
		}
		
		//Get books associated with this author
		List<Book> books = BookDAO.getBooks();
		List<Book> toRemoveBooks = new ArrayList<Book>();
		//String[][] bookTable = manager.getBookTable();

		for(Book book : books) {
			if(book.getPublisher() == publisherID) {
				toRemoveBooks.add(book);
			}
		}
		
		books.removeAll(toRemoveBooks);
		publishers.removeAll(toRemovePublishers);
		
		BookDAO.resetBooks(books);
		PublisherDAO.resetPublishers(publishers);
		return updated;
	}

}
