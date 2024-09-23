/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MovieManager.MovieList;

/**
 *
 * @author joshdelcid
 */
public class Movies {
    private String Notes;
    private String Name;
    private int Stars;
    private int Code;
   
    public Movies(String _Name, int _Stars, int _Code)
    {
        Stars = _Stars;
        Name = _Name;
        Code = _Code;
    }
    
    public Movies(String _Name, String _Notes, int _Stars, int _Code)
    {
        Notes = _Notes;
        Stars = _Stars;
        Name = _Name;
        Code = _Code;
    }

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

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    
    
}
