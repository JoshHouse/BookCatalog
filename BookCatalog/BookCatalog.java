package BookCatalog;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * 
 * @author Joshua House
 * Book catalog Class with methods used by Library application
 */
public class BookCatalog implements BookCatalogInterface {
	private ArrayList<Book> bookCatalog;	// Book catalog 
	/**
	 * Constructor for BookCatalog
	 */
	public BookCatalog()
	{
		this.bookCatalog = new ArrayList<Book>();
	}
	
	/**
	 * Loads the data from the input file to a book catalog
	 * @param inputFile
	 */
	public void loadData(String inputFile) {
		try {
		String ISBN, title, author, publisher;
		int publishingYear;
		File inFile = new File(inputFile);
		Scanner fileInput= new Scanner(inFile);;
		while (fileInput.hasNextLine())
		{
			ISBN = fileInput.nextLine();
			title = fileInput.nextLine();
			author = fileInput.nextLine();
			publisher = fileInput.nextLine();
			publishingYear = fileInput.nextInt();
			if(fileInput.hasNextLine()) {
				fileInput.nextLine();
				fileInput.nextLine();
			}
			bookCatalog.add(new Book(ISBN, title, author, publisher, publishingYear));
		}
		fileInput.close();
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	/**
	 * Prints the current catalog to the screen by iterating through the book 
	 * catalog and printing each book
	 */
	public void displayCatalog() {
		ListIterator<Book> iter = bookCatalog.listIterator();
		while (iter.hasNext()) {
			Book tempBook = iter.next();
			System.out.println(tempBook.toString());
			System.out.println("");
		}
	}
	/**
	 * Iterates through the list and tests whether the title matches the inputed 
	 * title
	 * @param titleInput  Title searched by the user
	 * @return tempBook   The book if found
	 */
	public Book searchForBook(String titleInput) {
		ListIterator<Book> iter = bookCatalog.listIterator();
		while (iter.hasNext()) {
			Book tempBook = iter.next();
			if (titleInput.equals(tempBook.getTitle())) {
				return tempBook;
			}
		}
		return null;
	}
	/**
	 * Checks to see if the inputed title is already in the list and if not it
	 * adds a book to the catalog with the inputed ISBN, title, author, publisher
	 * and publishing year
	 * @param ISBN 
	 * @param title
	 * @param author
	 * @param publisher
	 * @param publishingYear
	 * @return true   if the add was successful
	 * @return false  if the add was not successful
	 */
	public Boolean addBook(String ISBN, String title, String author, String publisher, int publishingYear) 
	{
		if(searchForBook(title) == null) {
			bookCatalog.add(new Book(ISBN, title, author, publisher, publishingYear));
			return true;
		} else {
			System.out.println("A book with that title already exists in the library");
			return false;
		}
	}
	/**
	 * Checks to make sure the book is in the catalog and iterates through the catalog
	 * until it finds a book with a matching title. It then sets the author, ISBN
	 * publisher, and publishing year to the inputed information
	 * @param ISBN 
	 * @param title
	 * @param author
	 * @param publisher
	 * @param publishingYear
	 * @return true   if the add was successful
	 * @return false  if the add was not successful
	 */
	public Boolean updateBook(String ISBN, String title, String author, String publisher, int publishingYear) {
		ListIterator<Book> iter = bookCatalog.listIterator();
		if(searchForBook(title) != null) {
			while (iter.hasNext()) {
				Book tempBook = iter.next();
				if (title.equals(tempBook.getTitle())) {
					tempBook.setAuthor(author);
					tempBook.setISBN(ISBN);
					tempBook.setPublisher(publisher);
					tempBook.setPublishingYear(publishingYear);
					iter.set(tempBook);
				}
			}
			return true;
		} else {
			System.out.println("A book with that title doesn't exist in the library");
			return false;
		}
	}
	/**
	 * Checks to make sure the book is in the catalog and iterates through the catalog
	 * until it finds a book with a matching title and removes that book.
	 * @param ISBN 
	 * @param title
	 * @param author
	 * @param publisher
	 * @param publishingYear
	 * @return true   if the add was successful
	 * @return false  if the add was not successful
	 */
	public Boolean removeBook(String title) {
		if(searchForBook(title) != null) {
			ListIterator<Book> iter = bookCatalog.listIterator();
			while (iter.hasNext())
			{
				int tempBookIndex = iter.nextIndex();
				Book tempBook = iter.next();
				if (title.equals(tempBook.getTitle())) {
					bookCatalog.remove(tempBookIndex);
					return true;
				}
			}
			return false;
		} else {
			System.out.println("Book not found in library");
			return false;
		}
	}
	/**
	 * Iterates through the catalog and checks to see if the publisher matches the
	 * inputed publisher. If so it adds it to publisherBookList
	 * @param publisher   			inputed publisher
	 * @return publisherBookList   	An array list of books written by the publisher
	 */
	public ArrayList<Book> getBooksByPublisher(String publisher) {
		ArrayList<Book> publisherBookList = new ArrayList<Book>();
		ListIterator<Book> iter = bookCatalog.listIterator();
		while (iter.hasNext()) {
			Book tempBook = iter.next();
			String BookPublisher = tempBook.getPublisher();
			if (BookPublisher.equals(publisher)) {
				publisherBookList.add(tempBook);
			}
		}
		return publisherBookList;
	}
	/**
	 * Uses Collections.sort to sort the catalog
	 */
	public void sortByTitle() {
		Collections.sort(this.bookCatalog);
	}
	/**
	 * Saves the catalog back to the document. Throws IOException
	 */
	public void save() throws IOException {
		PrintWriter output = new PrintWriter("assg6_catalog.txt");
		ListIterator<Book> iter = bookCatalog.listIterator();
		Book tempBook;
		while (iter.hasNext()) {
			tempBook = iter.next();
			output.println(tempBook.getISBN());
			output.println(tempBook.getTitle());
			output.println(tempBook.getAuthor());
			output.println(tempBook.getPublisher());
			output.println(tempBook.getPublishingYear());
			output.println();
		}
		output.close();
	}
	
}