package com.ss.rmdbs.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.ss.rmdbs.dao.PublisherDAO;
import com.ss.rmdbs.objs.Publisher;


public class PublisherDAOTest {

	private PublisherDAO publisherDAO;
	//private List<Publisher> readPublishers;
	private List<Publisher> madePublishers;
	
	private Publisher publisher1;
	private Publisher publisher2;
	private Publisher publisher3;

	@Before
	public void uploadPublishers() throws IOException{
		
		publisherDAO = new PublisherDAO();
		//System.out.println("Executing Before.");
		madePublishers = new ArrayList<Publisher>();
		publisher1 = new Publisher(1, "Sarah", "123 Addr");
		publisher2 = new Publisher(2, "Maria", "321 Rdda");
		publisher3 = new Publisher(3, "Jane Doe", "Downtown");

		madePublishers.add(publisher1);
		madePublishers.add(publisher2);
		madePublishers.add(publisher3);
		

		//readPublishers = PublisherDAO.readPublishers();

		//System.out.println("Finalizing Before.");
	}

	@Test
	public void testConvertToCSV() {
		//System.out.println("Executing test CSV.");
		StringBuilder csv = publisherDAO.convertPublishersCSV(madePublishers);
		String str = ("1,Sarah,123 Addr\n"
				+ "2,Maria,321 Rdda\n"
				+ "3,Jane Doe,Downtown\n");

//		//System.out.println("csv:" + csv);
//		//System.out.println("str:" + str);
		assertEquals(str, csv.toString());
	}

	@Test
	public void testPublisherSize() throws NullPointerException{
		int given = madePublishers.size();
		int expected = 3;
		assertEquals(given, expected);
	}
	@Test
	public void testPublisherFileCheckCreate() {
		File file = new File("Publishers.csv");
		file.delete();
		boolean given = publisherDAO.publisherFileCheck();
		boolean expected = true;
		assertEquals(given,expected);
	}
	
	@Test
	public void testPublisherFileCheck() {
		publisherDAO.resetPublishers(madePublishers);
		boolean given = publisherDAO.publisherFileCheck();
		boolean expected = false;
		assertEquals(given,expected);
	}
}

