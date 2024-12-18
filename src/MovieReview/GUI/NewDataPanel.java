package MovieReview.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public abstract class NewDataPanel extends JPanel{
    //CONSTANTS
    protected final java.awt.CardLayout CL;
    protected final JPanel indexCard;
    private final String fontName;
    private final int fontType;
    //CONSTANTS
    //VARIABLES
    protected java.awt.Font BTNFont;
    private int fontSize;
    //VARIABLES
    
    
    public NewDataPanel(JPanel _indexCard, java.awt.CardLayout _CL, java.awt.Dimension Size)
    {
        indexCard = _indexCard;
        CL = _CL;
        fontType = java.awt.Font.PLAIN;
        fontName = "Droid Sans";
        fontSize = 12;

        innerContainer = new JPanel(new java.awt.BorderLayout());

        setLayout(new java.awt.GridBagLayout());
        setBackground(java.awt.Color.decode("#E7CFBC"));
        setPreferredSize(Size);
        
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                scaleInnerContainer(getSize());
                innerContainer.revalidate();
                innerContainer.repaint();
            }
        });
        
        final int height = (int) (Size.getHeight() - Size.getHeight()/8);
        final int width = (int) (Size.getWidth() - Size.getWidth()/4);        
        
        innerContainer.setMinimumSize(new java.awt.Dimension(width, height));
        innerContainer.setBackground(java.awt.Color.decode("#FFF4EC"));
        add(innerContainer, new java.awt.GridBagConstraints());
        
        buttonsContainer = new JPanel(new java.awt.GridLayout(0, 3));
        buttonsContainer.setMinimumSize(new java.awt.Dimension(width, height/6));
        buttonsContainer.setOpaque(false);
        innerContainer.add(buttonsContainer, java.awt.BorderLayout.SOUTH);
        
        cancelContainer = new JPanel(new java.awt.GridBagLayout());
        cancelContainer.setMinimumSize(new java.awt.Dimension(width/4, height));
        final int BC_W = (int) (cancelContainer.getMinimumSize().getWidth() - cancelContainer.getMinimumSize().getWidth()/10);

        final javax.swing.JButton cancelBTN = new javax.swing.JButton("cancelar");
        cancelBTN.setMinimumSize(new java.awt.Dimension(BC_W, (int) buttonsContainer.getMinimumSize().getHeight()));
        cancelBTN.setBorder(new javax.swing.border.LineBorder(Color.decode("#512828"), 4));
        cancelBTN.setForeground(Color.WHITE);
        cancelBTN.setBackground(Color.decode("#EC3832"));
        cancelBTN.setFocusable(false);
        
        cancelBTN.addActionListener((ActionEvent e) -> {
            cancelBTNPressed();
        });
        
        cancelContainer.add(cancelBTN, new java.awt.GridBagConstraints());
        cancelContainer.setOpaque(false);
        buttonsContainer.add(cancelContainer);
        buttonsContainer.add(new java.awt.Container());
        
        acceptContainer = new JPanel(new java.awt.GridBagLayout());
        acceptContainer.setMinimumSize(new java.awt.Dimension(width/4, height));
        
        final javax.swing.JButton acceptBTN = new javax.swing.JButton("aceptar");
        acceptBTN.setMinimumSize(cancelBTN.getMinimumSize());
        acceptBTN.setBorder(new javax.swing.border.LineBorder(Color.decode("#285131"), 4));
        acceptBTN.setForeground(Color.WHITE);
        acceptBTN.setBackground(Color.decode("#49b649"));
        acceptBTN.setFocusable(false);
        
        acceptBTN.addActionListener((ActionEvent e) -> {
            try {
                acceptBTNPressed();
            } catch (CloneNotSupportedException e1) {}
        });
        
        acceptContainer.add(acceptBTN, new java.awt.GridBagConstraints());
        acceptContainer.setOpaque(false);
        add(innerContainer, new java.awt.GridBagConstraints());
        buttonsContainer.add(acceptContainer);

        final int CPH =  (int) (innerContainer.getPreferredSize().getHeight() - buttonsContainer.getPreferredSize().getHeight());
        final int CPW =  (int) (innerContainer.getPreferredSize().getWidth() - buttonsContainer.getPreferredSize().getWidth());
        centralPanel = new JPanel();
        centralPanel.setMinimumSize(new java.awt.Dimension(CPW, CPH));
        centralPanel.setOpaque(false);
        innerContainer.add(centralPanel, java.awt.BorderLayout.CENTER);
    }

    private void scaleInnerContainer(java.awt.Dimension Size)
    {
        final int height = (int) (Size.getHeight() - Size.getHeight()/8);
        final int width = (int) (Size.getWidth() - Size.getWidth()/4);        
        
        innerContainer.setPreferredSize(new java.awt.Dimension(width, height));
        buttonsContainer.setPreferredSize(new java.awt.Dimension(width, height/6));
        acceptContainer.setPreferredSize(new java.awt.Dimension(width/4, height));
        cancelContainer.setPreferredSize(acceptContainer.getPreferredSize());
        
        final int BC_W = (int) (cancelContainer.getPreferredSize().getWidth() - cancelContainer.getPreferredSize().getWidth()/10);
        final int BC_H = (int) buttonsContainer.getPreferredSize().getHeight();
        cancelContainer.getComponents()[0].setPreferredSize(new java.awt.Dimension(BC_W, BC_H));
        acceptContainer.getComponents()[0].setPreferredSize(cancelContainer.getComponents()[0].getPreferredSize());
        fontSize = ((BC_W + BC_H)/12) + 1;
        
        BTNFont = new java.awt.Font(fontName, fontType, fontSize);
        cancelContainer.getComponents()[0].setFont(BTNFont);
        acceptContainer.getComponents()[0].setFont(BTNFont);
        
        ((javax.swing.JButton) cancelContainer.getComponents()[0]).setBorder(new javax.swing.border.LineBorder(Color.decode("#512828"), (BC_W + BC_H)/45));
        ((javax.swing.JButton) acceptContainer.getComponents()[0]).setBorder(new javax.swing.border.LineBorder(Color.decode("#285131"), (BC_W + BC_H)/45));
     
        final int CPH =  (int) (innerContainer.getPreferredSize().getHeight() - buttonsContainer.getPreferredSize().getHeight());
        centralPanel.setPreferredSize(new java.awt.Dimension(width, CPH));
        scaleComponents();
    }
    
    public final void changeOuterBG(final java.awt.Color BG)
    { setBackground(BG); }

    public final void addComponentToCentralPanel(java.awt.Component Component)
    { centralPanel.add(Component); }
    
    public final void addComponentToCentralPanel(java.awt.Component Component, Object Constrains)
    { centralPanel.add(Component, Constrains); }
    
    public final JPanel getCentralPanel()
    { return centralPanel; }
    
    public final java.awt.Dimension getCentralPanelSize()
    { return centralPanel.getPreferredSize(); }

    
    //ABSTACT METHODS
    public abstract void acceptBTNPressed() throws CloneNotSupportedException;
    public abstract void cancelBTNPressed();
    public abstract void scaleComponents();
    //ABSTRACT METHODS
    
    //GUI ELEMENTS
    private final JPanel innerContainer;
    private final JPanel buttonsContainer;
    private final JPanel acceptContainer;
    private final JPanel cancelContainer;
    private final JPanel centralPanel;
    //GUI ELEMENTS
}