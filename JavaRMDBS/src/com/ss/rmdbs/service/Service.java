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
 * @author tj
 *  Implements the CRUD of the Database
 *  Is the middle man between the data access objects and the presentation layer(RMDBS.java)
 */
public class Service {

	private AuthorDAO authorDAO;
	private BookDAO bookDAO;
	private PublisherDAO publisherDAO;
	
	//Creates instances of the DAO's
	public Service() {
		//scanner = new Scanner(System.in);
		authorDAO = new AuthorDAO();
		bookDAO = new BookDAO();
		publisherDAO = new PublisherDAO();
	}
	
	//
	// Creates - sets up the files and objects
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
	
	//Gets the next author ID by finding the highest ID in the table and returns one more than that
	public int findNextAuthorID() {
		List<Author> authorList = AuthorDAO.getAuthors();
    	if (authorList == null || authorList.size() == 0) {
			return 1;
		}

		int id = 0;
		int max = 0;
		//Gets the id for all the authors and sets the new max
		for (Author author : authorList) {
			try {
				id = author.getID();
			} catch (NullPointerException e) {}
			if (id > max) {
				max = id;
			}
		}
		// System.out.println("The max is: " + max);

		return max+1;
	}

	//Gets the next book ID
	public int findNextBookID() {

    	List<Book> bookList = bookDAO.getBooks();
		if (bookList == null || bookList.size() == 0) {
			return 1;
		}
		int id = 0;
		int max = 0;
		//Loops through all of the books and gets the next id
		for (Book book : bookList) {
			try {
				id = book.getID();
			} catch (NullPointerException e) {}
			if (id > max) {
				max = id;
			}
		}

		return max+1;
	}

	//Gets the next publisher ID
	public int findNextPublisherID() {

    	List<Publisher> publisherList = publisherDAO.getPublishers();
    	if (publisherList == null || publisherList.size() == 0) {
			return 1;
		}

		int id = 0;
		int max = 0;
		//Loops through and get the next publisher ID
		for (Publisher publisher : publisherList) {
			try {
				id = publisher.getID();
			} catch (NullPointerException e) {}
			if (id > max) {
				max = id;
			}
		}
		// System.out.println("The max is: " + max);

		return max+1;
	}
	
	
	//
	//Key checks, checks if the ID is in the table and returns according boolean value
	//
	//Checks to see if the author ID and publisher ID's are in their tables
	public boolean bookKeyCheckAP(int a_id, int p_id) {
		if (authorKeyCheck(a_id) && publisherKeyCheck(p_id))
			return true;
		else
			return false;
	}
	
