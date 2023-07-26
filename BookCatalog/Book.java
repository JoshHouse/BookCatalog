package BookCatalog;
/**
 * 
 * @author Joshua House
 * Book Class object stored in ArrayList by book catalog. Holds ISBN, Title, Author,
 * Publisher, and publishingYear.
 */
public class Book implements Comparable<Book> {
	private String ISBN, title, author, publisher;
	private int publishingYear;
	/**
	 * Book constructor that takes ISBN, title, author, publisher, and Publishing Year
	 * and sets them as the value of the new book
	 * @param ISBN
	 * @param title
	 * @param author
	 * @param publisher
	 * @param publishingYear
	 */
	public Book(String ISBN, String title, String author, String publisher, int publishingYear){
		this.ISBN = ISBN;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publishingYear = publishingYear;
	}
	/**
	 * Takes the ISBN and sets it as the ISBN of the book
	 * @param ISBN
	 */
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	/**
	 * Takes the author and sets it as the author of the book
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * Takes the publisher and sets it as the author of the book
	 * @param publisher
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	/**
	 * Takes the publishing year and sets it as the publishing year of the book
	 * @param publishingYear
	 */
	public void setPublishingYear(int publishingYear) {
		this.publishingYear = publishingYear;
	}
	/**
	 * Returns the ISBN of the book
	 * @return ISBN
	 */
	public String getISBN() {
		return ISBN;
	}
	/**
	 * Returns the title of the book
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Returns the author of the book
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * Returns the publisher of the book
	 * @return publisher
	 */
	public String getPublisher() {
		return publisher;
	}
	/**
	 * Returns the publishing year of the book
	 * @return publishingYear
	 */
	public int getPublishingYear() {
		return publishingYear;
	}
	/**
	 * Converts the book to a string with the ISBN, title, author, publisher, and
	 * publishing year on separate lines
	 * @return Books information as a string
	 */
	public String toString() {
		return this.ISBN + "\n" + this.title + "\n" + this.author + "\n" + this.publisher + "\n" + this.publishingYear;
	}
	/**
	 * Compares two books titles to see if they are the same. Returns true if they
	 * are the same and false if they are not
	 * @param input
	 * @return true
	 * @return false
	 */
	public Boolean equals(Book input) {
		if(this.getTitle().equals(input.getTitle()))
		{
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Checks the lengths of both titles and runs a for loop for the length of the
	 * shortest title. It compares each letter and returns -1 when it hits a letter 
	 * in which this book's letter comes before the inputed book alphabetically. It
	 * returns 1 when it hits a letter in which this book's letter comes after the 
	 * inputed book alphabetically. It returns 0 when the titles are equal
	 * @param input   An inputed book to be compared with
	 * @return 1	When this.book comes before inputed book alphabetically
	 * @return 0	When the titles are the same
	 * @return -1	When the inputed book comes before this.book alphabetically
	 */
	public int compareTo(Book input) {
		int minimumLength;
		if (this.getTitle().length() < input.getTitle().length()) {
			minimumLength = this.getTitle().length();
		} else {
			minimumLength = input.getTitle().length();
		}
		for(int x = 0; x < minimumLength; x++) {
			if (this.getTitle().charAt(x) < input.getTitle().charAt(x))
			{
				return -1;
			} else if (this.getTitle().charAt(x) > input.getTitle().charAt(x)) {
				return 1;
			}
		}
		return 0;
	}
	
}