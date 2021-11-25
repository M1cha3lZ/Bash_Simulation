/**Michael Zhang
 * 112690432
 * michael.zhang.4@stonybrook.edu
 * Homework 5
 * CSE214.R04 
 * James Finn, Matthew Shinder
 * 
 * This class extends the exception class. Thrown 
 * if the node is a file
 */
@SuppressWarnings("serial")
public class NotADirectoryException extends Exception {
	public NotADirectoryException(){
	}
	public NotADirectoryException(String message) {
		super(message);
	}
}

