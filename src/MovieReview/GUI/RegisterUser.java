package MovieReview.GUI;

import java.awt.Color;
import javax.swing.JPanel;

public class RegisterUser extends NewDataPanel {
    
    public RegisterUser(javax.swing.JPanel indexCard, java.awt.CardLayout CL, java.awt.Dimension Size) {
        super(indexCard, CL, Size);
        getCentralPanel().setLayout(new java.awt.GridLayout(3, 1));
        
        final JPanel UserPNL = new JPanel(new java.awt.GridBagLayout());
        UserPNL.setOpaque(false);
        UserIcon = new javax.swing.JLabel("");

        UserIcon.setBorder(new javax.swing.border.LineBorder(java.awt.Color.RED));
        UserPNL.add(UserIcon, new java.awt.GridBagConstraints());
        addComponentToCentralPanel(UserPNL);

        final JPanel NamePNL = new JPanel(new java.awt.GridLayout(2, 1));
        NamePNL.setOpaque(false);
        nameTXT = new javax.swing.JTextField();
        nameLBL = new javax.swing.JLabel("Nombre de usuario:");
        nameLBL.setHorizontalTextPosition(javax.swing.JLabel.LEFT);
        nameLBL.setVerticalTextPosition(javax.swing.JLabel.BOTTOM);
        nameLBL.setForeground(Color.WHITE);
        
        final JPanel NamePNL1 = new JPanel();
        NamePNL1.setOpaque(false);
        NamePNL1.add(nameLBL);
        NamePNL.add(NamePNL1);
        
        final JPanel NamePNL2 = new JPanel();
        NamePNL2.setOpaque(false);
        NamePNL2.add(nameTXT);
        NamePNL.add(NamePNL2);
        addComponentToCentralPanel(NamePNL);
        
        final JPanel PassPNL = new JPanel(new java.awt.GridLayout(2,1));
        PassPNL.setOpaque(false);
        
        passLBL = new javax.swing.JLabel("Contraseña:");
        passLBL.setHorizontalTextPosition(javax.swing.JLabel.LEFT);
        passLBL.setVerticalTextPosition(javax.swing.JLabel.BOTTOM);
        passLBL.setForeground(Color.WHITE);
        final JPanel PassLBLPNL = new JPanel();
        PassLBLPNL.setOpaque(false);
        PassLBLPNL.add(passLBL);
        
        PassPNL.add(PassLBLPNL);
        
        final JPanel PassTXTPNL = new JPanel();
        PassTXTPNL.setOpaque(false);
        
        passTXT = new javax.swing.JPasswordField();
        passTXT.setEchoChar('*');
        
        revealBTN = new javax.swing.JButton();
        revealBTN.addActionListener((java.awt.event.ActionEvent e) -> {
            passTXT.setEchoChar((passTXT.getEchoChar() == '*') ? (char) 0 : '*');
        });
        PassTXTPNL.add(passTXT);
        PassTXTPNL.add(revealBTN);
        
        PassPNL.add(PassTXTPNL);
        addComponentToCentralPanel(PassPNL);
    }
    
    @Override
    public void scaleComponents()
    {
        final int MH_IP = (int) getCentralPanelSize().getHeight()/3;
        final int MW_IP = (int) getCentralPanelSize().getWidth();

        UserIcon.setPreferredSize(new java.awt.Dimension(MH_IP, MH_IP));
        nameTXT.setPreferredSize(new java.awt.Dimension(MW_IP - MW_IP/3, MH_IP/4));
        nameLBL.setPreferredSize(new java.awt.Dimension(MW_IP -MW_IP/3, MH_IP - MH_IP/4));
        passLBL.setPreferredSize(nameLBL.getPreferredSize());
        revealBTN.setPreferredSize(new java.awt.Dimension(MH_IP/4, MH_IP/4));
        passTXT.setPreferredSize(new java.awt.Dimension(MW_IP - MW_IP/3 - MH_IP/4, MH_IP/4));
        nameLBL.setFont(BTNFont);
        nameTXT.setFont(BTNFont);
        passLBL.setFont(BTNFont);
        passTXT.setFont(BTNFont);
    }

    @Override
    public void acceptBTNPressed()
    {
        final String Name = nameTXT.getText();
        final String Pass = passTXT.getText();
        if (Name.isBlank() || Pass.length() < 5)
        {
            javax.swing.JOptionPane.showMessageDialog(this.getParent(), "Datos incompletos!\nPor favor llene el campo de \'nombre de usuario\'\ne ingrese una contraseña con al menos 5 caracteres", "Campos incompletos", javax.swing.JOptionPane.WARNING_MESSAGE);
            return ;
        }
        
        javax.swing.JOptionPane.showMessageDialog(this.getParent(), "Se ha agregado de forma exitosa el usuario!", "REGISTRAR USUARIO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        clearFields();
    }

    @Override
    public void cancelBTNPressed() {
        CL.show(indexCard, "Main Menu");
        clearFields();
    }
     
    private void clearFields() {
        nameTXT.setText("");
        passTXT.setText("");        
    }

    public final void showPNL()
    {
        CL.show(indexCard, "Register Users");
    }
    
    //GUI ELEMENTS
    private final javax.swing.JLabel UserIcon;
    private final javax.swing.JLabel nameLBL;
    private final javax.swing.JTextField nameTXT;
    private final javax.swing.JPasswordField passTXT;
    private final javax.swing.JButton revealBTN;
    private final javax.swing.JLabel passLBL;
    //GUI ELEMENTS
}
