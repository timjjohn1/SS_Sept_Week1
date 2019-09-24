package com.ss.two;

public class DayTwoProj3Triangle implements DayTwoProj3Interface {

	public double height = 0;
	public double width = 0;
	
	//Constructor
	public DayTwoProj3Triangle(double height, double width)
	{
		this.height = height;
		this.width = width;
	}
	
	@Override
	//Calculates the Area of the triangle
	public double calculateArea() {
		return .5 * width*height;
	}

	@Override
	//Displays the area and the shape
	public void display() {
		System.out.println(this.calculateArea());
		System.out.println("    /\\   ");
		System.out.println("   /  \\   ");
		System.out.println("  /    \\  ");
		System.out.println(" /      \\ ");
		System.out.println("/________\\");
	}

}
