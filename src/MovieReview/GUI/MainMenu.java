package MovieReview.GUI;

import MovieManager.MovieList.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class MainMenu extends JPanel{
    //DATA USED WITHIN THIS FILE
    static protected String fontName;
    static protected int fontType;
    //DATA USED WITHIN THIS FILE
    //CONSTANTS
    private final RegisterUser RU;
    private final RegisterMovie RM;
    //CONSTANTS
    //VARIABLES
    private java.awt.Color PNLBG;
    private int fontSize;
    //VARIABLES

    public MainMenu(RegisterMovie _RM, RegisterUser _RU, BucketMovies _BM, java.awt.Dimension Size)
    {
        setBackground(Color.GRAY);
        RM = _RM;
        RU = _RU;

        MainMenu.fontType = java.awt.Font.PLAIN;
        MainMenu.fontName = "Droid Sans";
        
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

        addMovieBTN = new javax.swing.JButton("Agregar pelicula");
        addMovieBTN.addActionListener((java.awt.event.ActionEvent e) -> {
            RM.showPNL();
        });

        searchBarPNL.add(MovieNameTXT);
        searchBarPNL.add(searchMovieBTN);
        searchBarPNL.add(addMovieBTN);

        MLPNL = new MovieListPNL(getBackground(), _RM, _BM);
        Scroll = new javax.swing.JScrollPane(MLPNL);
        Scroll.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Scroll.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel changeMoviesPNL = new JPanel();
        changeMoviesPNL.setOpaque(false);
        
        clearMovieNameTXT();
        add(searchBarPNL);
        add(Scroll);
        add(changeMoviesPNL);
    }

    private void scaleComponents(java.awt.Dimension Size)
    {
        final int Width = (int) Size.getWidth();
        final int SearchH = (int) Size.getHeight()/15;
        final int ListH = (int) (Size.getHeight() - Size.getHeight()/15);
        MLPNL.scaleComponents(Width - 20, ListH/6 + ListH/49);
        scaleInitialPNLS(Width, SearchH);
        
        MovieNameTXT.setMaximumSize(new java.awt.Dimension((Width/2) + (Width/6), SearchH));
        searchMovieBTN.setMaximumSize(new java.awt.Dimension(SearchH, SearchH));
        addMovieBTN.setMaximumSize(new java.awt.Dimension((int) (Width - MovieNameTXT.getMaximumSize().getWidth() + SearchH), SearchH));
        fontSize = (Width + SearchH)/35;
        MovieNameTXT.setFont(new java.awt.Font(fontName, fontType, fontSize));
        addMovieBTN.setFont(new java.awt.Font(fontName, fontType, fontSize - fontSize/10));
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

        System.out.println("WORK IN PROGRESS");
    }

    private void clearMovieNameTXT()
    {
        MovieNameTXT.setText("Titulo de la pelicula");
        MovieNameTXT.setForeground(java.awt.Color.lightGray);
    }

    //GUI ELEMENTS
    private final javax.swing.JTextField MovieNameTXT;
    private final javax.swing.JButton searchMovieBTN;
    private final javax.swing.JButton addMovieBTN;
    private final javax.swing.JScrollPane Scroll;
    private final MovieListPNL MLPNL;
    //GUI ELEMENTS
}
class MovieListPNL extends JPanel
{

    public MovieListPNL(Color BG, RegisterMovie _RM, BucketMovies _BM)
    {
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
        setBackground(BG);

        MLC = new MovieListComponenets[10];
        for (int i = 0; i < 10; i++) {
            MLC[i] = new MovieListComponenets(_RM, _BM);
            MLC[i].setAlignmentX(JPanel.LEFT_ALIGNMENT);
            add(MLC[i]);
        }
    }

    public void scaleComponents(final int Width, final int Height)
    {
        for (int i = 0; i < 10; i++) MLC[i].scaleComponents(Width, Height);
    }

    //GUI ELEMENTS
    private final MovieListComponenets[] MLC;
    //GUI ELEMENTS
    private class MovieListComponenets extends JPanel
    {
        //CONSTANTS
        private final RegisterMovie RM;
        private final BucketMovies BM;
        //CONSTANTS
        //VARIABLES
        private int fontSize;
        Movies Movie;
        //VARIABLES

        public MovieListComponenets(RegisterMovie _RM, BucketMovies _BM)
        {
            setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));
            setOpaque(false);
            RM = _RM;
            BM = _BM;

            MoviePoster = new javax.swing.JLabel();
            MoviePoster.setBorder(new javax.swing.border.LineBorder(Color.RED));
            add(MoviePoster);

            codeLBL = new javax.swing.JLabel("Code: 10290");
            codeLBL.setHorizontalTextPosition(javax.swing.JLabel.LEFT);

