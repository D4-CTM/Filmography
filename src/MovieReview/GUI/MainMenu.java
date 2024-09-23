package MovieReview.GUI;

import java.awt.Color;
import javax.swing.JPanel;

public class MainMenu extends JPanel{
    //CONSTANTS
    private final java.awt.CardLayout CL;
    private final JPanel indexCard;
    private final String fontName;
    private final int fontType;
    //CONSTANTS
    //VARIABLES
    private java.awt.Color PNLBG;
    private int fontSize;
    //VARIABLES

    
    public MainMenu(JPanel _indexCard, java.awt.CardLayout _CL, java.awt.Dimension Size)
    {
        setBackground(Color.DARK_GRAY);
        indexCard = _indexCard;
        CL = _CL;
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
        
        searchMovieBTN = new javax.swing.JButton();
        searchBarPNL.add(MovieNameTXT);
        searchBarPNL.add(searchMovieBTN);
        
        
        JPanel movieListPNL = new JPanel();
        movieListPNL.setOpaque(false);
        
        JPanel changeMoviesPNL = new JPanel();
        changeMoviesPNL.setOpaque(false);
        
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
    
    //GUI ELEMENTS
    private final javax.swing.JTextField MovieNameTXT;
    private final javax.swing.JButton searchMovieBTN;
    
    //GUI ELEMENTS
}