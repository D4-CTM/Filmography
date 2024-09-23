/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MovieManager.DataTree;

import MovieManager.MovieList.Movies;

/**
 *
 * @author joshdelcid
 */
public class Tree {
    private Node root;
    private int size;

    public Tree()
    {
        root = null;
        size = 0;        
    }
    
    public int getSize() {
        return size; 
    }
    
    public boolean add(String Name, int Rating, int Code)
    {
        if (root == null) {
            root = new Node(new Movies(Name, Rating, Code));
            size++;
            return true;
        }
        
        return add(new Movies(Name, Rating, Code), root);
    }

    public boolean add(String Name, String Description, int Rating, int Code)
    {
        if (root == null) {
            root = new Node(new Movies(Name, Description, Rating, Code));
            size++;
            return true;
        }
        
        return add(new Movies(Name, Description, Rating, Code), root);
    }    
    
    private boolean add(Movies Movie, Node actPtr)
    {
        if (actPtr == null) {
            System.out.println("It's null");
            return false;
        }
        if (Movie.getCode() == actPtr.getCode()) {
            System.out.println("Codigo repetido");
            return false;
        }
        
        if (Movie.getCode() > actPtr.getCode())
        {
            if (actPtr.getRight() == null) 
            {
                actPtr.setRight(new Node(Movie));
                size++;
                return true;
            } else return add(Movie, actPtr.getRight());
        }
        else
        {
            if (actPtr.getLeft() == null) 
            {
                actPtr.setLeft(new Node(Movie));
                size++;
                return true;
            } else return add(Movie, actPtr.getLeft());            
        }
    }

    private boolean isLeaf(Node node)
    { return (node.getLeft() == null && node.getRight() == null); }
    
    public final boolean remove(int MovieCode)
    {
        if (root == null) return false;
        
        if (root.getCode() == MovieCode) 
        {
            if (root.getLeft() != null) {
                root.setMovie(getRightest(root, root.getLeft()).getMovie());
                return true;
            } else if (root.getRight() != null) { 
                root.setMovie(getLeftest(root, root.getRight()).getMovie());
                return true;
            } 

            root = null;
            return false;
        }
        
        Node father = searchFatherOf(MovieCode, root);
        if (father == null) return false;
        System.out.println(father.getCode());
        Node son = (MovieCode > father.getCode()) ? father.getRight() : father.getLeft();

        if (son == null) return false;
        return remove(father, son);
    }
    
    private boolean remove(Node father, Node son)
    {
        if (isLeaf(son))
        {

            if (father.getRight() == son) {
                father.setRight(null);
            } else father.setLeft(null);
            size--;
            return true;
        } 
        
        if (son.getLeft() != null) {     
            son.setMovie(getRightest(son, son.getLeft()).getMovie());
            return true;
        } else if (son.getRight() != null) { 
            son.setMovie(getLeftest(son, son.getRight()).getMovie());
            return true;
        }
    
        return false;
    }
    
    private Node getRightest(Node father, Node actPtr)
    {
        if (actPtr.getRight() != null) return getRightest(actPtr, actPtr.getRight());
        
        remove(father, actPtr);
        return actPtr;
    }
    
    private Node getLeftest(Node father, Node actPtr)
    {
        if (actPtr.getLeft().getLeft() != null) return getLeftest(actPtr, actPtr.getLeft());
        
        remove(father, actPtr);
        return actPtr;        
    }
    
    public Movies search(int Code)
    {
        return search(Code, root).getMovie();
    }
    
    private Node search(int Code, Node actPtr)
    {
        if (actPtr == null) return null;
        if (Code == actPtr.getCode()) return actPtr;
        
        if (Code > actPtr.getCode())
        {
            return search(Code, actPtr.getRight());
        }
        else return search(Code, actPtr.getLeft());
    }
    
    private Node searchFatherOf(int Code, Node actPtr)
    {
        if (actPtr == null) return null;
        
        if (Code > actPtr.getCode())
        {
            if (actPtr.getRight() != null && actPtr.getRight().getCode() == Code) return actPtr;
            return searchFatherOf(Code, actPtr.getRight());
        }
        else 
        {
            if (actPtr.getLeft() != null && actPtr.getLeft().getCode() == Code) return actPtr;
            return searchFatherOf(Code, actPtr.getLeft());
        }
    }    
    
}