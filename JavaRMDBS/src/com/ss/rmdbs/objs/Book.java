/**
 * 
 */
package com.ss.rmdbs.objs;

/**
 * @author tj
 * Simple POJO for a book
 */
public class Book {
	public int B_id;
	public String B_name;
	public int B_author;
	public int B_publisher;
	
	public Book()
	{
		B_id = 0;
		B_name = "";
		B_author = 0;
		B_publisher = 0;
	}
	
	public Book(int B_id, String B_name, int B_author, int B_publisher)
	{
		this.B_id = B_id;
		this.B_name = B_name;
		this.B_author = B_author;
		this.B_publisher = B_publisher;
	}
	
	
	//Getters
	public int getID()
	{
		return this.B_id;
	}
	public String getName()
	{
		return this.B_name;
	}
	public int getAuthor()
	{
		return B_author;
	}
	public int getPublisher()
	{
		return B_publisher;
	}
	
	
	//Setters
	public void setID(int B_id)
	{
		this.B_id = B_id;
	}
	public void setName(String B_name)
	{
		this.B_name = B_name;
	}
	public void setAuthor(int B_author)
	{
		this.B_author = B_author;
	}
	public void setPublisher(int B_publisher)
	{
		this.B_publisher = B_publisher;
	}

}


