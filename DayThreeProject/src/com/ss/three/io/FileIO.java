/**
 * 
 */
package com.ss.three.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author sj
 *
 */
public class FileIO {
//	Write a Java program to get a list of all file/directory 
//	names under a given directory.•
//	Write a Java program to append text to an existing file.
//	•Write an example that counts the
//	number of times a particular character, such as e, appears in
//	a file. The character can be specified at the command line
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//Part 1 - gets all of the files in this directory
		File dir = new File("/Library/Smoothstack/Java/workspace/DayThreeProject");
		String[] files = dir.list();
		
		for(String file : files) {
			System.out.println(file);
		}
		
		
		//Part 2 - creating a file called hello.txt
		File file = createFile("hello.txt");

		String msg = "Test text to file";
		writeToFile(file, msg);
		
		//Part 3 - getting number of e's in the file I created
		int es = checkFileLines(file, 'e');
		System.out.println("\n\nThe number of 'e''s is: " + es);
	}
	
	public static File createFile(String fileName) {

		File file = new File(fileName);
		
		return file;
	}
	
	public static void writeToFile(File file, String text) throws IOException {
		FileWriter fw = new FileWriter(file);
		
		fw.append(text);
		fw.close();
		
	}
	
	public static int occurances(String line, char ch) {
		int count = 0;
		for(int i = 0; i < line.length(); i++) {
			if(line.charAt(i) == ch) {
				count++;
			}
		}
		return count;
	}
	
	public static int checkFileLines(File file, char ch) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		int total = 0;
		while(scanner.hasNext()) {
			total = total + occurances(scanner.nextLine(), ch);
		}
		scanner.close();
		return total;
	}
}
