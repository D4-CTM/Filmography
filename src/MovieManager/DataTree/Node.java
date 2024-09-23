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
public class Node {
    private Node Right;
    private Node Left;
    private Movies Movie;
    
    public Node(Movies _data)
    {
        Movie = _data;
        Right = null;
        Left = null;
    }

    public Node getRight() {
        return Right;
    }

    public void setRight(Node Right) {
        this.Right = Right;
    }

    public Node getLeft() {
        return Left;
    }

    public void setLeft(Node Left) {
        this.Left = Left;
    }

    public Movies getMovie() {
        return Movie;
    }

    public void setMovie(Movies data) {
        this.Movie = data;
    }
    
    public int getCode() {
        return this.Movie.getCode();
    }
    
}
