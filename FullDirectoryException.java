/**Michael Zhang
 * 112690432
 * michael.zhang.4@stonybrook.edu
 * Homework 5
 * CSE214.R04 
 * James Finn, Matthew Shinder
 * 
 * This class extends the Exception class. Thrown when
 * the directory's nodes are all full. 
 */
@SuppressWarnings("serial")
public class FullDirectoryException extends Exception {
	public FullDirectoryException() {
	}
	public FullDirectoryException(String message) {
		super(message);
	}
}