            starLBL = new javax.swing.JLabel("Estrellas: 5");
            starLBL.setHorizontalAlignment(javax.swing.JLabel.LEFT);

            removeBTN = new javax.swing.JButton();

            final JPanel MovieInfoPNL = new JPanel();
            MovieInfoPNL.setLayout(new javax.swing.BoxLayout(MovieInfoPNL, javax.swing.BoxLayout.Y_AXIS));
            MovieInfoPNL.setOpaque(false);

            final JPanel MovieInfoheader = new JPanel();
            MovieInfoheader.setLayout(new javax.swing.BoxLayout(MovieInfoheader, javax.swing.BoxLayout.X_AXIS));
            MovieInfoheader.setAlignmentX(JPanel.LEFT_ALIGNMENT);
            MovieInfoheader.setOpaque(false);

            MovieInfoheader.add(codeLBL);
            MovieInfoheader.add(starLBL);
            MovieInfoheader.add(removeBTN);
            MovieInfoPNL.add(MovieInfoheader);

            MovieNameTXT = new javax.swing.JTextArea("The Assassination of Jesse James by the Coward Robert Ford");
            MovieNameTXT.setWrapStyleWord(true);
            MovieNameTXT.setEditable(false);
            MovieNameTXT.setLineWrap(true);
            MovieNameTXT.setOpaque(false);
            MovieNameTXT.setForeground(Color.BLACK);
            
            addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent evt) {
                    MovieNameTXT.setForeground(Color.WHITE);
                }

                @Override
                public void mouseExited(MouseEvent evt) {
                    MovieNameTXT.setForeground(Color.BLACK);
                }

                @Override
                public void mouseReleased(MouseEvent evt) {
                    if (!RM.showPNL(MovieNameTXT.getText())) System.out.println("No se ha ingresado la peli");
                }
                
            });
            MovieNameTXT.addMouseListener(this.getMouseListeners()[0]);
            MovieNameTXT.setAlignmentX(JPanel.LEFT_ALIGNMENT);
            MovieInfoPNL.add(MovieNameTXT);

            add(MovieInfoPNL);
        }

        public void scaleComponents(final int Width, final int Height)
        {
            java.awt.Dimension Size = new java.awt.Dimension(Width, Height/3);
            codeLBL.getParent().setMaximumSize(Size);
            codeLBL.getParent().setMinimumSize(Size);
            codeLBL.getParent().setPreferredSize(Size);

            Size = new java.awt.Dimension(Height - Height/3, Height);
            MoviePoster.setMinimumSize(Size);
            MoviePoster.setMaximumSize(Size);
            MoviePoster.setPreferredSize(Size);

            Size = new java.awt.Dimension((Width - Height)/2, Height);
            fontSize = (int) (Size.getWidth()/2 + Size.getHeight())/20;

            codeLBL.setMinimumSize(Size);
            codeLBL.setMaximumSize(Size);
            codeLBL.setPreferredSize(Size);            
            codeLBL.setFont(new java.awt.Font(MainMenu.fontName, MainMenu.fontType, fontSize));            

            starLBL.setMinimumSize(Size);
            starLBL.setMaximumSize(Size);
            starLBL.setPreferredSize(Size);
            starLBL.setFont(new java.awt.Font(MainMenu.fontName, MainMenu.fontType, fontSize + 5));            

            Size = new java.awt.Dimension((int) Width - (Height - Height/3), (Height - Height/3));
            fontSize = (int) (Size.getWidth()/2 + Size.getHeight())/((Size.getWidth()/4 >= 400) ? 18 : 15);
            System.out.println(Size.getWidth());
            MovieNameTXT.setMinimumSize(Size);
            MovieNameTXT.setMaximumSize(Size);
            MovieNameTXT.setPreferredSize(Size);
            MovieNameTXT.setFont(new java.awt.Font(MainMenu.fontName, MainMenu.fontType, fontSize + 5));            

            Size = new java.awt.Dimension(Height, Height);
            removeBTN.setMinimumSize(Size);
            removeBTN.setMaximumSize(Size);
            removeBTN.setPreferredSize(Size);

            Size = new java.awt.Dimension(Width, Height);
            setMinimumSize(Size);
            setMaximumSize(Size);
            setPreferredSize(Size);
            revalidate();
            repaint();
        }

        //GUI ELEMENTS
        private final javax.swing.JTextArea MovieNameTXT;
        private final javax.swing.JLabel MoviePoster;
        private final javax.swing.JButton removeBTN;
        private final javax.swing.JLabel starLBL;
        private final javax.swing.JLabel codeLBL;
        //GUI ELEMENTS
    }

}