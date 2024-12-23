package MovieReview.GUI;

import MovieManager.MovieList.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainMenu extends JPanel{
    //DATA USED WITHIN THIS FILE
    static protected String fontName;
    static protected int fontType;
    //DATA USED WITHIN THIS FILE
    public static MainMenu MENU;
    //CONSTANTS
    private final RegisterMovie RM;
    //CONSTANTS
    private int fontSize;
    //VARIABLES

    public MainMenu(RegisterMovie _RM, RegisterUser _RU, BucketMovies _BM, java.awt.Dimension Size)
    {
        MainMenu.MENU = this;

        setBackground(Color.decode("#FFF4EC"));
        RM = _RM;

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
                if (MovieNameTXT.getText().equals("Título de la pelicula") && MovieNameTXT.getForeground() == Color.lightGray)
                {
                    MovieNameTXT.setText("");
                    MovieNameTXT.setForeground(Color.BLACK);
                }
                showSearchIcon((int) MovieNameTXT.getPreferredSize().getHeight());
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (MovieNameTXT.getText().isBlank())
                {
                    clearMovieNameTXT();
                }
                showSearchIcon((int) MovieNameTXT.getPreferredSize().getHeight());
            }
            
        });
        
        searchMovieBTN = new javax.swing.JButton();
        searchMovieBTN.setBackground(Color.decode("#E7CFBC"));
        searchMovieBTN.addActionListener((java.awt.event.ActionEvent e) -> {
            searchMovie(MovieNameTXT.getText());
        });

        addMovieBTN = new javax.swing.JButton("Agregar pelicula");
        addMovieBTN.setBackground(Color.decode("#E7CFBC"));
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
        changeMoviesPNL.setLayout(new javax.swing.BoxLayout(changeMoviesPNL, javax.swing.BoxLayout.X_AXIS));
        changeMoviesPNL.setBackground(Color.decode("#E7CFBC"));
        
        final JPanel lastListPNL = new JPanel();
        lastListPNL.setLayout(new javax.swing.BoxLayout(lastListPNL, javax.swing.BoxLayout.Y_AXIS));
        lastListPNL.setOpaque(false);

        lastBTN = new javax.swing.JButton();
        lastBTN.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        lastBTN.setBackground(Color.decode("#E7CFBC"));
        lastBTN.addActionListener((java.awt.event.ActionEvent e) -> {
            MLPNL.showLastMovieList();
        });


        lastListPNL.add(lastBTN);
        changeMoviesPNL.add(lastListPNL);
        final JPanel nextListPNL = new JPanel();
        nextListPNL.setLayout(new javax.swing.BoxLayout(nextListPNL, javax.swing.BoxLayout.Y_AXIS));
        nextListPNL.setOpaque(false);

        nextBTN = new javax.swing.JButton();
        nextBTN.setAlignmentX(JPanel.RIGHT_ALIGNMENT);
        nextBTN.setBackground(Color.decode("#E7CFBC"));
        nextBTN.addActionListener((java.awt.event.ActionEvent e) -> {
            MLPNL.showNextMovieList();
        });

        nextListPNL.add(nextBTN);
        changeMoviesPNL.add(nextListPNL);
        
        MLPNL.showCurrentMovieList();

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
        lastBTN.setMaximumSize(searchMovieBTN.getMaximumSize());
        nextBTN.setMaximumSize(searchMovieBTN.getMaximumSize());
        lastBTN.setIcon(ImageRenderer.renderImage("./src/Images/ProgramImages/Left.png", SearchH, SearchH));
        nextBTN.setIcon(ImageRenderer.renderImage("./src/Images/ProgramImages/Right.png", SearchH, SearchH));

        fontSize = (Width + SearchH)/35;
        MovieNameTXT.setFont(new java.awt.Font(fontName, fontType, fontSize));
        addMovieBTN.setFont(new java.awt.Font(fontName, fontType, fontSize - fontSize/10));
        showSearchIcon(SearchH);
    }
    
    private void showSearchIcon(int Size) {
        searchMovieBTN.setIcon(ImageRenderer.renderImage(((MovieNameTXT.getForeground() == Color.lightGray) ? "./src/Images/ProgramImages/Reload.png" : "./src/Images/ProgramImages/Search.png"), Size, Size));
    }

    private void scaleInitialPNLS(final int Width, final int Height)
    {
        getComponents()[0].setMaximumSize(new java.awt.Dimension(Width, Height));
        getComponents()[0].setPreferredSize(getComponents()[0].getMaximumSize());

        lastBTN.getParent().setMaximumSize(new java.awt.Dimension(Width/2, Height));
        lastBTN.getParent().setPreferredSize(lastBTN.getParent().getMaximumSize());

        nextBTN.getParent().setMaximumSize(lastBTN.getParent().getMaximumSize());
        nextBTN.getParent().setPreferredSize(nextBTN.getParent().getMaximumSize());
        
        getComponents()[2].setMaximumSize(getComponents()[0].getMaximumSize());
        getComponents()[2].setPreferredSize(getComponents()[0].getPreferredSize());
        
    }
    
    private void searchMovie(String MovieName)
    {
        if (MovieNameTXT.getForeground() == Color.lightGray) 
        {
            MLPNL.showCurrentMovieList();
            return ;
        }

        MLPNL.showMovie(MovieName);
        clearMovieNameTXT();
    }

    private void clearMovieNameTXT()
    {
        MovieNameTXT.setText("Título de la pelicula");
        MovieNameTXT.setForeground(java.awt.Color.lightGray);
        showSearchIcon((int) MovieNameTXT.getPreferredSize().getHeight());
    }

    public static void updateMenu() {
        MENU.MLPNL.showCurrentMovieList();
    }

    //GUI ELEMENTS
    private final javax.swing.JTextField MovieNameTXT;
    private final javax.swing.JButton searchMovieBTN;
    private final javax.swing.JButton addMovieBTN;
    private final javax.swing.JScrollPane Scroll;
    private final MovieListPNL MLPNL;
    private final javax.swing.JButton lastBTN;
    private final javax.swing.JButton nextBTN;
    //GUI ELEMENTS
}
class MovieListPNL extends JPanel
{
    //CONSTANTS
    private final BucketMovies BM;
    //CONSTANTS

