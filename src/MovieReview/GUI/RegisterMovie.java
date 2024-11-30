package MovieReview.GUI;

import MovieManager.MovieList.BucketMovies;
import MovieManager.MovieList.Movies;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RegisterMovie extends NewDataPanel{
    //CONSTANTS
    private final BucketMovies BM;
    private final JFileChooser fileChooser;
    //CONSTANTS
    //VARIABLES
    private String initialName;
    //VARIABLES

    public RegisterMovie(javax.swing.JPanel indexCard, java.awt.CardLayout CL,java.awt.Dimension Size, BucketMovies _BM) 
    {
        super(indexCard, CL, Size);
        
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "webp"));
        fileChooser.setDialogTitle("Poster selector");
        initialName = "";
        
        getCentralPanel().setLayout(new java.awt.GridLayout(1, 2));
        BM = _BM;

        final JPanel MoviePPNL = new JPanel(new java.awt.GridBagLayout());
        MoviePPNL.setOpaque(false);
        MoviePoster = new javax.swing.JLabel("[Inserte póster 2:3]");
        MoviePoster.setBorder(new javax.swing.border.LineBorder(Color.RED));
        MoviePoster.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        MoviePoster.setVerticalAlignment(javax.swing.JLabel.CENTER);
        MoviePoster.setForeground(Color.WHITE);
        MoviePoster.setName(" ");

        MoviePoster.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (fileChooser.showOpenDialog(new javax.swing.JFrame()) == JFileChooser.APPROVE_OPTION) {                    
                    java.io.File PosterFile = fileChooser.getSelectedFile();
                    String path = "./MovieImages/" + PosterFile.getName();

                    if (PosterFile.renameTo(new java.io.File(path))) {
                        if (!MoviePoster.getName().isBlank() && MoviePoster.getName().equals(fileChooser.getSelectedFile().getName())) {
                            new java.io.File(MoviePoster.getName()).delete();
                        }

                        MoviePoster.setText("");
                        MoviePoster.setName(path);
                        MoviePoster.setIcon(ImageRenderer.renderImage(path, (int) MoviePoster.getPreferredSize().getWidth(), (int) MoviePoster.getPreferredSize().getHeight()));
                    } else JOptionPane.showMessageDialog(null, "No se ha podido agregar el poster", "agregar poster", JOptionPane.INFORMATION_MESSAGE);

                }

            }
        });

        MoviePPNL.add(MoviePoster, new java.awt.GridBagConstraints());
        addComponentToCentralPanel(MoviePPNL);
        
        final JPanel MovieInfoPNL = new JPanel(new java.awt.GridLayout(3, 1));
        MovieInfoPNL.setOpaque(false);
        
        final JPanel MovieNamePNL = new JPanel(new java.awt.GridLayout(2,1));
        MovieNamePNL.setOpaque(false);
        MovieNameLBL = new javax.swing.JLabel("Título:");
        MovieNameLBL.setHorizontalTextPosition(javax.swing.JLabel.LEFT);
        MovieNameLBL.setVerticalTextPosition(javax.swing.JLabel.BOTTOM);
        MovieNameLBL.setForeground(Color.WHITE);
        MovieNameTXT = new javax.swing.JTextField();

        final JPanel MovieNamePNL1 = new JPanel();
        MovieNamePNL1.setOpaque(false);
        MovieNamePNL1.add(MovieNameLBL);
        MovieNamePNL.add(MovieNamePNL1);
        
        final JPanel MovieNamePNL2 = new JPanel();
        MovieNamePNL2.setOpaque(false);
        MovieNamePNL2.add(MovieNameTXT);
        MovieNamePNL.add(MovieNamePNL2);
        MovieInfoPNL.add(MovieNamePNL);
        
        final JPanel DescriptionPNL = new JPanel();
        DescriptionPNL.setLayout(new javax.swing.BoxLayout(DescriptionPNL, javax.swing.BoxLayout.Y_AXIS));
        DescriptionPNL.setOpaque(false);
        DescriptionLBL = new javax.swing.JLabel("Descripción:");
        DescriptionLBL.setHorizontalTextPosition(javax.swing.JLabel.LEFT);
        DescriptionLBL.setForeground(Color.WHITE);
        final JPanel DescriptionPNL1 = new JPanel();
        DescriptionPNL1.setOpaque(false);
        DescriptionPNL1.add(DescriptionLBL);
        DescriptionPNL.add(DescriptionPNL1);
        
        DescriptionTXT = new javax.swing.JTextArea();
        DescriptionTXT.setWrapStyleWord(true);
        DescriptionTXT.setLineWrap(true);
        
        final javax.swing.JScrollPane Scrollable = new javax.swing.JScrollPane(DescriptionTXT);
        Scrollable.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Scrollable.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        final JPanel DescriptionPNL2 = new JPanel();
        DescriptionPNL2.setOpaque(false);
        DescriptionPNL2.add(Scrollable);
        DescriptionPNL.add(DescriptionPNL2);

        MovieInfoPNL.add(DescriptionPNL);
        
        StarsPNL = new Rating();
        MovieInfoPNL.add(StarsPNL);
        
        addComponentToCentralPanel(MovieInfoPNL);
    }

    @Override
    public void scaleComponents()
    {
        final int HCP = (int) getCentralPanelSize().getHeight()/2;
        final int WCP = (int) getCentralPanelSize().getWidth();
        
        MoviePoster.setPreferredSize(new java.awt.Dimension(HCP/2 + HCP/4, HCP + HCP/2));
        MovieNameLBL.setPreferredSize(new java.awt.Dimension(WCP/2 - WCP/20, HCP/2));
        MovieNameTXT.setPreferredSize(new java.awt.Dimension(WCP/2 - WCP/20, (HCP*2)/9));
        DescriptionLBL.setPreferredSize(new java.awt.Dimension(WCP/2 - WCP/20, (HCP*2)/18));
        DescriptionTXT.getParent().setPreferredSize(new java.awt.Dimension(WCP/2 - WCP/20, (HCP*2)/5));
        MoviePoster.setIcon(ImageRenderer.renderImage(MoviePoster.getName(), HCP/2 + HCP/4, HCP + HCP/2));
        StarsPNL.scaleComponents(WCP, HCP, BTNFont);

        
        MovieNameLBL.setFont(BTNFont);
        MovieNameTXT.setFont(BTNFont);
        DescriptionLBL.setFont(BTNFont);
        DescriptionTXT.setFont(BTNFont);
    }

    @Override
    public void acceptBTNPressed()
    {
        String MovieName = MovieNameTXT.getText();
        if (MovieName.isBlank()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor ingrese el titulo de la pelicula", "Titulo vacio", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return ;
        }
        
        String Description = DescriptionTXT.getText();

        try {
            identifyCase(MovieName, MoviePoster.getName(), Description);
        } catch (CloneNotSupportedException ex) {}
        
    }

    private void identifyCase(String MovieName, String MoviePoster, String Description) throws CloneNotSupportedException
    {
        final String intention;
        final String extra;
        final String title;

        if (initialName.equals(MovieName))
        {
            BM.searchFor(MovieName).setAditionalData(Description, StarsPNL.getStars());
            intention = "modificado los datos de la pelicula exitosamente";
            extra = "\nDesea seguir modificando los datos?";
            title = "Modificar pelicula";
            BM.update(initialName);
            initialName = MovieName;            
        } else if (!initialName.isBlank()) {
            intention = "modificado " + initialName + ", nuevo nombre: " + MovieName;
            title = "Modificar pelicula";
            extra = "\nDesea seguir modificando los datos?";
            BM.update(initialName);
            initialName = MovieName;
        } else {
            intention = "agregado " + MovieName + " exitosamente!";
            title = "Agregar pelicula";
            extra = "\nDesea agregar una pelicula mas?";
        }

        if (BM.add(MovieName, Description, MoviePoster, StarsPNL.getStars())) {
        
            if (javax.swing.JOptionPane.showConfirmDialog(this, "Se ha " + intention  + extra, title, javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.INFORMATION_MESSAGE) == javax.swing.JOptionPane.NO_OPTION) {
                MainMenu.updateMenu();
                CL.show(indexCard, "Main Menu"); 
            }
            clearData();

        } else javax.swing.JOptionPane.showMessageDialog(this, "No se ha logrado concretar la operacion.\nIntente ingresando otro nombre", title, javax.swing.JOptionPane.WARNING_MESSAGE);

    }

    @Override
    public void cancelBTNPressed() { 
        MainMenu.updateMenu();
        CL.show(indexCard, "Main Menu"); 
    }

    private void clearData() {
        MoviePoster.setText("[Inserte poster 2:3]");
        MoviePoster.setName(" ");
        MoviePoster.setIcon(null);
        DescriptionTXT.setText("");
        MovieNameTXT.setText("");
        StarsPNL.restartCamps();
    }

    public final void showPNL() {
        clearData();
        initialName = "";
        CL.show(indexCard, "Register Movie");
    }
    
    public final boolean showPNL(String MovieName) {
        Movies Movie = BM.searchFor(MovieName);
        if (Movie != null)
        {
            MoviePoster.setIcon(ImageRenderer.renderImage(Movie.getPosterPath(),(int) MoviePoster.getPreferredSize().getWidth(), (int) MoviePoster.getPreferredSize().getHeight()));
            MoviePoster.setName(Movie.getPosterPath());
            MoviePoster.setText("");
            DescriptionTXT.setText(Movie.getNotes());
            MovieNameTXT.setText(Movie.getName());
            StarsPNL.setStars(Movie.getStars());
            initialName = MovieName;

            CL.show(indexCard, "Register Movie");
            return true;
        }
        return false;
    }

    //GUI ELEMENTS
    private final javax.swing.JLabel MoviePoster;
    private final javax.swing.JLabel MovieNameLBL;
    private final javax.swing.JTextField MovieNameTXT;
    private final javax.swing.JLabel DescriptionLBL;
    private final javax.swing.JTextArea DescriptionTXT;
    private final Rating StarsPNL;
    //GUI ELEMENTS

    private class Rating extends JPanel
    {
        //VARIABLES
        private Color BSelected;
        private Color Selected;
        private Color BFree;
        private Color Free;
        private int rating;
        //VARIABLES
        
        public Rating()
        {
            RatingLBL = new javax.swing.JLabel("Calificación:");
            Stars = new javax.swing.JButton[5];
            for (int i = 0; i < 5; i++) { Stars[i] = new javax.swing.JButton(); }
            BSelected = Color.RED;
            Selected = Color.YELLOW;
            BFree = Color.BLACK;
            Free = Color.LIGHT_GRAY;
            initComponents();
        }
        
        private void initComponents()
        {
            setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
            setOpaque(false);
            RatingLBL.setHorizontalTextPosition(javax.swing.JLabel.LEFT);
            RatingLBL.setForeground(Color.WHITE);
            
            final JPanel RatingLBL_PNL = new JPanel();
            RatingLBL_PNL.setOpaque(false);
            RatingLBL_PNL.add(RatingLBL);
            final JPanel RatingBTN_PNL = new JPanel();
            RatingBTN_PNL.setOpaque(false);
            for (int i = 0; i < 5; i++) 
            {
                RatingBTN_PNL.add(Stars[i]);
                Stars[i].setFocusable(false);
                Stars[i].setName(String.valueOf(i + 1));
                Stars[i].addActionListener((java.awt.event.ActionEvent e) -> {
                    setStars(Integer.parseInt(((javax.swing.JButton) e.getSource()).getName()));
                });
            }
            setStars(-2);
            
            add(RatingLBL_PNL);
            add(RatingBTN_PNL);
            add(new java.awt.Container());
            add(new java.awt.Container());
        }

        public void scaleComponents(final int Width, final int Height, final java.awt.Font Font)
        {
            RatingLBL.setPreferredSize(new java.awt.Dimension(Width/2 - Width/20, Height/8));
            final int Proportions = (Width/2)/5 - (Width/2)/35;
            final java.awt.Dimension StarSize = new java.awt.Dimension(Proportions, Proportions);
            for (JButton Star : Stars) Star.setPreferredSize(StarSize);
            
            RatingLBL.setFont(Font);
        }
        
        protected void setStars(int _Rating)
        {
            if (_Rating == rating) {
                Stars[_Rating - 1].setBorder(new javax.swing.border.LineBorder(BFree, 4));
                Stars[_Rating - 1].setBackground(Free);
                
                rating--;
            } else {
                for (int i = 4; i >= 0; i--)
                {
                    if (i + 1 <= _Rating)
                    {
                        Stars[i].setBorder(new javax.swing.border.LineBorder(BSelected, 4));
                        Stars[i].setBackground(Selected);
                    }
                    else
                    {
                        Stars[i].setBorder(new javax.swing.border.LineBorder(BFree, 4));
                        Stars[i].setBackground(Free);
                    }
                }
    
                rating = _Rating;
            }
        }
        
        protected int getStars()
        { return (rating <= 0 ) ? 0 : rating; }
        
        protected void restartCamps()
        { setStars(-2); }
        
        //GUI ELEMENTS
        private final javax.swing.JButton[] Stars;
        private final javax.swing.JLabel RatingLBL;
        //GUI ELEMENTS
    }
}
