/**
 * 
 */
package com.ss.rmdbs.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;


import com.ss.rmdbs.objs.*;
import com.ss.rmdbs.service.Service;
import com.ss.rmdbs.dao.*;

/**
 * @book sj
 *
 */

public class ServiceTest {

	Service service;
	BookDAO bookDAO;
	AuthorDAO authorDAO;
	PublisherDAO publisherDAO;
	
	List<Author> authorList;
	List<Book> bookList;
	List<Publisher> publisherList;
	
	Author author1;
	Author author2;
	Author author3;
	
	Book book1;
	Book book2;
	Book book3;
	
	Publisher publisher1;
	Publisher publisher2;
	Publisher publisher3;
	
	@Before
	public void testInitialize() {
		////System.out.println("Initializing.");
		
		service = new Service();
		bookDAO = new BookDAO();
		authorDAO = new AuthorDAO();
		publisherDAO = new PublisherDAO();
		
		
		authorList = new ArrayList<Author>();
		bookList = new ArrayList<Book>();
		publisherList = new ArrayList<Publisher>();
		author1 = new Author(1, "Tim");
		author2 = new Author(2, "Johnny");
		author3 = new Author(3, "Jessica");
		////System.out.println("Authors created.");
		book1 = new Book(1, "Bible", 1, 2);
		book2 = new Book(2, "To Kill a Mockingbird", 2, 1);
		book3 = new Book(3, "Into the Wild", 3, 3);
		////System.out.println("Books created.");
		publisher1 = new Publisher(1, "Sarah", "123 Addr");
		publisher2 = new Publisher(2, "Maria", "321 Rdda");
		publisher3 = new Publisher(3, "Jane Doe", "Downtown");
		////System.out.println("Publishers created.");
		authorList.add(author1);
		authorList.add(author2);
		authorList.add(author3);
		////System.out.println("Authors added");
		bookList.add(book1);
		bookList.add(book2);
		bookList.add(book3);
		////System.out.println("Books added.");
		publisherList.add(publisher1);
		publisherList.add(publisher2);
		publisherList.add(publisher3);
		////System.out.println("Publisher added.");
		
		authorDAO.resetAuthors(authorList);
		publisherDAO.resetPublishers(publisherList);
		bookDAO.resetBooks(bookList);
		////System.out.println("Initialized.");
	}

	@Test
	public void testFindNextAuthorID() {
		////System.out.println("Executing Test findNextAuthorID.");
//		AuthorDAO.resetAuthors(authorList);
		int given = service.findNextAuthorID();
		int expected = 4;
		assertEquals(expected, given);
	}
	@Test
	public void testFindNextBookID() {
		////System.out.println("Executing Test findNextBookID.");
//		BookDAO.resetBooks(bookList);
		int given = service.findNextBookID();
		int expected = 4;
		assertEquals(expected, given);
	}
	@Test
	public void testFindNextPublisherID() {
		////System.out.println("Executing Test findNextPublisherID.");
//		PublisherDAO.resetPublishers(publisherList);
		int given = service.findNextPublisherID();
		int expected = 4;
		assertEquals(expected, given);
	}
	@Test
	public void testAuthorKeyCheckTrue() {
		////System.out.println("Executing Test authorKeyCheck.");
		boolean given = service.authorKeyCheck(2);
		boolean expected = true;
		assertEquals(expected, given);
	}
	@Test
	public void testAuthorKeyCheckFalse() {
		////System.out.println("Executing Test authorKeyCheck.");
		boolean given = service.authorKeyCheck(9);
		boolean expected = false;
		assertEquals(expected, given);
	}
	
	@Test
	public void testBookKeyCheckTrue() {
		////System.out.println("Executing Test bookKeyCheck.");
		boolean given = service.bookKeyCheck(2);
		boolean expected = true;
		assertEquals(expected, given);
	}
	@Test
	public void testBookKeyCheckFalse() {
		////System.out.println("Executing Test bookKeyCheck.");
		boolean given = service.bookKeyCheck(6);
		boolean expected = false;
		assertEquals(expected, given);
	}
	
	@Test
	public void testPublisherKeyCheckTrue() {

		////System.out.println("Executing Test publisherKeyCheck.");
//		PublisherDAO.resetPublishers(publisherList);
		boolean given = service.publisherKeyCheck(2);
		boolean expected = true;
		assertEquals(expected, given);
	}
	@Test
	public void testPublisherKeyCheckFalse() {

		////System.out.println("Executing Test publisherKeyCheck.");
//		PublisherDAO.resetPublishers(publisherList);
		boolean given = service.publisherKeyCheck(6);
		boolean expected = false;
		assertEquals(expected, given);
	}
	
	@Test
	public void testBookKeyAPCheckTrue()
	{
		boolean given = service.bookKeyCheckAP(3, 3);
		boolean expected = true;
		assertEquals(expected, given);
	}
	@Test
	public void testBookKeyAPCheckFalse()
	{
		boolean given = service.bookKeyCheckAP(4, 4);
		boolean expected = false;
		assertEquals(expected, given);
	}
	
	@Test
	public void testReadAuthorByIDTrue() {
		boolean given = service.readAuthorByID(2);
		boolean expected = true;
		assertEquals(expected, given);
	}
	@Test
	public void testReadAuthorByIDFalse() {
		boolean given = service.readAuthorByID(6);
		boolean expected = false;
		assertEquals(expected, given);
	}
	
