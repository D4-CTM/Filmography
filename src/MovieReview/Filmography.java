package MovieReview;

import MovieManager.MovieList.BucketMovies;
import MovieReview.GUI.MainFrame;


public class Filmography {
    
    public static void main(String[] args) {
        new MainFrame(new BucketMovies()).setVisible(true);
    }
    
}