	//Checks to see if the book ID exists in the book table
	public boolean bookKeyCheck(int b_id){
		List<Book> books = bookDAO.getBooks();
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
	
	//Checks to see if the author ID exists in the author table
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
	
	//Checks to see if the publisher ID exists in the publisher table
	public boolean publisherKeyCheck(int p_id) {
		List<Publisher> publishers = publisherDAO.getPublishers();
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
	
	public boolean readAuthorAll() {
		Boolean[] valid = new Boolean[1];
		valid[0] = false;
		List<Author> authors = AuthorDAO.getAuthors();
		authors.stream().forEach(author ->{
			System.out.println("Author ID: " + author.getID());
			System.out.println("Author Name: " + author.getName().replaceAll("~", ","));
			valid[0] = true;
		});
		return valid[0];
	}
	
	//Creates a stream that filters out the authors and prints it to console
	public boolean readAuthorByID(int authorID)
	{
		Boolean[] valid = new Boolean[1];
		valid[0] = false;
		List<Author> authors = AuthorDAO.getAuthors();
		authors.stream()
			.filter(author -> author.getID() == authorID)
			.forEach(author -> 
				{
					System.out.println("Author ID: " + author.getID());
					System.out.println("Author Name: " + author.getName().replaceAll("~", ","));
					valid[0] = true;
				});
		
//		forEach((author) ->{
//			System.out.println("Author ID: " + author.getID());
//			System.out.println("Title: " + author.getName().replaceAll("~", ","));
//		} );

		//The previous stream is the same as this loop
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
	
	//Filters authors that have a user inputed name and prints to console
	public boolean readAuthorByName(String authorName)
	{
		Boolean[] valid = new Boolean[1];
		valid[0] = false;
		List<Author> authors = AuthorDAO.getAuthors();
		authors.stream()
			.filter(author -> author.getName().equals(authorName))
			.forEach(author -> 
			{
				System.out.println("Author ID: " + author.getID());
				System.out.println("Author Name: " + author.getName().replaceAll("~", ","));
				valid[0] = true;
			});
		//The previous stream is the same as this loop
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
	
	public boolean readBookAll() {
		Boolean[] valid = new Boolean[1];
		valid[0] = false;
		List<Book> books = bookDAO.getBooks();
		books.stream().forEach(book ->{
			System.out.println("Book ID: " + book.getID());
			System.out.println("Book Name: " + book.getName().replaceAll("~", ","));
			valid[0] = true;
		});
		return valid[0];
	}
	//Reads desired book ID
	//Prints to console
	public boolean readBookByID(int bookID) {
		Boolean[] valid = new Boolean[1];
		valid[0] = false;
		List<Book> books = bookDAO.getBooks();
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
	public boolean readBookByName(String bookName) {
		Boolean[] valid = new Boolean[1];
		valid[0] = false;
		List<Book> books = bookDAO.getBooks();
		books.stream()
			.filter(book -> book.getName().equals(bookName))
			.forEach(book -> 
			{
				System.out.println("Book ID: " + book.getID());
				System.out.println("Title: " + book.getName().replaceAll("~", ","));
				System.out.println("Author ID: " + book.getAuthor());
				System.out.println("Publisher ID: " + book.getPublisher());
				valid[0] = true;
			});
		return valid[0];
		

		//The previous stream is the same as this loop
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
	public boolean readBookByAuthorID(int authorID) {
		Boolean[] valid = new Boolean[1];
		valid[0] = false;
		List<Book> books = bookDAO.getBooks();
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
		//The previous stream is the same as this loop
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
	public boolean readBookByPublisherID(int publisherID) {
		
		Boolean[] valid = new Boolean[1];
		valid[0] = false;
		List<Book> books = bookDAO.getBooks();
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
		

		//The previous stream is the same as this loop
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
	
	public boolean readPublisherAll() {
		Boolean[] valid = new Boolean[1];
		valid[0] = false;
		List<Publisher> publishers = publisherDAO.getPublishers();
		publishers.stream().forEach(publisher ->{
			System.out.println("Publisher ID: " + publisher.getID());
			System.out.println("Publisher Name: " + publisher.getName().replaceAll("~", ","));
			valid[0] = true;
		});
		return valid[0];
	}
	//Reads the publishers by desired input from user
	public boolean readPublisherByID(int publisherID) {
		
		Boolean[] valid = new Boolean[1];
		valid[0] = false;
		List<Publisher> publishers = publisherDAO.getPublishers();
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
		

		//The previous stream is the same as this loop
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
	public boolean readPublisherByName(String publisherName) {
		Boolean[] valid = new Boolean[1];
		valid[0] = false;
		List<Publisher> publishers = publisherDAO.getPublishers();
		publishers.stream()
			.filter(publisher -> publisher.getName().equals(publisherName))
			.forEach(publisher -> 
			{
				System.out.println("Publisher ID: " + publisher.getID());
				System.out.println("Publisher Name: " + publisher.getName().replaceAll("~", ","));
				System.out.println("Publisher Address: " + publisher.getAddress().replaceAll("~", ","));
				valid[0] = true;
			});
		return valid[0];
		

		//The previous stream is the same as this loop
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
	public boolean readPublisherByAddress(String publisherAddress) {
		
		Boolean[] valid = new Boolean[1];
		valid[0] = false;
		List<Publisher> publishers = publisherDAO.getPublishers();
		publishers.stream()
			.filter(publisher -> publisher.getAddress().equals(publisherAddress))
			.forEach(publisher -> 
			{
				System.out.println("Publisher ID: " + publisher.getID());
				System.out.println("Publisher Name: " + publisher.getName().replaceAll("~", ","));
				System.out.println("Publisher Address: " + publisher.getAddress().replaceAll("~", ","));
				valid[0] = true;
			});
		return valid[0];
		

		//The previous stream is the same as this loop
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
	
	
	//
	//Updates
	//
	//Gets the ID from the user and the new field information and sets the record to have new information
	//
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
		authorDAO.resetAuthors(authors);
		return updated[0];
		

		//The previous stream is the same as this loop
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
		List<Book> books = bookDAO.getBooks();
		//Filters the books and sets new book
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
		bookDAO.resetBooks(books);
		return updated[0];
		

		//The previous stream is the same as this loop
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
		List<Publisher> publishers = publisherDAO.getPublishers();
		publishers.stream()
			.filter(publisher -> publisher.getID() == publisherID)
			.forEach(publisher -> 
				{
					publisher.setName(publisherName);
					publisher.setAddress(publisherAddress);
					updated[0] = true;
				}
			);
		publisherDAO.resetPublishers(publishers);
		return updated[0];
		

		//The previous stream is the same as this loop
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

	//
	//Deletes
	//
	//Gets what ID the user wants to delete and removes it from the list
	//Calls DAO to update file
	//Resets the file
	public boolean deleteAnAuthor(int authorID) {
		boolean updated = false;
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
		List<Book> books = bookDAO.getBooks();
		List<Book> toRemoveBooks = new ArrayList<Book>();
		//String[][] bookTable = manager.getBookTable();

		for(Book book : books) {
			if(book.getAuthor() == authorID) {
				toRemoveBooks.add(book);
			}
		}
		
		books.removeAll(toRemoveBooks);
		authors.removeAll(toRemoveAuthors);

		bookDAO.resetBooks(books);
		authorDAO.resetAuthors(authors);
		return updated;
	}

	public boolean deleteABook(int bookID) {
		boolean updated = false;
		
		List<Book> books = bookDAO.getBooks();

		List<Book> toRemoveBooks = new ArrayList<Book>();
		for(Book book : books){
			if(book.getID() == bookID) {
				toRemoveBooks.add(book);
				updated = true;
			}
		}

		books.removeAll(toRemoveBooks);
		bookDAO.resetBooks(books);
		return updated;
	}

	public boolean deleteAPublisher(int publisherID) {
		boolean updated = false;
		
		List<Publisher> publishers = publisherDAO.getPublishers();
		//manager.printPublisherList();
		List<Publisher> toRemovePublishers = new ArrayList<Publisher>();
		
		for(Publisher publisher : publishers){
			if(publisher.getID() == publisherID) {
				toRemovePublishers.add(publisher);
				updated = true;
			}
		}
		
		//Get books associated with this author
		List<Book> books = bookDAO.getBooks();
		List<Book> toRemoveBooks = new ArrayList<Book>();
		//String[][] bookTable = manager.getBookTable();

		for(Book book : books) {
			if(book.getPublisher() == publisherID) {
				toRemoveBooks.add(book);
			}
		}
		
		books.removeAll(toRemoveBooks);
		publishers.removeAll(toRemovePublishers);
		
		bookDAO.resetBooks(books);
		publisherDAO.resetPublishers(publishers);
		return updated;
	}
	

}
