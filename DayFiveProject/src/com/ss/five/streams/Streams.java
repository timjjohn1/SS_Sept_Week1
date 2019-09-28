/**
 * 
 */
package com.ss.five.streams;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sj
 *
 */
public class Streams {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		listStream();
	}

//	2.	Using Java 8 features write a method that returns a comma 
//	separated string based on a given list of integers. Each element should 
//	be preceded by the letter 'e' if the number is even, and preceded by the 
//	letter 'o' if the number is odd. For example, if the input list is (3,44), 
//	the output should be 'o3,e44'.
	public static void listStream(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(3);
		list.add(10);
		list.add(6);
		
		String msg = list.stream()
			.map(s->s % 2 == 0 ? "e" + s : "o" + s)
			.collect(Collectors.joining(","));
		
		System.out.println("The string is: " +msg);
		
		
		List<String> strings = new ArrayList<String>();
		strings.add("all");
		strings.add("Ack");
		strings.add("Pass");
		strings.add("add");
		
		strings = strings.stream()
			.filter(s->s.startsWith("a"))
			.filter(s->s.length()==3)
			.collect(Collectors.toList());
		
		System.out.println(strings);
	}
}
