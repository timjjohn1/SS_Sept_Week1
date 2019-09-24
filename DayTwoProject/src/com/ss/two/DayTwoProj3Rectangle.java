package com.ss.two;

public class DayTwoProj3Rectangle implements DayTwoProj3Interface {

	public double height = 0;
	public double width = 0;
	
	//Constructor
	public DayTwoProj3Rectangle(double height, double width)
	{
		this.height = height;
		this.width = 10;
	}

	@Override
	//Area calculation for a rectangle
	public double calculateArea() {
		return height * width;
	}

	@Override
	//Displays the area and the shape
	public void display() {
		System.out.println(this.calculateArea());
		System.out.println("_______");
		System.out.println("|     |");
		System.out.println("|     |");
		System.out.println("|     |");
		System.out.println("|_____|");
	}

}
