package MovieManager.MovieList;

import MovieManager.DataTree.Tree;

public class BucketMovies {
    private final int BUCKET_SIZE = 13;
    private final MovieFileSaver MFS;
    private final MoviesList ML;
    private final Tree[] Bucket;

    public BucketMovies()
    {
        MFS = new MovieFileSaver("./MovieList");
        ML = new MoviesList();

        Bucket = new Tree[BUCKET_SIZE];
        for (int i = 0; i < BUCKET_SIZE; i++) {
            Bucket[i] = new Tree();
        }

        Movies[] Movie = MFS.readMovies();
        if (Movie == null) {
            return ;
        }
        int Code;
        for (Movies movies : Movie) {
            Code = strToInt(movies.getName().toLowerCase());
            Bucket[hash(movies.getName())].add(movies, Code);
            ML.add(movies, Code);
        }
    }
    
    public boolean add(String movieName, String description, String PosterPath, int Rating)
    {
        int index = hash(movieName);
        int Code = strToInt(movieName.toLowerCase());
        if (Bucket[index].add(movieName, description, PosterPath, Rating, Code)) {
            ML.add(Bucket[index].search(Code), Code);
            MFS.saveMovie(movieName, description, PosterPath, Rating);
            return true;
        }
        return false;
    }

    private int strToInt(String Text) {
        int value = 1;
        for (char C : Text.toCharArray()) {
            value += (int) C;
        }
        return value;
    }
    
    private int hash(String movieName)
    {
        int Hash = 1;
        for (int i = 0; i < movieName.length(); i++) {
            Hash += (BUCKET_SIZE * Hash * (int) movieName.charAt(i))^BUCKET_SIZE;
        }
        return java.lang.Math.abs(Hash % BUCKET_SIZE);
    }
    public Movies searchFor(String movieName)
    {
        if (movieName == null) return null;
        if (movieName.isBlank()) return null;
        
        int index = hash(movieName);
        int Code = strToInt(movieName.toLowerCase());
        return Bucket[index].search(Code);
        }
        
    public boolean remove(String movieName) throws CloneNotSupportedException
    {
        if (movieName == null) return false;
        if (movieName.isBlank()) return false;
        
        int index = hash(movieName);
        int Code = strToInt(movieName.toLowerCase());
        String MoviePath = searchFor(movieName).getPosterPath();
        if (Bucket[index].remove(Code)) {
            MFS.remove(movieName, MoviePath);
            ML.remove(Code);
            return true;
        }
        return false;
    }

    public Movies[] getNextMovieList()
    { return ML.getNextMovieList(); }

    public Movies[] getLastMovieList()
    { return ML.getLastMovieList(); }

    public Movies[] getCurrentMovieList()
    { return ML.getCurrentMovieList(); }

    public void alphabeticalSort()
    { ML.alphabeticalSort(); }

}