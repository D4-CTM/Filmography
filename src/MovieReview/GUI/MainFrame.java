package MovieReview.GUI;

import MovieManager.MovieList.BucketMovies;
import javax.swing.JPanel;

public class MainFrame extends javax.swing.JFrame{
    //CONSTANTS
    private final java.awt.Dimension MinDimension = new java.awt.Dimension(600, 600);
    private final java.awt.CardLayout CrdLayout = new java.awt.CardLayout();
    //CONSTANTS
    
    public MainFrame(BucketMovies BM)
    {
        indexCard = new JPanel(CrdLayout);
        RM = new RegisterMovie(indexCard, CrdLayout, MinDimension, BM);
        RU = new RegisterUser(indexCard, CrdLayout, MinDimension);
        MM = new MainMenu(indexCard, CrdLayout, MinDimension);
        initComponents();
    }
    
    private void initComponents()
    {
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setTitle("Filmography");
        
        indexCard.setPreferredSize(MinDimension);
        indexCard.add(MM, "Main Menu");
        indexCard.add(RM, "Register Movie");
        indexCard.add(RU, "Register Users");
        add(indexCard);
        CrdLayout.show(indexCard, "Main Menu");
        
        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
    }

    //GUI ELEMENTS
    private final JPanel indexCard;
    private final RegisterMovie RM;
    private final RegisterUser RU;
    private final MainMenu MM;
    //GUI ELEMENTS
}