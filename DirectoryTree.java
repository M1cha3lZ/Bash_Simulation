/**Michael Zhang
 * 112690432
 * michael.zhang.4@stonybrook.edu
 * Homework 5
 * CSE214.R04 
 * James Finn, Matthew Shinder
 * 
 * This class represents a file tree containing DirectoyNodes
 */
public class DirectoryTree {
	private DirectoryNode root;
	private DirectoryNode cursor;
	/**
	 * Creates a directory tree and the root of the tree
	 */
	public DirectoryTree() {
		root = new DirectoryNode();
		cursor = root;
		root.setName("root");
		root.setDepth(0);
	}
	/**
	 * returns the root of the tree
	 * @return
	 * DirectoryNode that represents the root
	 */
	public DirectoryNode getRoot() {
		return root;
	}
	/**
	 * returns the cursor
	 * @return
	 * DirectoryNode that represents the cursor
	 */
	public DirectoryNode getCursor() {
		return cursor;
	}
	/**
	 * sets the cursor
	 * @param node
	 * the node that cursor will be set to.
	 */
	public void setCursor(DirectoryNode node) {
		cursor = node;
	}
	/**
	 * moves the cursor to the root
	 */
	public void resetCursor() {
		cursor = root;
	}
	/**
	 * moves cursor to the direct child of cursor with the 
	 * matching name as the input
	 * @param name
	 * String that represents the name of the directory
	 * @throws NotADirectoryException
	 * thrown when the inputed name is a file
	 */
	public void changeDirectory(String name) throws NotADirectoryException {
		boolean found = false;
		if (cursor.getLeft().getName().equals(name)) {
			if (cursor.getLeft().getIsFile() == true) 
				throw new NotADirectoryException();
			else {
				cursor = cursor.getLeft();
				found = true;
			}
		}
		else if (cursor.getMiddle().getName().equals(name)) {
			if (cursor.getMiddle().getIsFile() == true) 
				throw new NotADirectoryException();
			else {
				cursor = cursor.getMiddle();
				found = true;
			}
		}
		else if (cursor.getRight().getName().equals(name)) {
			if (cursor.getRight().getIsFile() == true) 
				throw new NotADirectoryException();
			else {
				cursor = cursor.getRight();
				found = true;
			}
		}
		if (found == false) {
			throw new NullPointerException();
		}
	}
	/**
	 * finds the node in the tree with matching name recursively 
	 * and prints out the path
	 * @param root
	 * the start of the recursion 
	 * @param name
	 * name of the directory you are searching for
	 */
	public void find(DirectoryNode root, String name) {
		if (root.getName().equals(name)) {
			cursor = root;
			System.out.println(presentWorkingDirectory());
		}
		if (root.getLeft() != null) {
			find(root.getLeft(), name);
		}
		if (root.getMiddle() != null) {
			find(root.getMiddle(), name);
		}
		if (root.getRight() != null) {
			find(root.getRight(), name);
		}
	}
	public void path(DirectoryNode root, String name) {
		if (root.getName().equals(name)) {
			cursor = root;
		}
		if (root.getLeft() != null) {
			path(root.getLeft(), name);
		}
		if (root.getMiddle() != null) {
			path(root.getMiddle(), name);
		}
		if (root.getRight() != null) {
			path(root.getRight(), name);
		}
	}
	/**
	 * Returns a String containing the path of directory 
	 * names from the root node of the tree to the cursor
	 * @return
	 * String containing the path of directory
	 */
	public String presentWorkingDirectory() {
		DirectoryNode tempCursor = new DirectoryNode();
		tempCursor = cursor;
		String list ="";
		while(tempCursor != null) {
			list += tempCursor.getName() + "/";
			tempCursor = tempCursor.getParent();
		}
		String[] listArray = list.split("/");
		String workingDirectory = "";
		for (int i = listArray.length - 1; i >= 0; i--) {
			workingDirectory += listArray[i];
			if (i != 0) {
				workingDirectory += "/";
			}
		}
		return workingDirectory;
	}
	/**
	 * Returns a String containing a space-separated list of 
	 * names of all the child directories or files of the cursor.
	 * @return
	 * String containing children of the cursor
	 */
	public String listDirectory() {
		String list = "";
		if (cursor.getLeft() != null) 
			list += cursor.getLeft().getName() + " ";
		if (cursor.getMiddle() != null) 
			list += cursor.getMiddle().getName() + " ";
		if (cursor.getRight() != null) 
			list += cursor.getRight().getName() + " ";
		return list;		
	}
	/**
	 * prints the directory tree from the cursor
	 */
	public void printDirectoryTree() {
		System.out.println();
		cursor.printTree();
		System.out.println();
	}
	/**
	 * Creates a directory with the indicated name and 
	 * adds it to the children of the cursor node
	 * @param name
	 * name of new directory
	 * @throws IllegalArgumentException
	 * thrown if name contains white space or /
	 * @throws FullDirectoryException
	 * thrown if cursor node has 3 children
	 */
	public void makeDirectory(String name) throws 
	  IllegalArgumentException, FullDirectoryException{
		if (name.contains(" ") || name.contains("/"))  
			throw new IllegalArgumentException();
		DirectoryNode newChild = new DirectoryNode();
		newChild.setName(name);
		newChild.setParent(cursor);
		newChild.setIsFile(false);
		newChild.setDepth(cursor.getDepth() + 1);
		try {
			cursor.addChild(newChild);
		} 
		catch (FullDirectoryException e) {
			throw new FullDirectoryException();
		}
		catch (NotADirectoryException a) {
		}
	}
	/**
	 * Creates a file with the indicated name and 
	 * adds it to the children of the cursor node
	 * @param name
	 * name of new file
	 * @throws IllegalArgumentException
	 * thrown if name contains white space or /
	 * @throws FullDirectoryException
	 * thrown if cursor node has 3 children
	 */
	public void makeFile(String name) throws 
	  IllegalArgumentException, FullDirectoryException{
		if (name.contains(" ") || name.contains("/")) 
			throw new IllegalArgumentException();
		DirectoryNode newChild = new DirectoryNode();
		newChild.setName(name);
		newChild.setParent(cursor);
		newChild.setIsFile(true);
		newChild.setDepth(cursor.getDepth() + 1);
		try {
			cursor.addChild(newChild);
		} 
		catch (FullDirectoryException e) {
			throw new FullDirectoryException();
		}
		catch (NotADirectoryException a) {
		}
		
	}
	

}
