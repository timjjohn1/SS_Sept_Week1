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

import com.ss.rmdbs.objs.Author;

/**
 * @author sj
 *
 */
public class AuthorDAO {

	public AuthorDAO() {

    	authorFileCheck();

    	setAuthors(readAuthors());
	}
	
    private static List<Author> authors;

	/**
	 * @return the authors
	 */
	public static List<Author> getAuthors() {
		return authors;
	}

	/**
	 * @param authors the authors to set
	 */
	public static void setAuthors(List<Author> authors) {
		AuthorDAO.authors = authors;
	}
	
    public static List<Author> readAuthors(){
    	List<Author> authorList = new ArrayList<Author>();
    	
		File file = new File("Authors.csv");
		
		int id;
		String name;

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

					Author author = new Author(id, name);
					
					authorList.add(author);
					
				} catch (ArrayIndexOutOfBoundsException f) {
					// f.printStackTrace();
				}
			}

	    	inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    	return authorList;
    }
    
    public static StringBuilder convertAuthorsCSV(List<Author> authorList) {
    	StringBuilder csvString = new StringBuilder();
    	
    	if(authorList != null) {
    		for(Author author : authorList) {
    			csvString.append(author.getID() + "," + author.getName() + "\n");
    		}
    	}
    	return csvString;
    }
    
    public static boolean authorFileCheck()
	{
		try {
			File file = new File("Authors.csv");
	        boolean fvar = file.createNewFile();
		    if (fvar){
		         System.out.println("File has been created successfully.");
		         
		    }
		    else{
		         System.out.println("File already present.");
		         setAuthors(readAuthors());
		         return false;
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
		
		return true;
	}
    
	public static void resetAuthors(List<Author> authors)
	{

		//System.out.println(convertAuthorsCSV(authors).toString());
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
		String fields = "A_id,A_name\n";
		pw.write(fields);
		pw.write(convertAuthorsCSV(authors).toString());
		//System.out.println(convertAuthorsCSV(authors).toString());
			
		pw.close();
	}
	
	public void writeAuthor(Author author){
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
		
		setAuthors(readAuthors());
		
		System.out.println("Author has been added to the database.");
	}
}
