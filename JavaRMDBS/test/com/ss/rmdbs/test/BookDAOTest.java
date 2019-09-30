/**
 * 
 */
package com.ss.rmdbs.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.ss.rmdbs.dao.BookDAO;
import com.ss.rmdbs.objs.Book;

/**
 * @book sj
 *
 */
public class BookDAOTest {

	private List<Book> madeBooks;
	
	private Book book1;
	private Book book2;
	private Book book3;

	@Before
	public void uploadBooks() throws IOException{

		//System.out.println("Executing Before.");
		madeBooks = new ArrayList<Book>();
		book1 = new Book(1, "Bible", 1, 2);
		book2 = new Book(2, "To Kill a Mockingbird", 2, 1);
		book3 = new Book(3, "Into the Wild", 3, 3);

		madeBooks.add(book1);
		madeBooks.add(book2);
		madeBooks.add(book3);

		//System.out.println("Finalizing Before.");
	}

	@Test
	public void testConvertToCSV() {
		//System.out.println("Executing test CSV.");
		StringBuilder csv = BookDAO.convertBooksCSV(madeBooks);
		String str = ("1,Bible,1,2\n"
				+ "2,To Kill a Mockingbird,2,1\n"
				+ "3,Into the Wild,3,3\n");

//		//System.out.println("csv:" + csv);
//		//System.out.println("str:" + str);
		assertEquals(str, csv.toString());
	}

	@Test
	public void testBookSize() throws NullPointerException{
		int given = madeBooks.size();
		int expected = 3;
		assertEquals(given, expected);
	}
	@Test
	public void testBookFileCheckCreate() {
		File file = new File("Books.csv");
		file.delete();
		boolean given = BookDAO.bookFileCheck();
		boolean expected = true;
		assertEquals(given,expected);
	}
	
	@Test
	public void testBookFileCheck() {
		BookDAO.resetBooks(madeBooks);
		boolean given = BookDAO.bookFileCheck();
		boolean expected = false;
		assertEquals(given,expected);
	}
}