	@Test
	public void testReadAuthorByNameTrue() {
		boolean given = service.readAuthorByName("Tim");
		boolean expected = true;
		assertEquals(expected, given);
	}
	@Test
	public void testReadAuthorByNameFalse() {
		boolean given = service.readAuthorByName("Johnson");
		boolean expected = false;
		assertEquals(expected, given);
	}
	
	
	@Test
	public void testReadBookByIDTrue() {
		boolean given = service.readBookByID(2);
		boolean expected = true;
		assertEquals(expected, given);
	}
	@Test
	public void testReadBookByIDFalse() {
		boolean given = service.readBookByID(6);
		boolean expected = false;
		assertEquals(expected, given);
	}
	
	@Test
	public void testReadBookByNameTrue() {
		boolean given = service.readBookByName("Bible");
		boolean expected = true;
		assertEquals(expected, given);
	}
	@Test
	public void testReadBookByNameFalse() {
		boolean given = service.readBookByName("Not the Bible");
		boolean expected = false;
		assertEquals(expected, given);
	}
	@Test
	public void testReadBookByAuthorIDTrue() {
		boolean given = service.readBookByAuthorID(3);
		boolean expected = true;
		assertEquals(expected, given);
	}
	@Test
	public void testReadBookByAuthorIDFalse() {
		boolean given = service.readBookByAuthorID(6);
		boolean expected = false;
		assertEquals(expected, given);
	}
	@Test
	public void testReadBookByPublisherIDTrue() {
		boolean given = service.readBookByPublisherID(3);
		boolean expected = true;
		assertEquals(expected, given);
	}
	
	@Test
	public void testReadBookByPublisherIDFalse() {
		boolean given = service.readBookByPublisherID(6);
		boolean expected = false;
		assertEquals(expected, given);
	}
	@Test
	public void testReadPublisherByIDTrue() {
		boolean given = service.readPublisherByID(2);
		boolean expected = true;
		assertEquals(expected, given);
	}
	@Test
	public void testReadPublisherByIDFalse() {
		boolean given = service.readPublisherByID(6);
		boolean expected = false;
		assertEquals(expected, given);
	}
	
	@Test
	public void testReadPublisherByNameTrue() {
		boolean given = service.readPublisherByName("Maria");
		boolean expected = true;
		assertEquals(expected, given);
	}
	@Test
	public void testReadPublisherByNameFalse() {
		boolean given = service.readPublisherByName("Johnson");
		boolean expected = false;
		assertEquals(expected, given);
	}
	@Test
	public void testReadPublisherByAddressTrue() {
		boolean given = service.readPublisherByAddress("Downtown");
		boolean expected = true;
		assertEquals(expected, given);
	}
	@Test
	public void testReadPublisherByAddressFalse() {
		boolean given = service.readPublisherByAddress("Johnson");
		boolean expected = false;
		assertEquals(expected, given);
	}
	@Test
	public void testUpdateAnAuthorTrue() {
		boolean given = service.updateAnAuthor(1, "Jacob");
		boolean expected = true;
		assertEquals(expected, given);
	}
	@Test
	public void testUpdateAnAuthorFalse() {
		boolean given = service.updateAnAuthor(6, "Bison");
		boolean expected = false;
		assertEquals(expected, given);
	}
	
	@Test
	public void testUpdateABookTrue() {
		boolean given = service.updateABook(2, "Book2", 2, 1);
		boolean expected = true;
		assertEquals(expected, given);
	}
	@Test
	public void testUpdateABookFalse() {
		boolean given = service.updateABook(10, "Book10", 2, 1);
		boolean expected = false;
		assertEquals(expected, given);
	}
	
	@Test
	public void testUpdateAPublisherTrue() {
		boolean given = service.updateAPublisher(3, "June Day", "Uptown");
		boolean expected = true;
		assertEquals(expected, given);
	}
	@Test
	public void testUpdateAPublisherFalse() {
		boolean given = service.updateAPublisher(5, "Publisher5", "Address5");
		boolean expected = false;
		assertEquals(expected, given);
	}
	
	@Test
	public void testDeleteAnAuthorTrue() {
		boolean given = service.deleteAnAuthor(1);
		boolean expected = true;
		assertEquals(expected, given);
	}
	
	@Test
	public void testDeleteAnAuthorFalse() {
		boolean given = service.deleteAnAuthor(10);
		boolean expected = false;
		assertEquals(expected, given);
	}
	@Test
	public void testDeleteABookTrue() {
		boolean given = service.deleteABook(1);
		boolean expected = true;
		assertEquals(expected, given);
	}
	
	@Test
	public void testDeleteAnBookFalse() {
		boolean given = service.deleteABook(10);
		boolean expected = false;
		assertEquals(expected, given);
	}
	@Test
	public void testDeleteAnPublisherTrue() {
		boolean given = service.deleteAPublisher(1);
		boolean expected = true;
		assertEquals(expected, given);
	}
	
	@Test
	public void testDeleteAnPublisherFalse() {
		boolean given = service.deleteAPublisher(10);
		boolean expected = false;
		assertEquals(expected, given);
	}
	
}
