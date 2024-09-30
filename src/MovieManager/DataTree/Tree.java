package MovieManager.DataTree;

import MovieManager.MovieList.Movies;
import java.util.LinkedList;
import java.util.Queue;

public class Tree {
    private Node<Movies> root;
    
    public Tree()
    { root = null; }

    public int getSize() {
        return getSize(root);
    }

    private int getSize(Node<Movies> actPtr) {
        if (actPtr == null) return 0;

        return (getSize(actPtr.getLeft()) + 1 + getSize(actPtr.getRight()));
    }

    public boolean add(String name, String description, int rating, int code) {
        Node<Movies> neoNode = new Node<>(code, new Movies(name, description, rating));
        if (root == null) {
            root = neoNode;
            return true;
        }

        return add(root, neoNode);
    }

    private boolean add(Node<Movies> actPtr, Node<Movies> neoPtr) {
        if (actPtr == null) return false;

        if (neoPtr.getCode() > actPtr.getCode()) {
            if (actPtr.getRight() != null) return add(actPtr.getRight(), neoPtr);
            actPtr.setRight(neoPtr);
            return true;
        } else if (neoPtr.getCode() < actPtr.getCode()) {
            if (actPtr.getLeft() != null) return add(actPtr.getLeft(), neoPtr);
            actPtr.setLeft(neoPtr);
            return true;
        }

        return false;
    }

    public Movies search(int Code) {
        return search(root, Code);
    }

    private Movies search(Node<Movies> actPtr, int Code) {
        if (actPtr == null) return null;

        if (actPtr.getCode() == Code) return actPtr.getData();

        if (Code > actPtr.getCode()) {
            return search(actPtr.getRight(), Code);
        } else {
            return search(actPtr.getLeft(), Code);
        }
    }

    public boolean remove(int Code) throws CloneNotSupportedException {
        if (root == null) return false;

        if (root.getCode() == Code) {
            return remove(null, root);
        }

        Node<Movies> father = searchFatherOf(root, Code);
        if (father == null) {
            System.out.println("FATHER WAS NOT FOUND, SON DOESN'T EXIST");
            System.out.println("ROOT: " + root.getData().getName());
            return false;
        }

        Node<Movies> son = (Code > father.getCode()) ? father.getRight() : father.getLeft();
        if (son == null) return false;

        return remove(father, son);
    }

    private boolean remove(Node<Movies> father, Node<Movies> son) throws CloneNotSupportedException {

        if (isLeaf(son)) {
            return removeLeafNode(father, son);
        }

        return removeChildNode(son);
    }

    private boolean removeChildNode(Node<Movies> son) throws CloneNotSupportedException {
        Node<Movies> auxNode;
        if (son.getLeft() == null) {
            auxNode = getLeftest(son, son.getRight());
        } else {
            auxNode = getRightest(son, son.getLeft());
        }

        if (son.getCode() == root.getCode()) {
            root.setData(auxNode.getData());
            root.setCode(auxNode.getCode());
        } else {
            son.setData(auxNode.getData());
            son.setCode(auxNode.getCode());
        }

        return true;
    }

    private boolean removeLeafNode(Node<Movies> father, Node<Movies> son) {
        if (root == son) {
            root = null;
            return true;
        } 

        if (father.getLeft() == son) {
            if (father.getCode() == root.getCode()) {
                root.setLeft(null);
            } else father.setLeft(null);
            return true;

        } else if (father.getRight() == son) {
            if (father == root) {
                root.setRight(null);
            } else father.setRight(null);
            return true;
        }

        return false;
    }

    private Node<Movies> searchFatherOf(Node<Movies> actPtr, int Code) {
        if (actPtr == null) return null;

        if (Code > actPtr.getCode()) 
        {
            if (actPtr.getRight() != null && actPtr.getRight().getCode() == Code) return actPtr;
            return searchFatherOf(actPtr.getRight(), Code);
        } 
        else 
        {
            if (actPtr.getLeft() != null && actPtr.getLeft().getCode() == Code) return actPtr;
            return searchFatherOf(actPtr.getLeft(), Code);
        }

    }

    private Node<Movies> getLeftest(Node<Movies> father, Node<Movies> son) throws CloneNotSupportedException {
        if (son.getLeft() != null) return getLeftest(son, son.getLeft());

        Node<Movies> cloneSon = (Node<Movies>) son.clone();
        remove(father, son);
        return cloneSon;
    }

    private Node<Movies> getRightest(Node<Movies> father, Node<Movies> son) throws CloneNotSupportedException {
        if (son.getRight() != null) return getRightest(son, son.getRight());

        Node<Movies> cloneSon = (Node<Movies>) son.clone();
        remove(father, son);
        return cloneSon;
    }

    private boolean isLeaf(Node<Movies> Node) 
    { return (Node.getLeft() == null && Node.getRight() == null); }

    public Node<Movies> getRoot()
    { return root; }

    public String getHierarchy()
    {
        if (root == null) return "ZERO";

        Queue<Node<Movies>> Q = new LinkedList<>();
        Node<Movies> auxNode;
        String result = "";
        int Size;
        Q.add(root);

        while (!Q.isEmpty()) {
            result += "[";
            
            for (Size = Q.size(); Size > 0; Size--)
            {
                auxNode = Q.poll();

                if (auxNode.getLeft() != null) Q.add(auxNode.getLeft());
                if (auxNode.getRight() != null) Q.add(auxNode.getRight());
                result += auxNode.getCode() + " : " + auxNode.getData().getName();
                if (Size != 1) result += ", ";
            }

            result += "]\n";
        }

        return result;
    }

}