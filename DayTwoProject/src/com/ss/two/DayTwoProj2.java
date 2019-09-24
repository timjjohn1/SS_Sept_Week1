/**
 * 
 */
package com.ss.two;


/**
 * @author sj
 *
 */
public class DayTwoProj2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[][] values = {{1,2},{3,4}};
		
		double max = 0;
		int cord1 = 0;
		int cord2 = 0;
		int i = 0;
		int k = 0;
		for (double[] set : values){
			k = 0;
			for(double elem : set) {
				if (elem > max) {
					max = elem;
					cord1 = i;
					cord2 = k;
				}
				k++;
			}
			i++;
		}
		System.out.println("The max value is: " + max);
		System.out.println("The position is: (" + cord1 + ", "+ cord2 + ")");
	}

}
