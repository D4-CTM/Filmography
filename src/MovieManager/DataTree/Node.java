/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MovieManager.DataTree;

/**
 *
 * @author joshdelcid
 */
public class Node<T> implements Cloneable{
    private Node<T> Right;
    private Node<T> Left;
    private int Code;
    private T data;
    
    public Node(int _Code, T _data)
    {
        data = _data;
        Code = _Code;
        Right = null;
        Left = null;
    }

    public Node<T> getRight() {
        return Right;
    }

    public void setRight(Node<T> Right) {
        this.Right = Right;
    }

    public Node<T> getLeft() {
        return Left;
    }

    public void setLeft(Node<T> Left) {
        this.Left = Left;
    }

    public T getData() {
        return data;
    }

    public void setData(T _data) {
        this.data = _data;
    }
    
    public void setCode(int _Code) {
        Code = _Code;
    }

    public int getCode() {
        return Code;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }    

}
