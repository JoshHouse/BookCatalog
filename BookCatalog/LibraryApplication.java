package BookCatalog;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.ListIterator;
/**
 * @author joshuahouse
 * Library Application that runs the menu that the user interacts with
 */
public class LibraryApplication {
	public static void main(String args[]) {
		Boolean added = false, removed = false, updated = false; //To be updated when add, remove, and update functions are preformed
		String inputFile = "assg6_catalog.txt"; // Input File
		String ISBN, title, author, publisher; // Variables to hold values when moving between lists and catalogs
		int publishingYear; // Variable to hold values when being moved between lists and catalogs
		BookCatalog catalog = new BookCatalog(); //Initializing catalog as a bookCatalog
		catalog.loadData(inputFile); //Uses load data method to get the catalog from the input file
		int choice = -1; // Initilizing choice to negative 1 because a choice has not been made but it still needs to satisfy the while loop
		Scanner keyboard = new Scanner(System.in);
		// While loop to continuously run the menu
		while (choice != 9) {
			System.out.println("1) Display all the books in the catalog");
			System.out.println("2) Search for book");
			System.out.println("3) Add a new book");
			System.out.println("4) Update an Existing book");
			System.out.println("5) Remove a book");
			System.out.println("6) Display books by a publisher");
			System.out.println("7) Sort all the books based on title");
			System.out.println("8) Save data");
			System.out.println("9) Exit");
			choice = keyboard.nextInt();
			keyboard.nextLine();
			// Switch statement to register choice
			switch(choice) {
				// Display all the books in the catalog
				case 1: 
					catalog.displayCatalog();
				break;
				// Search for book
				case 2:
					title = "";
					System.out.println("Enter the title of the book: ");
					title = keyboard.nextLine();
					if(catalog.searchForBook(title) != null) {
						System.out.println(catalog.searchForBook(title));
					} else {
						System.out.println("A book by that title is not in this catalog.");
					}
				break;
				// Add a new book
				case 3:
					ISBN = "";
					title = "";
					author = "";
					publisher = "";
					publishingYear = 0;
					System.out.println("Enter the ISBN number:");
					ISBN = keyboard.nextLine();
					System.out.println("Enter the title:");
					title = keyboard.nextLine();
					System.out.println("Enter the author:");
					author = keyboard.nextLine();
					System.out.println("Enter the publisher:");
					publisher = keyboard.nextLine();
					System.out.println("Enter the publishing year:");
					publishingYear = keyboard.nextInt();
					keyboard.nextLine();
					added = catalog.addBook(ISBN, title, author, publisher, publishingYear);
				break;
				// Update an Existing book
				case 4:
					title = "";
					System.out.println("Enter the title of the book you would like to update: ");
					title = keyboard.nextLine();
					int subMenuChoice = -1;
					// checks to see if the book is in the catalog before running menu
					if (catalog.searchForBook(title) != null) {
						// While loop to run sub menu until 0 is picked
						while (subMenuChoice != 0) {
							Book tempBook = catalog.searchForBook(title);
							System.out.println("\t 0) Return to Main Menu");
							System.out.println("\t 1) ISBN");
							System.out.println("\t 2) Author");
							System.out.println("\t 3) Publisher");
							System.out.println("\t 4) Publishing Year");
							subMenuChoice = keyboard.nextInt();
							keyboard.nextLine();
							switch (subMenuChoice) {
								// Return to Main Menu
								case 0:
								
								break;
								// Update ISBN
								case 1:
									ISBN = "";
									System.out.println("Enter the updated ISBN number: ");
									ISBN = keyboard.nextLine();
									updated = catalog.updateBook(ISBN, tempBook.getTitle(), tempBook.getAuthor(), tempBook.getPublisher(), tempBook.getPublishingYear());
								break;
								// Update Author
								case 2:
									author = "";
									System.out.println("Enter the updated author: ");
									author = keyboard.nextLine();
									updated = catalog.updateBook(tempBook.getISBN(), tempBook.getTitle(), author, tempBook.getPublisher(), tempBook.getPublishingYear());
									break;
								// Update Publisher
								case 3:
									publisher = "";
									System.out.println("Enter the updated publisher: ");
									publisher = keyboard.nextLine();
									updated = catalog.updateBook(tempBook.getISBN(), tempBook.getTitle(), tempBook.getAuthor(), publisher, tempBook.getPublishingYear());
									break;
								// Update Publishing Year
								case 4:
									publishingYear = 0;
									System.out.println("Enter the updated publishing year: ");
									publishingYear = keyboard.nextInt();
									keyboard.nextLine();
									updated = catalog.updateBook(tempBook.getISBN(), tempBook.getTitle(), tempBook.getAuthor(), tempBook.getPublisher(), publishingYear);
									break;
								default:
									System.out.println("Please enter a valid choice from the submenu");
							}
						}
					}
				break;
				// Remove a book
				case 5:
					title = "";
					System.out.println("Enter the title of the book you would like to remove: ");
					title = keyboard.nextLine();
					removed = catalog.removeBook(title);
				break;
				// Display books by a publisher
				case 6:
					publisher = "";
					ArrayList<Book> publisherBooks = new ArrayList<Book>(); // Creates an Array List to hold the publisher's books
					System.out.println("Enter the publisher's name:");
					publisher= keyboard.nextLine();
					publisherBooks = catalog.getBooksByPublisher(publisher);
					ListIterator<Book> iter = publisherBooks.listIterator();
				    
					if(iter.hasNext())
					{
						// Iterates through the array list and prints the books
						while (iter.hasNext()) {
							System.out.println(iter.next());
							System.out.println("");
						}
					} 
					else 
					{
						System.out.println("This catalog doesnt have any books by that publisher.");
					}
					
				break;
				// Sort all the books based on title
				case 7:			
					catalog.sortByTitle();
				break;
				// Save data
				case 8:
					// Checks to see if any changes have been made and if so it saves
					if (added == true || updated == true || removed == true) {
						try {
							catalog.save();
						}
						catch (Exception e)
						{
							System.out.println("Could not write to file.");
						}
						added = false;
						updated = false;
						removed = false;
					}
				break;
				// Exit
				case 9:
					// Checks to see if any changes have been made to the document without saving and if so saves before closing
					if (added == true || updated == true || removed == true) {
						try {
							catalog.save();
						}
						catch (Exception e)
						{
							System.out.println("Could not write to file.");
						}
						added = false;
						updated = false;
						removed = false;
					}
				break;
				// If any choice other than 1-9 is made, it prints a message stating that you have to choose 1-9
				default:
					System.out.println("Invalid choice. Please select numbers 1 through 9 from the menu.");
			
			}
			// Asks the user to press enter to continue after any of the switch statements above are run
			System.out.println("Press enter to continue");
			keyboard.nextLine();
		} // this is the end of the while loop
		keyboard.close();
		System.exit(0);
	}

}