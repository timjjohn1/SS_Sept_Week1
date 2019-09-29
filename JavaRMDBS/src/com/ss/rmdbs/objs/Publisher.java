/**
 * 
 */
package com.ss.rmdbs.objs;

/**
 * @author sj
 *
 */
public class Publisher {
	
	public int P_id;
	public String P_name;
	public String P_address;
	
	public Publisher()
	{
		P_id = 0;
		P_name = "";
		P_address = "";
	}
	
	public Publisher(int P_id, String P_name, String P_address)
	{
		this.P_id = P_id;
		this.P_name = P_name;
		this.P_address = P_address;
	}
	

	
	//Getters
	public int getID()
	{
		return this.P_id;
	}
	public String getName()
	{
		return this.P_name;
	}
	public String getAddress()
	{
		return this.P_address;
	}
	
	//Setters
	public void setID(int P_id)
	{
		this.P_id = P_id;
	}
	public void setName(String P_name)
	{
		this.P_name = P_name;
	}
	public void setAddress(String P_address)
	{
		this.P_address = P_address;
	}
	
	
}
