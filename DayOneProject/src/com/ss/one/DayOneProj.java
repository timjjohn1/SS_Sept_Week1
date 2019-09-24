/**
 * 
 */
package com.ss.one;

/**
 * Program designed to output given output
 * @author sj
 *
 */
public class DayOneProj {

	/**
	 * Main Calls the four functions which will output the given designs
	 * @param args
	 */
	public static void main(String[] args) {
		//Function Calls(Output is embedded in the functions
		printOne();
		printTwo();
		printThree();
		printFour();

	}
	
	//Prints the first design by looping through the pyramid
	public static void printOne()
	{
		System.out.println("1)");
		for(int i = 1;i<5;i++)
		{
			for(int k = 0; k < i; k++)
			{
			System.out.print("*");
			}
			System.out.println();
		}
		System.out.println(".........");
		System.out.println();
	}
	
	//Prints the second design by nested loops
	public static void printTwo()
	{
		System.out.println("2)");
		System.out.println("..........");
		for(int i = 4;i>0;i--)
		{
			for(int k = i; k > 0; k--)
			{
			System.out.print("*");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	//Prints the third design by nested loops
	public static void printThree()
	{
		System.out.println("3)");
		int j = 1;
		int p = 5;
		for(int k = 0;k < 4; k++)
		{
			for(int i = p; i > 0; i--)
			{
				System.out.print(" ");
			}
			for(int l = 0; l < j; l++)
			{
				System.out.print("*");
			}
			p = p - 1;
			j = j + 2;
			System.out.println();
		}
		System.out.println("...........");
		System.out.println();
	}

	//Prints the fourth design by nested loops
	public static void printFour()
	{
		System.out.println("4)|");
		System.out.println("............");
		int j = 7;
		int p = 2;
		for(int k = 0;k < 4; k++)
		{
			for(int i = 0; i < p; i++)
			{
				System.out.print(" ");
			}
			for(int l = 0; l < j; l++)
			{
				System.out.print("*");
			}
			p = p + 1;
			j = j - 2;
			System.out.println();
		}
		System.out.println();
	}

}
