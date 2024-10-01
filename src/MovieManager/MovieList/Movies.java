package MovieManager.MovieList;

public class Movies {
    private String MoviePosterPath;
    private String Notes;
    private String Name;
    private int Stars;
    
    public Movies(String _Name, String _Notes, String PosterPath, int _Stars)
    {
        MoviePosterPath = PosterPath;
        Notes = _Notes;
        Stars = _Stars;
        Name = _Name;
    }

    public String getPosterPath()
    { return MoviePosterPath; }

    public void setPosterPath(String Path) 
    { MoviePosterPath = Path; }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getStars() {
        return Stars;
    }

    public void setStars(int Stars) {
        this.Stars = Stars;
    }

    public void setAditionalData(String Description, int Stars)
    {
        this.Stars = Stars;
        this.Notes = Description;
    }
    
}
