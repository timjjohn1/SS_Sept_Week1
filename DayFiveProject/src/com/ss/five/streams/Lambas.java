/**
 * 
 */
package com.ss.five.streams;

import java.util.ArrayList;
/**
 * @author sj
 *
 */
public class Lambas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String> strings = new ArrayList<String>();
		
		strings.add("Sushi");
		strings.add("Pizza");
		strings.add("Mac and Cheese");
		strings.add("Pie");
		
		
		
		strings.sort((s1,s2)->s1.length() - s2.length());
		System.out.println(strings.toString());
		strings.sort((s1,s2)->-(s1.length() - s2.length()));
		System.out.println(strings.toString());
		strings.sort((s1,s2)->s1.charAt(0) - s2.charAt(0));
		System.out.println(strings.toString());
		//strings.sort((String s1, String s2)->{ });
		strings.sort((s1, s2) -> 
	        { 
	            int checkForE = 0;
	            if(s1.contains("e") && !s2.contains("e")) {
	            		checkForE = -1;
	            } 
	            else if(s2.contains("e") && !s1.contains("e")) {
	            	checkForE = 1;
	            }
	            return(checkForE);
	        });
		System.out.println(strings.toString());
		
		ArrayList<String> newStrings = new ArrayList<String>();
		newStrings.add("Sushi");
		newStrings.add("Pizza");
		newStrings.add("Mac and Cheese");
		newStrings.add("Pie");
		
		//Using the method that I made
		newStrings.sort((s1,s2) -> sortByE(s1,s2));
		System.out.println(newStrings.toString());

	}
	
	public static int sortByE(String s1, String s2) {
        int checkForE = 0;
        if(s1.contains("e") && !s2.contains("e")) {
        		checkForE = -1;
        } 
        else if(s2.contains("e") && !s1.contains("e")) {
        	checkForE = 1;
        }
        return(checkForE);
	}
}
