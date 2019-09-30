/**
 * 
 */
package com.ss.rmdbs.objs;

/**
 * @author tj
 * Simple POJO for an author
 */
public class Author {
	public int A_id;
	public String A_name;
	
	public Author()
	{
		A_id = 0;
		A_name = "";
	}
	
	public Author(int Author_id, String A_name)
	{
		this.A_id = Author_id;
		this.A_name = A_name;
	}
	
	
	public void delete(int id) {

	}
	
	public int getID()
	{
		return this.A_id;
	}
	
	public String getName()
	{
		return this.A_name;
	}
	
	public void setID(int A_id)
	{
		this.A_id = A_id;
	}
	
	public void setName(String A_name)
	{
		this.A_name = A_name;
	}
}