    public MovieListPNL(Color BG, RegisterMovie _RM, BucketMovies _BM)
    {
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
        setBackground(BG);
        BM = _BM;

        MLC = new MovieListComponenets[10];
        for (int i = 0; i < 10; i++) {
            MLC[i] = new MovieListComponenets(_RM, _BM, this);
            MLC[i].removeBTN.addActionListener((java.awt.event.ActionEvent e) -> {
                try {
                    String movieName = ((JButton) e.getSource()).getName();
                    removeMovie(movieName);
                } catch (CloneNotSupportedException ex) {}
            });
            MLC[i].setAlignmentX(JPanel.LEFT_ALIGNMENT);
            add(MLC[i]);
        }

    }

    public void showCurrentMovieList() {
        Movies[] Movie = BM.getCurrentMovieList();

        if (Movie == null) { 
            for (int i = 0; i < 10; i ++) MLC[i].setVisible(false);
            return ;
        }

        for (int i = 0; i < 10; i++) {
            if (Movie[i] != null) {
                MLC[i].setMovie(Movie[i]);
                MLC[i].setVisible(true);
            } else MLC[i].setVisible(false);
        }
    }

    public void showNextMovieList() {
        Movies[] Movie = BM.getNextMovieList();

        if (Movie == null) { 
            JOptionPane.showMessageDialog(getParent(), "No se han encontrado mas peliculas", "Buscar pelicula", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (Movie[i] != null) {
                MLC[i].setMovie(Movie[i]);
                MLC[i].setVisible(true);
            } else MLC[i].setVisible(false);
        }

        if (!MLC[0].isVisible()) {
            JOptionPane.showMessageDialog(getParent(), "No hay mas peliculas en la lista", "Siguiente", JOptionPane.WARNING_MESSAGE);
            showLastMovieList();
        }
    }

    public void showLastMovieList() {
        Movies[] Movie = BM.getLastMovieList();

        if (Movie == null) { 
            JOptionPane.showMessageDialog(getParent(), "No se han encontrado mas peliculas", "Buscar pelicula", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (Movie[i] != null) {
                MLC[i].setMovie(Movie[i]);
                MLC[i].setVisible(true);
            } else MLC[i].setVisible(false);
        }

        if (!MLC[0].isVisible()) {
            JOptionPane.showMessageDialog(getParent(), "No hay mas peliculas en la lista", "Anterior", JOptionPane.WARNING_MESSAGE);
            showNextMovieList();
        }
    }

    public void showMovie(String MovieName) {
        Movies Movie = BM.searchFor(MovieName);

        if (Movie == null) {
            JOptionPane.showMessageDialog(getParent(), "No se ha encontrado " + MovieName + "\nIntente escribiendo de nuevo el nombre", "Buscar pelicula", JOptionPane.INFORMATION_MESSAGE);
            return ;
        }

        MLC[0].setMovie(Movie);
        MLC[0].setVisible(true);
        for (int i = 1; i < 10; i++) {
            MLC[i].setVisible(false);
        }
    }

    private void removeMovie(String movieName) throws CloneNotSupportedException
    {
        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(getParent(), "¿Desea eliminar " + movieName + " de la lista de peliculas?\nSi realiza esto no podra recuperar la pelicula", "Eliminar pelicula", JOptionPane.WARNING_MESSAGE )) {
            String message = BM.remove(movieName) ? "Se la eliminado " + movieName + " correctamente" : "Ha ocurrido un error";

            JOptionPane.showMessageDialog(getParent(), message, "Eliminar pelicula", JOptionPane.INFORMATION_MESSAGE);
            showCurrentMovieList();
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
        private final MovieListPNL MLPNL;
        //CONSTANTS
        //VARIABLES
        private int fontSize;
        Movies Movie;
        //VARIABLES

        public MovieListComponenets(RegisterMovie _RM, BucketMovies _BM, MovieListPNL _MLPNL)
        {
            setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));
            setOpaque(false);
            MLPNL = _MLPNL;
            RM = _RM;

            MoviePoster = new javax.swing.JLabel();
            MoviePoster.setBorder(new javax.swing.border.LineBorder(Color.RED));
            add(MoviePoster);

            codeLBL = new javax.swing.JLabel("");
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

            MovieNameTXT = new javax.swing.JTextArea(" ");
            MovieNameTXT.setWrapStyleWord(true);
            MovieNameTXT.setEditable(false);
            MovieNameTXT.setLineWrap(true);
            MovieNameTXT.setOpaque(false);
            MovieNameTXT.setForeground(Color.BLACK);
            
            addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent evt) {
                    MovieNameTXT.setForeground(Color.gray);
                }

                @Override
                public void mouseExited(MouseEvent evt) {
                    MovieNameTXT.setForeground(Color.BLACK);
                }

                @Override
                public void mouseReleased(MouseEvent evt) {
                    if (RM.showPNL(MovieNameTXT.getText())) MLPNL.showCurrentMovieList();
                }
                
            });
            MovieNameTXT.addMouseListener(this.getMouseListeners()[0]);
            MovieNameTXT.setAlignmentX(JPanel.LEFT_ALIGNMENT);
            MovieInfoPNL.add(MovieNameTXT);

            add(MovieInfoPNL);
        }

        public void setMovie(Movies _Movie) {
            Movie = _Movie;
            MoviePoster.setIcon(ImageRenderer.renderImage(_Movie.getPosterPath(), (int) MoviePoster.getPreferredSize().getWidth(), (int) MoviePoster.getPreferredSize().getHeight()));
            starLBL.setText("Estrellas: " + _Movie.getStars());
            MovieNameTXT.setText(_Movie.getName());
            removeBTN.setName(_Movie.getName());
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
            if (Movie != null && !Movie.getPosterPath().isBlank()) MoviePoster.setIcon(ImageRenderer.renderImage(Movie.getPosterPath(), (int) Size.getWidth(), (int) Size.getHeight()));

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