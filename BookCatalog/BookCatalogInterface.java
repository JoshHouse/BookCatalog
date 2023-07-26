package BookCatalog;

import java.io.IOException;
import java.util.ArrayList;
/**
 * 
 * @author joshuahouse
 * Interface for the BookCatalog class
 */
public interface BookCatalogInterface {
	public void loadData(String inputFile);
	public void displayCatalog();
	public Book searchForBook(String titleInput);
	public Boolean addBook(String ISBN, String title, String author, String publisher, int publishingYear);
	public Boolean updateBook(String ISBN, String title, String author, String publisher, int publishingYear);
	public Boolean removeBook(String title);
	public ArrayList<Book> getBooksByPublisher(String publisher);
	public void sortByTitle();
	public void save() throws IOException;
}