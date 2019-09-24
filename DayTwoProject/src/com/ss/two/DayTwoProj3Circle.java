package com.ss.two;

public class DayTwoProj3Circle implements DayTwoProj3Interface {

	public double rad = 2;

	//Constructor
	public DayTwoProj3Circle(double rad)
	{
		this.rad = rad;
	}
	
	@Override
	//Area calculation for a circle
	public double calculateArea() {
		return 3.14*(rad * rad);
	}

	@Override
	//Displays the area and the shape
	public void display() {
		System.out.println(this.calculateArea());
		System.out.println("O");
	}

}
