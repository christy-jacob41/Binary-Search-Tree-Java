// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

//importing array lists
import java.util.ArrayList;
/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;

    // calling recursive nodeCount method and passing the root
    public int nodeCount()
    {
    	return nodeCount(root);
    }
    
    // nodeCount method recursively traverses the tree and returns the count of nodes
    private int nodeCount(BinaryNode<AnyType> currentNode)
    {
    	// if current node is null don't count it
    	if(currentNode == null)
    	{
    		return 0;
    	}
    	else
    	{
    		// if current node isn't null count it plus it's children
    		return 1 + nodeCount(currentNode.left) + nodeCount(currentNode.right);
    	}
    }
    
    // calling recursive isFull method and passing the root
    public boolean isFull()
    {
    	return isFull(root);
    }
    
    // isFull method recursively traverses the tree and returns true as long as each node has two children or is a leaf node
    private boolean isFull(BinaryNode<AnyType> currentNode)
    {
    	// if both children are null, it's a leaf node
    	if(currentNode.left == null && currentNode.right == null)
    	{
    		return true;
    	}
    	// if a node has two children, check if the children are a leaf or a parent with two children
    	else if(currentNode.left != null && currentNode.right != null)
    	{
    		return isFull(currentNode.left) && isFull(currentNode.right);
    	}
    	// if the node isn't a leaf or a parent with two children, return false
    	else
    	{
    		return false;
    	}
    }
    
    // calling recursive compareStructure method and passing the root of both trees unless they're both empty
    public boolean compareStructure(BinarySearchTree<AnyType> otherTree)
    {
    	// if both trees are empty no need to compare
    	if(this.isEmpty() && otherTree.isEmpty())
    	{
    		return true;
    	}
    	else
    	{
    		// call recursive compareStructure method if both trees aren't empty
    		return compareStructure(root, otherTree.root);
    	}
    }
    
    // compareStructure method recursively traverses tree and compares corresponding nodes of both trees to make sure they both are either null or not null
    private boolean compareStructure(BinaryNode<AnyType>currentTreeNode, BinaryNode<AnyType>otherTreeNode)
    {
    	// if corresponding nodes are null, the structure is same for that parent
    	if(currentTreeNode == null && otherTreeNode == null)
    	{
    		return true;
    	}
    	// if both corresponding nodes are not null, keep moving down the tree to make sure the structure of the rest of the tree is the same
    	else if(currentTreeNode != null && otherTreeNode!= null)
    	{
    		return compareStructure(currentTreeNode.left, otherTreeNode.left) && compareStructure(currentTreeNode.right, otherTreeNode.right);
    	}
    	// if corresponding nodes aren't null or non-null, return false
    	else
    	{
    		return false;
    	}
    }
   
    // calling recursive equals method as long as both trees aren't empty
    public boolean equals(BinarySearchTree<AnyType> otherTree)
    {
    	// if both are empty no need to check
    	if(this.isEmpty() && otherTree.isEmpty())
    	{
    		return true;
    	}
    	else
    	{
    		// call recursive equals method if both trees aren't empty
    		return equals(this.root, otherTree.root);
    	}
    }
    
    // recursive equals method that checks each corresponding node in both trees to make sure they're equal
    private boolean equals(BinaryNode<AnyType> currentTreeNode, BinaryNode<AnyType> otherTreeNode)
    {
    	// if both corresponding nodes are null then they're equal
    	if(currentTreeNode == null && otherTreeNode == null)
    	{
    		return true;
    	}
    	// if both corresponding nodes aren't null and have same element, they're equal so continue checking rest of the tree
    	else if((currentTreeNode != null && otherTreeNode != null) && currentTreeNode.element.equals(otherTreeNode.element))
    	{
    		return equals(currentTreeNode.left, otherTreeNode.left) && equals(currentTreeNode.right, otherTreeNode.right);
    	}
    	// if corresponding nodes aren't equal, return false
    	else
    	{
    		return false;
    	}
    }
    
    // calling recursive copy function that copies every node after creating a new binary search tree
    public BinarySearchTree<AnyType> copy()
    {
    	BinarySearchTree<AnyType> newCopy = new BinarySearchTree<>();
    	// recursive copy method returns a binary node which in this case is the copied root
    	newCopy.root = copy(this.root);
    	// returns the new tree
    	return newCopy;
    }
    
    // recursive equals method that goes through each node in the current tree and copies it over to the new tree
    private BinaryNode<AnyType> copy(BinaryNode<AnyType> currentTreeNode)
    {
    	// if the current tree's node is null, no need to copy
    	if(currentTreeNode == null)
    	{
    		return null;
    	}
    	
    	// create a new binary node for each node in the current tree then recursively copy each of it's children using preorder traversal
    	BinaryNode<AnyType> otherTreeNode = new BinaryNode<AnyType>(currentTreeNode.element, copy(currentTreeNode.left), copy(currentTreeNode.right));
    	
    	// return the new tree's current node
    	return otherTreeNode;
    	
    }
    
 // calling recursive mirror function that mirrors every node after creating a new binary search tree
    public BinarySearchTree<AnyType> mirror()
    {
    	BinarySearchTree<AnyType> mirrorTree = new BinarySearchTree<>();
    	// recursive mirror method returns a binary node which in this case is the copied root
    	mirrorTree.root = mirror(this.root);
    	// returns the new tree
    	return mirrorTree;
    }
    
    // recursive equals method that goes through each node in the current tree and mirrors it over to the new tree
    private BinaryNode<AnyType> mirror(BinaryNode<AnyType> currentTreeNode)
    {
    	// if the current tree's node is null, no need to mirror
    	if(currentTreeNode == null)
    	{
    		return null;
    	}
    	
    	// create a new binary node for each node in the current tree then recursively mirror each of it's children using preorder traversal
    	BinaryNode<AnyType> mirrorTreeNode = new BinaryNode<AnyType>(currentTreeNode.element, mirror(currentTreeNode.right), mirror(currentTreeNode.left));
  
    	// return the new tree's current node
    	return mirrorTreeNode;
    	
    }
    
    // calling recursive isMirror method as long as both trees aren't empty
    public boolean isMirror(BinarySearchTree<AnyType> tree2)
    {
    	// if both trees are empty, it's true
    	if(this.isEmpty() && tree2.isEmpty())
    	{
    		return true;
    	}
    	else
    	{
    		// if both trees aren't empty, call recursive isMirror method
    		return isMirror(root, tree2.root);

    	}
    }
    
    // recursive isMirror function makes sure the corresponding mirrored nodes are null or equal or else, return false
    private boolean isMirror(BinaryNode<AnyType> currentTreeNode, BinaryNode<AnyType> otherTreeNode)
    {
    	// if corresponding mirrored nodes are null, they're equal
    	if(currentTreeNode == null && otherTreeNode == null)
    	{
    		return true;
    	}
    	// if both corresponding mirrored nodes are equal, continue checking down the tree
    	else if((currentTreeNode != null && otherTreeNode != null) && currentTreeNode.element.equals(otherTreeNode.element))
    	{
    		return isMirror(currentTreeNode.left, otherTreeNode.right) && isMirror(currentTreeNode.right, otherTreeNode.left);
    	}
    	// if corresponding mirrored nodes aren't equal, return false
    	else
    	{
    		return false;
    	}
    }
    
    // rotateRight method performs a single rotate right on the node containing the specified value
    public void rotateRight(AnyType value) throws Exception
    {
    	// if the value is in the tree perform the rotate
    	if(contains(value))
    	{
    		// node containing the value
    		BinaryNode<AnyType> valueNode = root;
    		// parent node of the child containing the value(unless the node containing the value is root)
    		BinaryNode<AnyType> parentNode = root;
    		// left child of the node containing the value
    		BinaryNode<AnyType> valueLeftChild = root.left;
    		// right node of left child of node containing the value
    		BinaryNode<AnyType> valueLeftChildsRightChild = root.left.right;
    		
    		// find the proper node to rotate and the necessary nodes to perform the rotate
    		while(!(valueNode.element.equals(value)))
    		{
    			// adjust the parent node of the node containing the value
    			parentNode = valueNode;
    			// move left if current node is greater than the value
    			if(valueNode.element.compareTo(value) > 0)
    			{
    				valueNode = valueNode.left;
    				valueLeftChild = valueNode.left;
    				if(valueLeftChild!=null)
    					valueLeftChildsRightChild = valueLeftChild.right;
    				else
    					valueLeftChildsRightChild = null;
    			}
    			// move right if the current node is less than the value
    			else
    			{
    				valueNode = valueNode.right;
    				valueLeftChild = valueNode.left;
    				if(valueLeftChild!=null)
    					valueLeftChildsRightChild = valueLeftChild.right;
    				else
    					valueLeftChildsRightChild = null;
    			}
    			
    		}
    		
    		// if necessary nodes to rotate are null throw an exception
    		if(valueNode==null || valueLeftChild == null)
    			throw new Exception("Rotating can't occur because necessary node is null");
    		
    		// perform the rotate by changing left and right pointers
    		BinaryNode<AnyType> temp = valueLeftChild;
    		valueNode.left=valueLeftChildsRightChild;
    		temp.right = valueNode;
    		
    		// change the root if the root is the one shifted
    		if(root.element.equals(value))
    		{
    			root = valueLeftChild;
    		}
    		// if root isn't shifted have the parent point to the new node after shifting
    		else
    		{
    			// if parent is larger, put the shifted node on left
    			if(parentNode.element.compareTo(temp.element)>0)
    			{
    				parentNode.left = temp;
    			}
    			// if parent is smaller, put the shifted node on right
    			else
    			{
    				parentNode.right = temp;
    			}
    		}
    	}
    	else
    	{
    		// if value isn't in the tree, print an error message
    		System.out.println("Value isn't in the binary search tree. Try again.");
    	}
    }
    
 // rotateLeft method performs a single rotate left on the node containing the specified value
    public void rotateLeft(AnyType value) throws Exception
    {
    	// if the value is in the tree perform the rotate
    	if(contains(value))
    	{
    		// node containing the value
    		BinaryNode<AnyType> valueNode = root;
    		// parent node of the child containing the value(unless the node containing the value is root)
    		BinaryNode<AnyType> parentNode = root;
    		// right child of the node containing the value
    		BinaryNode<AnyType> valueRightChild = root.right;
    		// left node of right child of node containing the value
    		BinaryNode<AnyType> valueRightChildsLeftChild = root.right.left;
    		
    		// find the proper node to rotate and the necessary nodes to perform the rotate
    		while(!(valueNode.element.equals(value)))
    		{
    			// adjust the parent node of the node containing the value
    			parentNode = valueNode;
    			// move left if current node is greater than the value
    			if(valueNode.element.compareTo(value) > 0)
    			{
    				valueNode = valueNode.left;
    				valueRightChild = valueNode.right;
    				if(valueRightChild!=null)
    					valueRightChildsLeftChild = valueRightChild.left;
    				else
    					valueRightChildsLeftChild = null;
    			}
    			// move right if the current node is less than the value
    			else
    			{
    				valueNode = valueNode.right;
    				valueRightChild = valueNode.right;
    				if(valueRightChild!=null)
    					valueRightChildsLeftChild = valueRightChild.left;
    				else
    					valueRightChildsLeftChild = null;
    			}
    			
    		}
    		
    		// if necessary nodes to rotate are null throw an exception
    		if(valueNode==null || valueRightChild == null)
    			throw new Exception("Rotating can't occur because necessary node is null");
    		
    		// perform the rotate by changing left and right pointers
    		BinaryNode<AnyType> temp = valueRightChild;
    		valueNode.right = valueRightChildsLeftChild;
    		temp.left = valueNode;
    		
    		// change the root if the root is the one shifted
    		if(root.element.equals(value))
    		{
    			root = valueRightChild;
    		}
    		// if root isn't shifted have the parent point to the new node after shifting
    		else
    		{
    			// if parent is larger, put the shifted node on left
    			if(parentNode.element.compareTo(temp.element)>0)
    			{
    				parentNode.left = temp;
    			}
    			// if parent is smaller, put the shifted node on right
    			else
    			{
    				parentNode.right = temp;
    			}
    		}
    	}
    	else
    	{
    		// if value isn't in the tree, print an error message
    		System.out.println("Value isn't in the binary search tree. Try again.");
    	}
    }
    
    // calling recursive printLevels method
    public void printLevels()
    {
    	// array list holding strings that have the elements of the nodes at each level
    	ArrayList<String> holdLevels = new ArrayList<>();
    	
    	// add an entry for each level in the tree
    	for(int i = 0; i <= height(root); i++)
    	{
    		holdLevels.add("");
    	}
    	// calling recursive printLevels method that adds the elements if not null, to the strings in the array list
    	printLevels(root, 0, holdLevels);
    	
    	// printing the tree
    	System.out.println("Printing Tree Level-By-Level:");
    	// print the string representing each level of the tree
    	for(int i = 0; i <= height(root); i++)
    	{
    		System.out.println("Level(Depth) " + i + ": " + holdLevels.get(i));
    	}
    }
    
    // recursive printLevels method that adds each element if not null on each level of the tree to the corresponding string in the array list
    private void printLevels(BinaryNode<AnyType> currentNode, int currentLevel, ArrayList<String> holdLevels)
    {
    	// if the node isn't null, add the element to the corresponding string and keep moving down the tree
    	if(currentNode != null)
    	{
    		holdLevels.set(currentLevel, holdLevels.get(currentLevel) + currentNode.element + " ");
    		printLevels(currentNode.left, currentLevel+1, holdLevels);
    		printLevels(currentNode.right, currentLevel+1, holdLevels);
    	}
    
    }
        // Test program
    public static void main( String [ ] args ) throws Exception
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<>( );
        final int NUMS = 4000;
        final int GAP  =   37;

        System.out.println( "Checking... (no more output means success)" );

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            t.insert( i );

        for( int i = 1; i < NUMS; i+= 2 )
            t.remove( i );

        if( NUMS < 40 )
            t.printTree( );
        if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
            System.out.println( "FindMin or FindMax error!" );

        for( int i = 2; i < NUMS; i+=2 )
             if( !t.contains( i ) )
                 System.out.println( "Find error1!" );

        for( int i = 1; i < NUMS; i+=2 )
        {
            if( t.contains( i ) )
                System.out.println( "Find error2!" );
        }
                
     // creating new binary search tree
        BinarySearchTree<Integer> w = new BinarySearchTree<>();
        for(int i = 0; i <= 40; i++)
        {
        	w.insert(i);
        }
        
     // creating a full tree to test is Full method
        BinarySearchTree<Integer> fullTree = new BinarySearchTree<>();
        fullTree.insert(25);
        fullTree.insert(20);
        fullTree.insert(30);
        fullTree.insert(18);
        fullTree.insert(22);
        fullTree.insert(28);
        fullTree.insert(33);
        
        // testing nodeCount method with trees t and w
        System.out.println("\nCalling nodeCount method");
        System.out.println("Node Count of tree t: " + t.nodeCount());
        System.out.println("Node Count of w: " + w.nodeCount()); // should print 41
        System.out.println("Node Count of fullTree: " + fullTree.nodeCount()); // should print 41

     // testing isFull method with trees t and w
        System.out.println("\nCalling isFull method");
        System.out.println("The tree t is full(true or false): " + t.isFull()); // should print false since it has 117 levels(according to height method) and only 1999 nodes
        System.out.println("The tree w is full(true or false): " + w.isFull()); // should print false
        System.out.println("The tree fullTree is full(true or false): " + fullTree.isFull());
        
        // creating an identical tree to w to test compareStructure and equals methods
        BinarySearchTree<Integer> twinOfW = new BinarySearchTree<>();
        for( int i = 0; i <= 40; i++)
        {
        	twinOfW.insert( i );
        }

        // creating a tree with identical structure not w but not equal to test compareStructure and equals methods
        BinarySearchTree<Integer> l = new BinarySearchTree<>();
        for( int i = 60; i <=100; i++)
        {
        	l.insert( i );
        }
      
        // testing compareStructure method with t and tree with different structure w
        System.out.println("\nCalling compareStructure method");
        System.out.println("The structure of t and w are the same(true or false): " + t.compareStructure(w)); // should print false
        // testing compareStructure method with w and a tree with identical structure twinOfW
        System.out.println("The structure of w and twinOfW are the same(true or false): " + w.compareStructure(twinOfW)); // should print true
        // testing compareStructure method with w and a tree with identical structure l
        System.out.println("The structure of w and l are the same(true or false): " + w.compareStructure(l)); // should print true
        
        // testing equals method with w and identical tree twinOfW
        System.out.println("\nCalling equals method");
        System.out.println("Trees w and twinOfW are equal(true or false): " + w.equals(twinOfW)); // should print true
        // testing equals method with w and different tree l
        System.out.println("Trees w and l are equal(true or false): " + w.equals(l)); // should print false
        // testing equals method with w and different tree t
        System.out.println("Trees w and t are equal(true or false): " + w.equals(t)); // should print false

        // testing copy method after creating it using the compareStructure and equals methods and trees t and w
        BinarySearchTree<Integer> copyOfW = w.copy();
        BinarySearchTree<Integer> copyOfT = t.copy();
        System.out.println("\nCalling copy method");
        // all the outputs should be true
        System.out.println("The copy of w has the same structure as w(true or false): " + w.compareStructure(copyOfW));
        System.out.println("The copy of w is equal to w(true or false): " + w.equals(copyOfW));
        System.out.println("The copy of t has the same structure as t(true or false): " + t.compareStructure(copyOfT));
        System.out.println("The copy of t is equal to t(true or false): " + t.equals(copyOfT));
        
        // testing copy method with fullTree after printing the original fullTree
        System.out.println("\nTesting with fullTree");
        System.out.println("Original fullTree");
        fullTree.printLevels();
        BinarySearchTree<Integer> copyOfFullTree = fullTree.copy();
        System.out.println("Copy of fullTree");
        copyOfFullTree.printLevels();
        
        // testing mirror method with fullTree
        System.out.println("\nCalling mirror method");
        System.out.println("Mirror of fullTree");
        BinarySearchTree<Integer> mirrorOfFullTree = fullTree.mirror();
        mirrorOfFullTree.printLevels();
        
        // testing isMirror method with two trees that aren't mirrors and one tree that is a mirror of fullTree
        System.out.println("\nCalling isMirror method");
        System.out.println("Tree w is a mirror of fullTree(true or false): " + fullTree.isMirror(w)); // should return false
        System.out.println("Tree copyOfFullTree is a mirror of fullTree(true or false): " + fullTree.isMirror(copyOfFullTree)); // should return false
        System.out.println("Tree mirrorOfFullTree is a mirror of fullTree(true or false): " + fullTree.isMirror(mirrorOfFullTree)); // should return true
        
        // creating a tree to test the rotate right method
        BinarySearchTree<Integer> testRotateRight = new BinarySearchTree<>();
        testRotateRight.insert(100);
        testRotateRight.insert(150);
        testRotateRight.insert(50);
        testRotateRight.insert(70);
        testRotateRight.insert(40);
        testRotateRight.insert(45);
        
        // printing the testRotateRight tree
        System.out.println("\nPrinting the testRotateRight tree");
        testRotateRight.printLevels();
        
        // testing rotate right method
        System.out.println("\nCalling the rotateRight method");
        testRotateRight.rotateRight(75); // should print that the value doesn't exist in the tree
        System.out.println("Rotating 100 to the right");
        testRotateRight.rotateRight(100);
        testRotateRight.printLevels();
        System.out.println("Rotating 100 to the right");
        testRotateRight.rotateRight(100);
        testRotateRight.printLevels();
        // these 4 should throw exceptions
 //       testRotateRight.rotateRight(70);
 //       testRotateRight.rotateRight(40);
 //       testRotateRight.rotateRight(150);
 //       testRotateRight.rotateRight(100);

        // testing rotate left method
        System.out.println("\nCalling the rotateLeft method");
        testRotateRight.rotateLeft(75); // should print that the value doesn't exist in the tree
        System.out.println("Rotating 70 to the left");
        testRotateRight.rotateLeft(70);
        testRotateRight.printLevels();
        System.out.println("Rotating 50 to the left");
        testRotateRight.rotateLeft(50); // should return back to originally created tree
        testRotateRight.printLevels();
        // these 2 should throw exceptions
 //       testRotateRight.rotateLeft(40);
 //       testRotateRight.rotateLeft(150);
        
        //testing printLevels method
        System.out.println("\nCalling printLevels method");
        System.out.println("Testing with fullTree");
        fullTree.printLevels();
        System.out.println("Testing with w");
        w.printLevels();


        // randomly testing rotate right and rotate left and print levels
        System.out.println("\nRandom testing of binary search tree w");
        for(int i = 0; i < 20; i++)
        {
        	w.rotateLeft(i);
        }
        w.printLevels();

        for(int i = 10; i > 5; i--)
        {
        	w.rotateRight(i);
        }
        w.printLevels();
    }
}