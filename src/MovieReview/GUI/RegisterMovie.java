package MovieReview.GUI;

import MovieManager.MovieList.BucketMovies;
import MovieManager.MovieList.Movies;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

public class RegisterMovie extends NewDataPanel{
    //CONSTANTS
    private final BucketMovies BM;
    //CONSTANTS
    //VARIABLES
    private String initialName;
    //VARIABLES

    public RegisterMovie(javax.swing.JPanel indexCard, java.awt.CardLayout CL,java.awt.Dimension Size, BucketMovies _BM) 
    {
        super(indexCard, CL, Size);
        initialName = "";
        
        getCentralPanel().setLayout(new java.awt.GridLayout(1, 2));
        BM = _BM;

        final JPanel MoviePPNL = new JPanel(new java.awt.GridBagLayout());
        MoviePPNL.setOpaque(false);
        MoviePoster = new javax.swing.JLabel();
        MoviePoster.setBorder(new javax.swing.border.LineBorder(Color.RED));
        
        MoviePPNL.add(MoviePoster, new java.awt.GridBagConstraints());
        addComponentToCentralPanel(MoviePPNL);
        
        final JPanel MovieInfoPNL = new JPanel(new java.awt.GridLayout(3, 1));
        MovieInfoPNL.setOpaque(false);
        
        final JPanel MovieNamePNL = new JPanel(new java.awt.GridLayout(2,1));
        MovieNamePNL.setOpaque(false);
        MovieNameLBL = new javax.swing.JLabel("Titulo:");
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
        DescriptionLBL = new javax.swing.JLabel("Descripcion:");
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

        identifyCase(MovieName, Description);
    }

    private void identifyCase(String MovieName, String Description)
    {
        final String intention;
        final String extra;
        final String title;

        if (initialName.equals(MovieName))
        {
            BM.searchFor(MovieName).setAditionalData(Description, StarsPNL.getStars());
            javax.swing.JOptionPane.showMessageDialog(this, "Se han modificado los datos de la pelicula exitosamente", "Modificar pelicula", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            initialName = MovieName;
            return ;
        }

        if (!initialName.isBlank()) {
            intention = "modificado " + initialName;
            title = "Modificar pelicula";
            extra = "\nDesea seguir modificando los datos?";
            BM.remove(initialName);
            initialName = MovieName;
        } else {
            intention = "agregado";
            title = "Agregar pelicula";
            extra = "\nDesea agregar una pelicula mas?";
        }

        if (BM.add(MovieName, Description, StarsPNL.getStars())) {
        
            if (javax.swing.JOptionPane.showConfirmDialog(this, "Se ha " + intention + " exitosamente: " + MovieName + "\nCodigo de pelicula: " + BM.searchFor(MovieName).getCode() + extra, title, javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.INFORMATION_MESSAGE) == javax.swing.JOptionPane.NO_OPTION)
            { CL.show(indexCard, "Main Menu"); }
            clearData();

        } else javax.swing.JOptionPane.showMessageDialog(this, "No se ha " + intention + " \'" + MovieName + "\'.\nIntente ingresando otro nombre", title, javax.swing.JOptionPane.WARNING_MESSAGE);

    }

    @Override
    public void cancelBTNPressed() {
        CL.show(indexCard, "Main Menu");
        clearData();
    }

    private void clearData() {
        DescriptionTXT.setText("");
        MovieNameTXT.setText("");
        StarsPNL.restartCamps();
    }

    public final void showPNL()
    {
        CL.show(indexCard, "Register Movie");
        initialName = "";
    }
    
    public final boolean showPNL(String MovieName)
    {
        Movies Movie = BM.searchFor(MovieName);
        if (Movie != null)
        {
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
            RatingLBL = new javax.swing.JLabel("Calificacion:");
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
                Stars[i].setName(String.valueOf(i));
                Stars[i].addActionListener((java.awt.event.ActionEvent e) -> {
                    setStars(Integer.parseInt(((javax.swing.JButton) e.getSource()).getName()));
                });
            }
            setStars(-1);
            
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
            if (_Rating == rating) _Rating -= 1;
            
            for (int i = 4; i >= 0; i--)
            {
                if (i <= _Rating)
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
        
        protected int getStars()
        { return (rating <= 0 ) ? 0 : rating; }
        
        protected void restartCamps()
        { setStars(-1); }
        
        //GUI ELEMENTS
        private final javax.swing.JButton[] Stars;
        private final javax.swing.JLabel RatingLBL;
        //GUI ELEMENTS
    }
}
