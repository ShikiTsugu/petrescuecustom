import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class IntGraphView extends JFrame {
    private ImagePane imagePane;
    private JLabel titre = new JLabel();
    private JButton jouer,quitter;
    private JButton niv1 = new JButton("Niveau 1");
    private JButton niv2 = new JButton("Niveau 2");
    private JButton niv3 = new JButton("Niveau 3");
    private JButton niv4 = new JButton("Niveau 4");
    private JButton retour = new JButton("Retour");
    private IntGraphModel model;

    public IntGraphView(IntGraphModel m){
        model = m;
        afficheIni();
    }

    public ImageIcon createImageIcon(String nomFichier) {
        File f = new File(nomFichier);
        if (nomFichier != null) {
            return new ImageIcon(nomFichier);
        } else {
            System.err.println("Fichier introuvable : " + f.getAbsolutePath());
            return null;
        }
    }

    public void afficheIni(){
        setTitle("Pet Rescue Saga");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        jouer = new JButton("Jouer");
        jouer.setFont(new Font("Monospaced",Font.BOLD,20));
        jouer.setBackground(new Color(93,125,101));
        jouer.setForeground(Color.WHITE);
        jouer.addActionListener((ActionEvent e) -> jouer());

        quitter = new JButton("Quitter");
        quitter.setFont(new Font("Monospaced",Font.BOLD,20));
        quitter.setBackground(new Color(95,105,60));
        quitter.setForeground(Color.WHITE);
        quitter.addActionListener((ActionEvent e) -> System.exit(0));

        titre.setText("Pet Rescue Saga");
        titre.setFont(new Font("Monospaced",Font.BOLD,60));

        imagePane = new ImagePane();

        BoxLayout boxlayout = new BoxLayout(imagePane, BoxLayout.Y_AXIS);
        imagePane.setLayout(boxlayout);
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 120)));
        imagePane.add(titre);
        jouer.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 120)));
        imagePane.add(jouer);
        quitter.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 20)));
        imagePane.add(quitter);

        setContentPane(imagePane);
    }

    public class ImagePane extends JPanel{
        public ImagePane(){
            setPreferredSize(new Dimension(model.getImage().getWidth(), model.getImage().getHeight()));
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(model.getImage(),0,0,this);
        }
    }
    
    public void jouer() {
        imagePane.removeAll();
    	affichemenu();
    	imagePane.updateUI();
    }
    
    public void affichemenu() {
    	imagePane.removeAll();

        niv1.setFont(new Font("Monospaced",Font.BOLD,20));
        niv1.setBackground(new Color(93,125,101));
        niv1.setForeground(Color.WHITE);

        niv2.setFont(new Font("Monospaced",Font.BOLD,20));
        niv2.setBackground(new Color(93,125,101));
        niv2.setForeground(Color.WHITE);
        niv2.setEnabled(false);

        niv3.setFont(new Font("Monospaced",Font.BOLD,20));
        niv3.setBackground(new Color(93,125,101));
        niv3.setForeground(Color.WHITE);
        niv3.setEnabled(false);

        niv4.setFont(new Font("Monospaced",Font.BOLD,20));
        niv4.setBackground(new Color(93,125,101));
        niv4.setForeground(Color.WHITE);
        niv4.setEnabled(false);

        retour.setFont(new Font("Monospaced",Font.BOLD,20));
        retour.setBackground(new Color(95,105,60));
        retour.setForeground(Color.WHITE);

        retour.addActionListener((ActionEvent e) -> {
            afficheIni();
            imagePane.updateUI();
        });
        JLabel niv = new JLabel();
        niv.setText("Niveaux");
        niv.setFont(new Font("Monospaced",Font.BOLD,40));
        BoxLayout boxlayout = new BoxLayout(imagePane, BoxLayout.Y_AXIS);
        imagePane.setLayout(boxlayout);
        niv.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 100)));
        imagePane.add(niv);
        niv1.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 60)));
    	imagePane.add(niv1);
        niv2.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 20)));
    	imagePane.add(niv2);
        niv3.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 20)));
    	imagePane.add(niv3);
        niv4.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 20)));
    	imagePane.add(niv4);
        retour.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 60)));
        imagePane.add(retour);
    }
    
}
