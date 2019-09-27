/**
 * 
 */
package com.ss.rmdbs;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author sj
 *
 */
public class RMDBS {


    private static Scanner scanner = new Scanner( System.in );
	static Service service = new Service();
//    private static int Author_id = 1;
//    private static int Book_id = 1;
//    private static int Publisher_id = 1;
//    private static String[][] authorTable;
//    private static String[][] bookTable;
//    private static String[][] publisherTable;
	/**
	 * @param args
	 * @throws IOException 1
	 */
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		boolean replay = false;
		while (replay == false) {
			//Menu ouput
			System.out.println("For all selections, please type the associated number.");
			System.out.println("Choose what you wish to do:");
			System.out.println("1. Create");
			System.out.println("2. Read");
			System.out.println("3. Update");
			System.out.println("4. Delete");
			System.out.println("5. Exit");
			boolean checker = false;
		
			//Makes the user pick a valid input and then carries out that input command
			while (checker == false) {
				String input = scanner.nextLine();
				switch(input) {
					case "1":
						System.out.println("Create:");
						displayCreate();
						createChoice();
						checker = true;
						break;
					case "2":
						System.out.println("Read:");
						displayRead();
						readChoice();
						checker = true;
						break;
					case "3":
						System.out.println("Update:");
						displayUpdate();
						updateChoice();
						checker = true;
						break;
					case "4":
						System.out.println("Delete");
						displayDelete();
						deleteChoice();
						checker = true;
						break;
					case "5":
						System.out.println("Done.");
						replay = true;
						break;
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
	}
	public static void displayRead()
	{
		System.out.println("What would you like to read?");
		System.out.println("1. Author");
		System.out.println("2. Book");
		System.out.println("3. Publisher");
	}
	public static void displayReadAuthorBy()
	{
		System.out.println("What would you like to filter by?");
		System.out.println("1. Author ID");
		System.out.println("2. Author name");
	}
	public static void displayReadBookBy()
	{
		System.out.println("What would you like to filter by?");
		System.out.println("1. Book ID");
		System.out.println("2. Book name");
		System.out.println("3. Author ID");
		System.out.println("4. Publisher ID");
	}
	public static void displayReadPublisherBy()
	{
		System.out.println("What would you like to filter by?");
		System.out.println("1. Publisher ID");
		System.out.println("2. Publisher name");
		System.out.println("3. Publisher Address");
	}
	public static void displayUpdate()
	{
		System.out.println("What would you like to update?");
		System.out.println("1. Author");
		System.out.println("2. Book");
		System.out.println("3. Publisher");
	}
	public static void displayDelete(){
		System.out.println("What would you like to delete?");
		System.out.println("1. Author");
		System.out.println("2. Book");
		System.out.println("3. Publisher");
	}
	
	
	
	//Simple menu that checks for a valid input
	//When valid input, it will call supporting functions for sub-menus
	public static void createChoice()
	{
		boolean checker = false;
		while (checker == false)
		{
			String in = scanner.nextLine();
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
			boolean found = false;
			String in = scanner.nextLine();
			//User choice switch statement
			switch(in) {
			//All cases are the same just with different function calls
				case "1": 
					boolean valid = false;
					//Loops until valid input is chosen
					while(valid == false) {
						displayReadAuthorBy();
						String readBy = scanner.nextLine();
						if(readBy.compareTo("2") == 0) {
							while(found == false) {
								System.out.print("Enter author name: ");
								String authorName = scanner.nextLine();
								if(Service.readAuthorByName(authorName)){
									found = true;
								}
							}
							valid = true;
						}
						else if (readBy.compareTo("1") == 0) {
							while(found == false) {
								System.out.print("Enter author ID: ");
								String authorID = scanner.nextLine();
								if(Service.readAuthorByID(authorID)){
									found = true;
								}
							}
							valid = true;
						}
					}
					checker = true;
					break;
					
				case "2":
					boolean valid2 = false;
					while(valid2 == false) {
						displayReadBookBy();
						String readBy = scanner.nextLine();
						if(readBy.compareTo("2") == 0) {
							while(found == false) {
								System.out.print("Enter Book name: ");
								String bookName = scanner.nextLine();
								if(Service.readBookByName(bookName)){
									found = true;
								}
							}
							valid2 = true;
						}
						else if (readBy.compareTo("1") == 0) {
							while(found == false) {
								System.out.print("Enter Book ID: ");
								String bookID = scanner.nextLine();
								if(Service.readBookByID(bookID)){
									found = true;
								}
							}
							valid2 = true;
						}
						else if (readBy.compareTo("3") == 0) {
							while(found == false) {
								System.out.print("Enter Author ID: ");
								String bookID = scanner.nextLine();
								if(Service.readBookByID(bookID)){
									found = true;
								}
							}
							valid2 = true;
						}
						else if (readBy.compareTo("4") == 0) {
							while(found == false) {
								System.out.print("Enter Publisher ID: ");
								String publisherID = scanner.nextLine();
								if(Service.readBookByPublisherID(publisherID)){
									found = true;
								}
							}
							valid2 = true;
						}
					}
					checker = true;
					break;
				case "3":
					boolean valid3 = false;
					while(valid3 == false) {
						displayReadPublisherBy();
						String readBy = scanner.nextLine();
						if(readBy.compareTo("2") == 0) {
							while(found == false) {
								System.out.print("Enter publisher name: ");
								String authorName = scanner.nextLine();
								if(Service.readPublisherByName(authorName)){
									found = true;
								}
							}
							valid3 = true;
						}
						else if (readBy.compareTo("1") == 0) {
							while(found == false) {
								System.out.print("Enter publisher ID: ");
								String authorID = scanner.nextLine();
								if(Service.readPublisherByID(authorID)){
									found = true;
								}
							}
							valid3 = true;
						}
						else if (readBy.compareTo("3") == 0) {
							while(found == false) {
								System.out.print("Enter publisher address: ");
								String authorID = scanner.nextLine();
								if(Service.readPublisherByAddress(authorID)){
									found = true;
								}
							}
							valid3 = true;
						}
					}
					checker = true;
					break;
				default:
					System.out.println("Invalid Input.");
				}
			System.out.println();
		}
	}
	
	//Allows the user to choose what they want to update, 1 - Author, 2 - Book, 3 - Publisher
	public static void updateChoice() {
		boolean checker = false;
		while (checker == false)
		{
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
			String in = scanner.nextLine();
			switch(in) {
				case "1": 
					deleteAuthor();
					checker = true;
					break;
				case "2":
					//deleteBook();
					checker = true;
					break;
				case "3":
					//deletePublisher();
					checker = true;
					break;
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
			System.out.println("Name of Author?");

			authorName = scanner.nextLine();

			// Add and user input screens here
			if (authorName != "" && authorName != null) {
				replay = true;
			}
		}
		service.createAnAuthor(authorName);
	}
	
	//Will get the name, author id, and the publisher id for the book entry and send them to the service layer to be created
	//Service layer will filter out which entries are valid and it will loop until a valid one is input
	public static void createBook() {
		System.out.println("Name of Book?");

		String bookName = scanner.nextLine();

		while (true) {

			System.out.println("Author ID?");

			String a_id = scanner.nextLine();

			System.out.println("Publisher ID?");

			String p_id = scanner.nextLine();

			if (service.bookKeyCheckAP(Integer.parseInt(a_id), Integer.parseInt(p_id)) == true) {

				service.createABook(bookName, a_id, p_id);
				return;
			}
		}
	}
	
	//Will get the inputed publisher name and address and send it to the service layer to be processed
	public static void createPublisher() {
		System.out.println("Name of Publisher?");

		String publisherName = scanner.nextLine();

		System.out.println("Publishers Address?");

		String publisherAddress = scanner.nextLine();

		service.createAPublisher(publisherName, publisherAddress);
	}
	
	//
	//Supporting functions for updateChoice()
	//
	
	//Gets the ID of the author that wants to be changed from the user
	//Gets input until a valid ID is entered and then gets the new record information and sends it to the service layer
	public static void updateAuthor() {
		boolean replay = true;

		String authorID = null;
		String authorName = null;

		while (replay == true) {
			System.out.println("ID of Author?");

			authorID = scanner.nextLine();

			// Add and user input screens here
			if (service.authorKeyCheck(Integer.parseInt(authorID))) {
				replay = false;
			}
		}
		replay = true;
		while (replay == true) {
			System.out.println("New name of Author?");
			
			authorName = scanner.nextLine();
			
			if(authorName != "" && authorName != null) {
				replay = false;
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

		String bookID = null;
		String bookName = null;
		String authorID = null;
		String publisherID = null;

		while (replay == true) {
			System.out.println("ID of Book?");

			bookID = scanner.nextLine();

			// Add and user input screens here
			if (service.authorKeyCheck(Integer.parseInt(bookID))) {
				replay = false;
			}
		}
		replayName = true;
		while (replayName == true && replayAID == true && replayPID == true) {
			System.out.println("New name of Book?");
			
			bookName = scanner.nextLine();
			
			if(bookName != "" && bookName != null) {
				replayName = false;
			}
			System.out.println("New ID of Author?");
			
			authorID = scanner.nextLine();
			
			if(authorID != "" && authorID != null) {
				replayAID = false;
			}
			System.out.println("New ID of Publisher?");
			
			publisherID = scanner.nextLine();
			
			if(publisherID != "" && publisherID != null) {
				replayPID = false;
			}
			
			
		}
		service.updateABook(bookID, bookName, authorID, publisherID);
	}
	

	//Gets the ID of the publisher that wants to be changed from the user
	//Gets input until a valid ID is entered and then gets the new record information and sends it to the service layer
	public static void updatePublisher() {
		boolean replay = true;

		String publisherID = null;
		String publisherName = null;
		String publisherAddress = null;

		while (replay == true) {
			System.out.println("ID of Publisher?");

			publisherID = scanner.nextLine();

			// Add and user input screens here
			if (service.publisherKeyCheck(Integer.parseInt(publisherID))) {
				replay = false;
			}
		}
		replay = true;
		while (replay == true) {
			System.out.println("New name of publisher?");
			
			publisherName = scanner.nextLine();
			
			if(publisherName != "" && publisherName != null) {
				replay = false;
			}
			
		}
		replay = true;
		while (replay == true) {
			System.out.println("New address of publisher?");
			
			publisherAddress = scanner.nextLine();
			
			if(publisherAddress != "" && publisherAddress != null) {
				replay = false;
			}
			
		}
		service.updateAPublisher(publisherID, publisherName, publisherAddress);
	}
	
	//
	//deleteChoice() supporting functions
	//
	
	
	public static void deleteAuthor() {
		boolean replay = false;

		String authorID = null;

		while (replay == false) {
			System.out.println("ID of Author?");

			authorID = scanner.nextLine();

			// Add and user input screens here
			if (service.authorKeyCheck(Integer.parseInt(authorID))) {
				replay = true;
			}
		}
		service.deleteAnAuthor(authorID);
	}
	public void deleteBook() {
		boolean replay = false;

		String bookID = null;

		while (replay == false) {
			System.out.println("ID of book?");

			bookID = scanner.nextLine();

			// Add and user input screens here
			if (service.bookKeyCheck(Integer.parseInt(bookID))) {
				replay = true;
			}
		}
		service.deleteABook(bookID);
	}
	public void deletePublisher() {
		
	}
	
}
