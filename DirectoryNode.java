/**Michael Zhang
 * 112690432
 * michael.zhang.4@stonybrook.edu
 * Homework 5
 * CSE214.R04 
 * James Finn, Matthew Shinder
 * 
 * This class represents a node in the file tree
 */
public class DirectoryNode {
	private String name;
	private DirectoryNode left;
	private DirectoryNode middle;
	private DirectoryNode right;
	private DirectoryNode parent;
	private boolean isFile;
	private int depth;
	/*
	 * Constructor that creates a Directory node
	 */
	public DirectoryNode() {
	}
	/**
	 * returns the name of the node
	 * @return
	 * name of the DirectoryNode
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the name of the node to a String
	 * @param name
	 * String that the name of the node will be set to
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * returns the left child of the node
	 * @return
	 * reference to the left child
	 */
	public DirectoryNode getLeft() {
		return left;
	}
	/**
	 * returns the middle child of the node
	 * @return
	 * reference to the middle child
	 */
	public DirectoryNode getMiddle() {
		return middle;
	}
	/**
	 * returns the right child of the node
	 * @return
	 * reference to the right child
	 */
	public DirectoryNode getRight() {
		return right;
	}
	/**
	 * sets whether the node is a file or not
	 * @param bool
	 * boolean value to set if the node is a file or not
	 */
	public void setIsFile(boolean bool) {
		isFile = bool;
	}
	/**
	 * returns if the node is a file
	 * @return
	 * boolean value of isFile
	 */
	public boolean getIsFile() {
		return isFile;
	}
	/**
	 * adds a new child node to the current node. up to 3 nodes can be added
	 * @param newChild
	 * new node added as a child to the current node
	 * @throws FullDirectoryException
	 * thrown when all 3 child references are filled
	 * @throws NotADirectoryException
	 * thrown when current node is a file
	 */
	public void addChild(DirectoryNode newChild) throws FullDirectoryException, NotADirectoryException{
		if (left != null && right != null && middle != null) 
			throw new FullDirectoryException();
		if (this.isFile == true) 
			throw new NotADirectoryException();
		if (this.left == null) 
			this.left = newChild;
		else if (this.middle == null) 
			this.middle = newChild;
		else if (this.right == null) 
			this.right = newChild;

	}
	/**
	 * sets the parent node of new node
	 * @param parentNode
	 */
	public void setParent(DirectoryNode parentNode) {
		parent = parentNode;
	}
	/**
	 * returns parent node of current node
	 * @return
	 * parent node of current node
	 */
	public DirectoryNode getParent() {
		return parent;
	}
	/**
	 * sets the depth of the current node
	 * @param depth
	 * integer to set the depth to 
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}
	/**
	 * returns depth
	 * @return
	 * returns integer value representing the depth of the node
	 */
	public int getDepth(){
		return depth;
	}
	/**
	 * prints out the file tree
	 */
	public void printTree() {
		for (int i = 0; i < depth; i++) {
			System.out.print("    ");
		}
		if (this.getIsFile() == false) 
			System.out.println("|- " + this.getName());
		else if (this.getIsFile() == true) 
			System.out.println("- " + this.getName());
		if(left != null) {
			left.printTree();
		}
		if (middle != null) {
			middle.printTree();
		}
		if (right != null) {
			right.printTree();
		}
	}
	
}
