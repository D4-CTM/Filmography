package MovieManager.MovieList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MovieFileSaver {
    private final String ROOT;
    private RandomAccessFile MovieSaver;

    public MovieFileSaver(String root) {
        ROOT = root;
        File FM = new File(ROOT);
        if (!FM.exists()) {
            FM.mkdirs();
        }
    }

    public void saveMovie(String MovieName, String Description, String PosterPath, int Rating) {
        try {
            MovieSaver = new RandomAccessFile(ROOT + "/" + MovieName + ".Film", "rw");
            MovieSaver.writeUTF(MovieName);
            MovieSaver.writeUTF(Description == null ? " " : Description);
            MovieSaver.writeUTF(PosterPath == null ? " " : PosterPath);            
            MovieSaver.writeInt(Rating);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Movies[] readMovies() {
        File FM = new File(ROOT);
        if (!FM.exists()) {
            return null;
        }

        if (!FM.isDirectory()) {
            return null;
        }

        return getMovies(FM.list());
    }

    private Movies[] getMovies(String[] Names) {
        Movies[] Movie = new Movies[Names.length];

        int i = 0;
        for (String movie : Names) {
            try {
                Movie[i] = readFrom(ROOT + "/" + movie);
                i++;
            } catch (IOException e) {
            }
        }
        return Movie;
    }

    private Movies readFrom(String path) throws IOException {
        MovieSaver = new RandomAccessFile(path, "r");
        String MovieName = MovieSaver.readUTF();
        String Description = MovieSaver.readUTF();
        String PosterPath = MovieSaver.readUTF();
        int Stars = MovieSaver.readInt();
        return new Movies(MovieName, Description, PosterPath, Stars);
    }

    public boolean remove(String MovieName) {
        File FM = new File(ROOT + "/" + MovieName + ".Film");
        return FM.delete();
    }
    
    public boolean remove(String MovieName, String MoviePath) {
        File FM = new File(ROOT + "/" + MovieName + ".Film");
        new File(MoviePath).delete();
        return FM.delete();
    }

}
