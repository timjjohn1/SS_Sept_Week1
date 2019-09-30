/**
 * 
 */
package com.ss.rmdbs;

import java.io.IOException;
import java.util.Scanner;

import com.ss.rmdbs.service.Service;

/**
 * @author tj
 *	RMDBS is the main driver for the lms
 *  This class runs the presentation layer
 *  Creates an instance of the service layer so that it can interact with the database
 */
public class RMDBS {


    private static Scanner scanner = new Scanner( System.in );
	static Service service = new Service();

	/**
	 * @param args
	 * @throws IOException 1
	 */
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		startDisplay();
	}
	
	public static void startDisplay()
	{
		boolean replay = false;
		while (replay == false) {
			//Menu ouput
			System.out.println("\n\nFor all selections, please type the associated number.");
			System.out.println("Choose what you wish to do:");
			System.out.println("1. Create");
			System.out.println("2. Read");
			System.out.println("3. Update");
			System.out.println("4. Delete");
			System.out.println("5. Exit");
			boolean checker = false;
		
			//Makes the user pick a valid input and then carries out that input command
			while (checker == false) {
				System.out.print("Enter your selection: ");
				String input = scanner.nextLine();
				System.out.print("\n\n");
				
				switch(input) {
					case "1":
						System.out.println("Create:");
						createChoice();
						checker = true;
						break;
					case "2":
						System.out.println("Read:");
						readChoice();
						checker = true;
						break;
					case "3":
						System.out.println("Update:");
						updateChoice();
						checker = true;
						break;
					case "4":
						System.out.println("Delete");
						deleteChoice();
						checker = true;
						break;
					case "5":
						System.out.println("Done.");
						replay = true;
						return;
					default:
						System.out.println("Invalid input.");
				}	
			}	
		}
	}
	//
	//Displays for user options
	//
	public static void displayCreate()
	{
		System.out.println("What would you like to create?");
		System.out.println("1. Author");
		System.out.println("2. Book");
		System.out.println("3. Publisher");
		System.out.println("0. -Go Back-");
	}
	public static void displayRead()
	{
		System.out.println("What would you like to read?");
		System.out.println("1. Author");
		System.out.println("2. Book");
		System.out.println("3. Publisher");
		System.out.println("0. -Go Back-");
	}
	public static void displayReadAuthorBy()
	{
		System.out.println("What would you like to filter by?");
		System.out.println("1. Author ID");
		System.out.println("2. Author Name");
		System.out.println("3. All");
		System.out.println("0. -Go Back-");
	}
	public static void displayReadBookBy()
	{
		System.out.println("What would you like to filter by?");
		System.out.println("1. Book ID");
		System.out.println("2. Book Name");
		System.out.println("3. Author ID");
		System.out.println("4. Publisher ID");
		System.out.println("5. All");
		System.out.println("0. -Go Back-");
	}
	public static void displayReadPublisherBy()
	{
		System.out.println("What would you like to filter by?");
		System.out.println("1. Publisher ID");
		System.out.println("2. Publisher Name");
		System.out.println("3. Publisher Address");
		System.out.println("4. All");
		System.out.println("0. -Go Back-");
	}
	public static void displayUpdate()
	{
		System.out.println("What would you like to update?");
		System.out.println("1. Author");
		System.out.println("2. Book");
		System.out.println("3. Publisher");
		System.out.println("0. -Go Back-");
	}
	public static void displayDelete(){
		System.out.println("What would you like to delete?");
		System.out.println("1. Author");
		System.out.println("2. Book");
		System.out.println("3. Publisher");
		System.out.println("0. -Go Back-");
	}
	
	
	
	//Simple menu that checks for a valid input
	//When valid input, it will call supporting functions for sub-menus
	public static void createChoice()
	{
		boolean checker = false;
		while (checker == false)
		{

			displayCreate();
			System.out.print("Enter your selection: ");
			String in = scanner.nextLine();
			System.out.print("\n\n\n");
			switch(in) {
				case "1": 
					createAuthor();
					checker = true;
					break;
				case "2":
					createBook();
					checker = true;
					break;
				case "3":
					createPublisher();
					checker = true;
					break;
				case "0":
					checker = true;
					return;
				default:
					System.out.println("Invalid Input.");
				}
		}
	}
	
	
	// Allows the user to pick what they want to read: 1 - Author, 2 - Book, 3 - Publisher
	//  Then is allows the user to choose what they want to filter by
	//		Author can be filtered by: ID or Name
	//		Book can be filtered by: ID, Book Name, Author ID, or Publisher ID
	//		Publisher can be filterd by: ID, Publisher Name, or Address
	//	It will then take it to the service layer functions to carry out the processing
	public static void readChoice() {
		boolean checker = false;
		while (checker == false)
		{
			displayRead();
			System.out.print("Enter your selection: ");
			String in = scanner.nextLine();
			//User choice switch statement
			switch(in) {
			//All cases are the same just with different function calls
				case "1":
					readAuthor();
					checker = true;
					break;
				case "2":
					readBook();
					checker = true;
					break;
				case "3":
					readPublisher();
					checker = true;
					break;
				case "0":
					checker = true;
					return;
				default:
					System.out.println("Invalid Input.");
				}
			System.out.println();
		}
	}
	
	//Menu for reading the author from table
	//Calls the readBy helper functions
	public static boolean readAuthor() {
		//Loops until valid input is chosen
		boolean valid = false;
		while(valid == false) {
			displayReadAuthorBy();
			String readBy = scanner.nextLine();
			switch(readBy) {
			case "1":
				readAuthorByID();
				valid = true;
				break;
			case "2":
				readAuthorByName();
				valid = true;
				break;
			case "3":
				readAuthorAll();
				valid = true;
				break;
			case "0":
				readChoice();
				valid = true;
				break;
			default:
				System.out.println("Invalid Input.");
			}
		}
		return valid;
	}

	//Reading the authors
	//Calls the service layer to get the records
	public static void readAuthorAll() {
		service.readAuthorAll();
	}
	public static boolean readAuthorByID() {
		boolean found = false;
		while(found == false) {
			System.out.print("Enter author ID: ");	
			int authorID = 0;
			while(!scanner.hasNextInt()) {
				System.out.println("Please enter a valid Integer.");
				System.out.print("ID of Author: ");
			    scanner.next();
			}
			
			authorID = scanner.nextInt();
			scanner.nextLine();
			if(service.readAuthorByID(authorID)){
				found = true;
			}
			else {

				System.out.println("Invalid Author ID.");
			}
		}
		return found;
	}
	
	public static boolean readAuthorByName() {
		boolean found = false;
		while(found == false) {
			System.out.print("Enter author name: ");
			String authorName = scanner.nextLine();
			if(service.readAuthorByName(authorName)){
				found = true;
			}
			else {
				System.out.println("Invalid Author Name.");
			}
		}
		return found;
	}
	
	//Menu for the reading a book
	//Calls the helper functions once chosen
	public static boolean readBook() {
		boolean valid = false;
		while(valid == false) {
			displayReadBookBy();
			String readBy = scanner.nextLine();
			switch (readBy) {
			case "1":
				readBookByBookID();
				valid = true;
				break;
			case "2":
				readBookByBookName();
				valid = true;
				break;
			case "3":
				readBookByAuthorID();
				valid = true;
				break;
			case "4":
				readBookByPublisherID();
				valid = true;
				break;
			case "5":
				readBookAll();
				valid = true;
				break;
			case "0":
				return true;
			default:
				System.out.println("Invalid Input.");
			}
			
		}
		return valid;
	}
	
	//
	//Reading the book by different criteria
	//
	//Calls the service layer to get the records
	public static void readBookAll() {
		service.readBookAll();
	}
	public static boolean readBookByBookName() {
		boolean found = false;
		while(found == false) {
			System.out.print("Enter Book name: ");
			String bookName = scanner.nextLine();
			if(service.readBookByName(bookName)){
				found = true;
			}
			else {

				System.out.println("Invalid Book Name.");
			}
		}
		return found;
	}
	public static boolean readBookByBookID() {
		boolean found = false;
		while(found == false) {
			System.out.print("Enter Book ID: ");

			int bookID = 0;
			while(!scanner.hasNextInt()) {
				System.out.println("Please enter a valid Integer.");
				System.out.print("ID of Book: ");
			    scanner.next();
			}
			bookID = scanner.nextInt();
			scanner.nextLine();
			if(service.readBookByID(bookID)){
				found = true;
			}
			else {

				System.out.println("Invalid Book ID.");
			}
		}
		return found;
	}
	public static boolean readBookByAuthorID() {
		boolean found = false;
		while(found == false) {
			System.out.print("Enter Author ID: ");
			int authorID = 0;
			while(!scanner.hasNextInt()) {
				System.out.println("Please enter a valid Integer.");
				System.out.print("ID of Author: ");
			    scanner.next();
			}
			authorID = scanner.nextInt();
			scanner.nextLine();
			if(service.readBookByAuthorID(authorID)){
				found = true;
			}
			else {
				System.out.println("Invalid Author ID.");
			}
		}
		return found;
	}
	
	//Reading the publisher by the ID
	//Calls the service layer to get the records
	public static boolean readBookByPublisherID() {
		boolean found = false;
		while(found == false) {
			System.out.print("Enter Publisher ID: ");
			int publisherID = 0;
			while(!scanner.hasNextInt()) {
				System.out.println("Please enter a valid Integer.");
				System.out.print("ID of Publisher: ");
			    scanner.next();
			}
			publisherID = scanner.nextInt();
			if(service.readBookByPublisherID(publisherID)){
				found = true;
			}
			else {

				System.out.println("Invalid Publisher ID.");
			}
		}
		return found;
	}
	
	//Choices for reading a publisher
	//Calls the helper functions once choice is picked
	public static boolean readPublisher() {
		boolean valid = false;
		while(valid == false) {
			displayReadPublisherBy();
			String readBy = scanner.nextLine();
			switch(readBy) {
			case "1":
				readPublisherByID();
				break;
			case "2":
				readPublisherByName();
				break;
			case "3":
				readPublisherByAddress();
				break;
			case "4":
				readAuthorAll();
				valid = true;
				break;
			case "0":
				return true;
			default:
				System.out.println("Invalid Input.");
			}
			
		}
		return valid;
	}
	//Read the Publishers according to different criteria
	//Calls the service layer functions
	public static void readPublisherAll() {
		service.readPublisherAll();
	}
	public static boolean readPublisherByID() {
		boolean found = false;
		while(found == false) {
			System.out.print("Enter publisher name: ");
			String authorName = scanner.nextLine();
			if(service.readPublisherByName(authorName)){
				found = true;
			}
			else {

				System.out.println("Invalid Publisher Name.");
			}
		}
		return found;
	}
	public static boolean readPublisherByName() {
		boolean found = false;
		while(found == false) {
			System.out.print("Enter publisher ID: ");
			int publisherID = 0;
			while(!scanner.hasNextInt()) {
				System.out.println("Please enter a valid Integer.");
				System.out.print("ID of Author: ");
			    scanner.next();
			}
			publisherID = scanner.nextInt();
			if(service.readPublisherByID(publisherID)){
				found = true;
			}
			else {

				System.out.println("Invalid Publisher ID.");
			}
		}
		return found;
	}
	public static boolean readPublisherByAddress() {
		boolean found = false;
		while(found == false) {
			System.out.print("Enter publisher address: ");
			String publisherAddress = scanner.nextLine();
			if(service.readPublisherByAddress(publisherAddress)){
				found = true;
			}
			else {

				System.out.println("Invalid Publisher Address.");
			}
		}
		return false;
	}
	
	//Allows the user to choose what they want to update, 1 - Author, 2 - Book, 3 - Publisher
	public static void updateChoice() {
		boolean checker = false;
		while (checker == false)
		{

			displayUpdate();
			System.out.print("Enter your selection: ");
			String in = scanner.nextLine();
			switch(in) {
				case "1": 
					updateAuthor();
					checker = true;
					break;
				case "2":
					updateBook();
					checker = true;
					break;
				case "3":
					updatePublisher();
					checker = true;
					break;
				case "0":
					checker = true;
					return;
				default:
					System.out.println("Invalid Input.");
				}
		}
	}
	
	//Menu for record deletion
	//Gets a valid choice from the user and sends it to supporting functions
	public static void deleteChoice() {
		boolean checker = false;
		while (checker == false)
		{
			displayDelete();
			System.out.print("Enter your selection: ");
			String in = scanner.nextLine();
			
			switch(in) {
				case "1": 
					deleteAuthor();
					checker = true;
					break;
				case "2":
					deleteBook();
					checker = true;
					break;
				case "3":
					deletePublisher();
					checker = true;
					break;
				case "0":
					checker = true;
					return;
				default:
					System.out.println("Invalid Input.");
				}
		}
	}
	
	//
	//Supporting functions for createChoice()
	//
	
	//Will get the name of the author they want to create and sends it to the service layer to be created
	//User will be able to create different Author records with the same name(they will have different ID's)
	public static void createAuthor() {
		boolean replay = false;

		String authorName = null;

		while (replay == false) {
			System.out.print("Name of Author: ");

			authorName = scanner.nextLine();

			authorName = authorName.replaceAll(",", "~");

			// Add and user input screens here
			if (authorName != "" && authorName != null) {
				replay = true;

			}
			else {

				System.out.println("Invalid Author Name.");
			}
		}
		service.createAnAuthor(authorName);
	}
	
	//Will get the name, author id, and the publisher id for the book entry and send them to the service layer to be created
	//Service layer will filter out which entries are valid and it will loop until a valid one is input
	public static void createBook() {
		System.out.print("Name of Book: ");

		String bookName = scanner.nextLine();

		bookName = bookName.replaceAll(",", "~");
		
		System.out.println(bookName);

		while (true) {

			System.out.print("Author ID: ");
			
			while(!scanner.hasNextInt()) {
				System.out.println("Please enter a valid Integer.");
				System.out.print("ID of Author: ");
			    scanner.next();
			}
			int a_id = scanner.nextInt();
			scanner.nextLine();
			
			System.out.print("Publisher ID: ");

			while(!scanner.hasNextInt()) {
				System.out.println("Please enter a valid Integer.");
				System.out.print("ID of Publisher: ");
			    scanner.next();
			}

			int p_id = scanner.nextInt();
			scanner.nextLine();

			if (service.bookKeyCheckAP(a_id, p_id) == true) {

				service.createABook(bookName, a_id, p_id);
				return;
			}

			System.out.println("Invalid combination of Author and Publisher ID.");
		}
	}
	
	//Will get the inputed publisher name and address and send it to the service layer to be processed
	public static void createPublisher() {
		System.out.print("Name of Publisher: ");

		String publisherName = scanner.nextLine();
		
		
		publisherName = publisherName.replaceAll(",", "~");

		System.out.print("Publishers Address: ");

		String publisherAddress = scanner.nextLine();
		
		publisherAddress = publisherAddress.replaceAll(",", "~");

		service.createAPublisher(publisherName, publisherAddress);
	}
	
	//
	//Supporting functions for updateChoice()
	//
	
	//Gets the ID of the author that wants to be changed from the user
	//Gets input until a valid ID is entered and then gets the new record information and sends it to the service layer
	public static void updateAuthor() {
		boolean replay = true;

		int authorID = 0;
		String authorName = null;

		while (replay == true) {

			System.out.print("ID of Author: ");
			
			while(!scanner.hasNextInt()) {
				System.out.println("Please enter a valid Integer.");
				System.out.print("ID of Author: ");
			    scanner.next();
			}
			authorID = scanner.nextInt();

			// Add and user input screens here
			if (service.authorKeyCheck(authorID)) {
				replay = false;
			}
			else {

				System.out.println("Invalid Author ID.");
			}
		}
		replay = true;
		while (replay == true) {
			System.out.print("New name of Author: ");
			
			scanner.nextLine();
			authorName = scanner.nextLine();

			authorName = authorName.replaceAll(",", "~");
			
			
			if(authorName != "" && authorName != null) {
				replay = false;
			}
			else {

				System.out.println("Author not found.");
			}
		}
		service.updateAnAuthor(authorID, authorName);
	}

	//Gets the ID for the book that wants to be changed from the user
	//Gets input until a valid ID is entered and then gets the new record information and sends it to service layer
	public static void updateBook() {
		boolean replay = true;
		boolean replayName = true;
		boolean replayAID = true;
		boolean replayPID = true;

		int bookID = -1;
		String bookName = null;
		int authorID = -1;
		int publisherID = -1;

		while (replay == true) {
			System.out.print("ID of Book: ");

			while(!scanner.hasNextInt()) {
				System.out.println("Please enter a valid Integer.");
				System.out.print("ID of Book: ");
			    scanner.next();
			}
			bookID = scanner.nextInt();
			scanner.nextLine();

			// Add and user input screens here
			if (service.authorKeyCheck(bookID)) {
				replay = false;
			}
			else {

				System.out.println("Book not found.");
			}
		}
		replayName = true;
		while (replayName == true && replayAID == true && replayPID == true) {
			System.out.print("New name of Book: ");
			
			bookName = scanner.nextLine();

			bookName = bookName.replaceAll(",", "~");
			
			if(bookName != "" && bookName != null) {

				replayName = false;
			}
			else {

				System.out.println("Invalid Book Name.");
			}
			
			System.out.print("New ID of Author: ");
			
			while(!scanner.hasNextInt()) {
				System.out.println("Please enter a valid Integer.");
				System.out.print("New ID of Author: ");
			    scanner.next();
			}
			authorID = scanner.nextInt();
			scanner.nextLine();
			
			if(service.authorKeyCheck(authorID)) {
				replayAID = false;
			}
			else {
				System.out.println("Author not found.");
			}
			
			
			System.out.print("New ID of Publisher: ");
			while(!scanner.hasNextInt()) {
				System.out.println("Please enter a valid Integer.");
				System.out.print("New ID of Publisher: ");
			    scanner.next();
			}
			publisherID = scanner.nextInt();
			scanner.nextLine();
			if(service.publisherKeyCheck(publisherID)) {
				replayPID = false;
			}
			else {

				System.out.println("Publisher not found.");
			}
			
			
		}
		service.updateABook(bookID, bookName, authorID, publisherID);
	}
	

	//Gets the ID of the publisher that wants to be changed from the user
	//Gets input until a valid ID is entered and then gets the new record information and sends it to the service layer
	public static void updatePublisher() {
		boolean replay = true;

		int publisherID = 0;
		String publisherName = null;
		String publisherAddress = null;

		while (replay == true) {
			System.out.print("ID of Publisher: ");

			while(!scanner.hasNextInt()) {
				System.out.println("Please enter a valid Integer.");
				System.out.print("ID of Publisher: ");
			    scanner.next();
			}
			publisherID = scanner.nextInt();
			scanner.nextLine();
			
			// Add and user input screens here
			if (service.publisherKeyCheck(publisherID)) {
				replay = false;
			}
			else {
				System.out.println("Publisher not found.");
			}
		}
		replay = true;
		while (replay == true) {
			System.out.print("New name of publisher: ");
			
			publisherName = scanner.nextLine();

			publisherName = publisherName.replaceAll(",", "~");
			
			if(publisherName != "" && publisherName != null) {
				replay = false;
			}
			else {
				System.out.println("Invalid Publisher Name.");
			}
			
		}
		replay = true;
		while (replay == true) {
			System.out.print("New address of publisher: ");
			
			publisherAddress = scanner.nextLine();

			publisherAddress = publisherAddress.replaceAll(",", "~");
			
			if(publisherAddress != "" && publisherAddress != null) {
				replay = false;
			}
			else {

				System.out.println("Invalid Publisher Address.");
			}
			
		}
		service.updateAPublisher(publisherID, publisherName, publisherAddress);
	}
	
	//
	//deleteChoice() supporting functions
	//
	public static void deleteAuthor() {
		boolean replay = false;

		int authorID = 0;

		while (replay == false) {
			System.out.print("ID of Author: ");

			while(!scanner.hasNextInt()) {
				System.out.println("Please enter a valid Integer.");
				System.out.print("ID of Author: ");
			    scanner.next();
			}
			authorID = scanner.nextInt();
			scanner.nextLine();

			// Add and user input screens here
			if (service.authorKeyCheck(authorID)) {
				replay = true;
			}
			else {

				System.out.println("Invalid Author ID.");
			}
		}
		service.deleteAnAuthor(authorID);
	}
	public static void deleteBook() {
		boolean replay = false;

		int bookID = 0;

		while (replay == false) {
			System.out.print("ID of book: ");

			while(!scanner.hasNextInt()) {
				System.out.println("Please enter a valid Integer.");
				System.out.print("ID of Book: ");
			    scanner.next();
			}
			bookID = scanner.nextInt();
			scanner.nextLine();

			// Add and user input screens here
			if (service.bookKeyCheck(bookID)) {
				replay = true;
			}
			else {

				System.out.println("Invalid Book ID.");
			}
		}
		service.deleteABook(bookID);
	}
	public static void deletePublisher() {
		boolean replay = false;

		int publisherID = 0;

		while (replay == false) {
			System.out.print("ID of publisher: ");

			while(!scanner.hasNextInt()) {
				System.out.println("Please enter a valid Integer.");
				System.out.print("ID of Publisher: ");
			    scanner.next();
			}
			publisherID = scanner.nextInt();
			scanner.nextLine();

			// Add and user input screens here
			if (service.publisherKeyCheck(publisherID)) {
				replay = true;
			}
			else {

				System.out.println("Invalid Publisher ID.");
			}
		}
		service.deleteAPublisher(publisherID);
	
	}
	
}
