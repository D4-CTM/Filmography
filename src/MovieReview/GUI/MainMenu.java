package MovieReview.GUI;

import MovieManager.MovieList.*;
import java.awt.Color;
import javax.swing.JPanel;

public class MainMenu extends JPanel{
    //CONSTANTS
    private final RegisterMovie RM;
    private final RegisterUser RU;
    private final String fontName;
    private final BucketMovies BM;
    private final int fontType;
    //CONSTANTS
    //VARIABLES
    private java.awt.Color PNLBG;
    private int fontSize;
    //VARIABLES

    public MainMenu(RegisterMovie _RM, RegisterUser _RU, BucketMovies _BM, java.awt.Dimension Size)
    {
        setBackground(Color.DARK_GRAY);
        BM = _BM;
        RM = _RM;
        RU = _RU;

        fontType = java.awt.Font.PLAIN;
        fontName = "Droid Sans";
        
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
        
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                scaleComponents(getSize());
                revalidate();
                repaint();
            }
        });

        JPanel searchBarPNL = new JPanel();
        searchBarPNL.setLayout(new javax.swing.BoxLayout(searchBarPNL, javax.swing.BoxLayout.X_AXIS));
        searchBarPNL.setOpaque(false);
        MovieNameTXT = new javax.swing.JTextField();
        MovieNameTXT.addFocusListener(new java.awt.event.FocusListener() {

            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (MovieNameTXT.getText().equals("Titulo de la pelicula") && MovieNameTXT.getForeground() == Color.lightGray)
                {
                    MovieNameTXT.setText("");
                    MovieNameTXT.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (MovieNameTXT.getText().isBlank())
                {
                    clearMovieNameTXT();
                }
            }
            
        });
        
        searchMovieBTN = new javax.swing.JButton();
        searchMovieBTN.addActionListener((java.awt.event.ActionEvent e) -> {
            searchMovie(MovieNameTXT.getText());
        });

        searchBarPNL.add(MovieNameTXT);
        searchBarPNL.add(searchMovieBTN);
        
        
        JPanel movieListPNL = new JPanel();
        movieListPNL.setOpaque(false);
        
        JPanel changeMoviesPNL = new JPanel();
        changeMoviesPNL.setOpaque(false);
        
        clearMovieNameTXT();
        add(searchBarPNL);
        add(movieListPNL);
        add(changeMoviesPNL);
    }

    private void scaleComponents(java.awt.Dimension Size)
    {
        final int Width = (int) Size.getWidth();
        final int SearchH = (int) Size.getHeight()/15;
        final int ListH = (int) (Size.getHeight() - Size.getHeight()/15);
        scaleInitialPNLS(Width, SearchH);
        
        MovieNameTXT.setMaximumSize(new java.awt.Dimension((Width/2) + (Width/6), SearchH));
        searchMovieBTN.setMaximumSize(new java.awt.Dimension(SearchH, SearchH));
        fontSize = (Width + SearchH)/30;
        MovieNameTXT.setFont(new java.awt.Font(fontName, fontType, fontSize));
        
    }
    
    private void scaleInitialPNLS(final int Width, final int Height)
    {
        getComponents()[0].setMaximumSize(new java.awt.Dimension(Width, Height));
        getComponents()[0].setPreferredSize(getComponents()[0].getMaximumSize());
        
        getComponents()[2].setMaximumSize(getComponents()[0].getMaximumSize());
        getComponents()[2].setPreferredSize(getComponents()[0].getPreferredSize());
        
    }
    
    private void searchMovie(String MovieName)
    {
        if (MovieNameTXT.getForeground() == Color.lightGray) 
        {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor ingrese el nombre de una pelicula es el buscador", "Buscar pelicula", javax.swing.JOptionPane.WARNING_MESSAGE);
            return ;
        }
        
        if (!RM.showPNL(MovieName)) 
        {
            javax.swing.JOptionPane.showMessageDialog(this, "No se ha encontrado \'" + MovieName + "\' revise haber escrito bien el titulo", "Buscar pelicula", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
        
    }

    private void clearMovieNameTXT()
    {
        MovieNameTXT.setText("Titulo de la pelicula");
        MovieNameTXT.setForeground(java.awt.Color.lightGray);
    }

    //GUI ELEMENTS
    private final javax.swing.JTextField MovieNameTXT;
    private final javax.swing.JButton searchMovieBTN;
    
    //GUI ELEMENTS
}