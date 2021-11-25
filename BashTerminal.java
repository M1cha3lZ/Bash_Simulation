/**Michael Zhang
 * 112690432
 * michael.zhang.4@stonybrook.edu
 * Homework 5
 * CSE214.R04 
 * James Finn, Matthew Shinder
 * 
 * This class allows the user to interact with the Directory Tree
 * with certain inputs
 * 
 */
import java.util.Scanner;
public class BashTerminal {
	/**
	 * main method of the class. Runs a Bash Terminal and allows the user to 
	 * navigate the tree through specific inputs
	 * @param args
	 * @throws IllegalArgumentException
	 * thrown if improper name was entered 
	 * @throws FullDirectoryException
	 * thrown if directory has 3 children and attempts to add more
	 * 
	 * extra credit implemented "fine {name}", "cd ..", cd {path}
	 */
	public static void main (String[]args) throws 
	  IllegalArgumentException, FullDirectoryException {
		Scanner stdin = new Scanner(System.in);
		System.out.println("Starting bash terminal.");
		DirectoryTree tree = new DirectoryTree();
		String input = "";
		// runs until user enters exit
		while (!input.equals("exit")) {
			System.out.print("[zhang147@helloWorld]: $ ");
			input = stdin.nextLine();
			// prints the working directory of cursor
			if (input.trim().equals("pwd"))
				System.out.println(tree.presentWorkingDirectory());
			// lists the name of the children of cursor 
			if (input.trim().equals("ls"))
				System.out.println(tree.listDirectory());
			// prints the tree starting from cursor
			if (input.trim().equals("ls -R")) 
				tree.printDirectoryTree();
			// moves cursor.
			if (input.trim().split(" ")[0].equals("cd")) {
				try {
					String dir = input.trim().split(" ")[1];
					// resets the cursor to root
					if (dir.equals("/"))
						tree.resetCursor();
					// moves cursor to the parent
					else if (dir.equals("..")) {
						if (tree.getCursor().getName().equals("root")) {
							System.out.println("ERROR: Already at root directory.");
						}
						else {
							tree.setCursor(tree.getCursor().getParent());
						}
					}
					//moves cursor with provided path
					else if (input.split("/").length > 1) {
						tree.path(tree.getRoot(), input.trim().split("/")[input.split("/").length-1]);
					}
					// moves parent to the child with specified name
					else
						tree.changeDirectory(input.split(" ")[1]);
				} 
				catch (NotADirectoryException e) {
					System.out.println("ERROR: Cannot change directory into a file.");
				}
				catch (NullPointerException e) {
					System.out.println("ERROR: No such directory named '" 
					  + input.split(" ")[1] + "'.");
				}
			}
			//creates new directory
			if (input.trim().split(" ")[0].equals("mkdir")) {
				try {
					tree.makeDirectory(input.trim().split("mkdir ")[1]);
				}catch (FullDirectoryException e) {
					System.out.println("ERROR: Present directory is full.");
				}
				catch (IllegalArgumentException i) {
					System.out.println("ERROR: Please enter a valid name.");
				}
			}
			// creates new file
			if (input.trim().split(" ")[0].equals("touch")) {
				try {
					tree.makeFile(input.trim().split("touch ")[1]);
				}catch (FullDirectoryException e) {
					System.out.println("ERROR: Present directory is full.");
				}
				catch (IllegalArgumentException i) {
					System.out.println("ERROR: Please enter a valid name.");
				}
			}
			// finds node with given name and prints out the path
			if (input.trim().split(" ")[0].equals("find")) {
				DirectoryNode temp = tree.getCursor();
				String name = input.trim().split("find ")[1];
				tree.find(tree.getRoot(), name);
				tree.setCursor(temp);
			}
			
		}
		System.out.println("Program terminating normally");
		stdin.close();
	}

}
