/**
 * 
 */
package com.ss.two;

/**
 * @author sj
 *
 */
public class DayTwoProj1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double sum = 0;
		if(args.length > 0)
		{
			for(int i = 0; i < args.length; i++)
			{
				sum = sum + Double.parseDouble(args[i]);
			}
		}
		System.out.println(sum);
	}

}
