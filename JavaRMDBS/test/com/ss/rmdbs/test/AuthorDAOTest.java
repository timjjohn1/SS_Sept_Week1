/**
 * 
 */
package com.ss.rmdbs.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ss.rmdbs.dao.AuthorDAO;
import com.ss.rmdbs.objs.Author;

/**
 * @author sj
 *
 */
public class AuthorDAOTest {
	//private List<Author> readAuthors;
	private List<Author> madeAuthors;
	private AuthorDAO authorDAO;
	
	private Author author1;
	private Author author2;
	private Author author3;

	@Before
	public void uploadAuthors() throws IOException{

		authorDAO = new AuthorDAO();
		//System.out.println("Executing Before.");
		madeAuthors = new ArrayList<Author>();
		author1 = new Author(1, "Tim");
		author2 = new Author(2, "Johnny");
		author3 = new Author(3, "Jessica");

		madeAuthors.add(author1);
		madeAuthors.add(author2);
		madeAuthors.add(author3);

		//readAuthors = AuthorDAO.readAuthors();

		//System.out.println("Finalizing Before.");
	}

	@Test
	public void testConvertToCSV() {
		//System.out.println("Executing test CSV.");
		StringBuilder csv = AuthorDAO.convertAuthorsCSV(madeAuthors);
		String str = ("1,Tim\n"
				+ "2,Johnny\n"
				+ "3,Jessica\n");
		////System.out.println("str:" + str);
		assertEquals(str, csv.toString());
	}

	@Test
	public void testAuthorSize() throws NullPointerException{
		int given = madeAuthors.size();
		int expected = 3;
		assertEquals(given, expected);
	}
	
	@Test
	public void testAuthorFileCheckCreate() {
		File file = new File("Authors.csv");
		file.delete();
		boolean given = authorDAO.authorFileCheck();
		boolean expected = true;
		assertEquals(given,expected);
	}
	
	@Test
	public void testAuthorFileCheck() {
		authorDAO.resetAuthors(madeAuthors);
		boolean given = authorDAO.authorFileCheck();
		boolean expected = false;
		assertEquals(given,expected);
	}
}
