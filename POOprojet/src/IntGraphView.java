import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class IntGraphView extends JFrame {
    private ImagePane imagePane;
    private JLabel titre = new JLabel();
    private JButton jouer,quitter;
    private JButton niv1 = new JButton("Niveau 1");
    private JButton niv2 = new JButton("Niveau 2");
    private JButton niv3 = new JButton("Niveau 3");
    private JButton niv4 = new JButton("Niveau 4");
    private ArrayList<JButton> niveaux = new ArrayList<>();
    private JButton retour = new JButton("Retour");
    private IntGraphModel model;
    private final JeuGraphique jg = new JeuGraphique();

    public IntGraphView(IntGraphModel m){
        model = m;
        niveaux.add(niv1);niveaux.add(niv2);niveaux.add(niv3);niveaux.add(niv4);
        afficheIni();
    }

    public ArrayList<JButton> getNiveaux(){
        return niveaux;
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

    public void niveauxDispo(){
        for(int i = 0; i<jg.getNiveaux().size(); i++){
            if(jg.getNiveaux().get(i).clear()) {
                niveaux.get(i).setEnabled(true);
            }
            else {
                niveaux.get(i).setEnabled(true);
                for(int j = i+1; j<jg.getNiveaux().size(); j++){
                    niveaux.get(j).setEnabled(false);
                }
                break;
            }
        }
    }
    
    public void affichemenu() {
    	imagePane.removeAll();
        for(JButton n : niveaux){
            n.setFont(new Font("Monospaced",Font.BOLD,20));
            n.setBackground(new Color(93,125,101));
            n.setForeground(Color.WHITE);
        }
        niveauxDispo();

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
        imagePane.add(Box.createRigidArea(new Dimension(0, 60)));
        for(JButton n : niveaux){
            n.setAlignmentX(Component.CENTER_ALIGNMENT);
            imagePane.add(Box.createRigidArea(new Dimension(0, 15)));
            imagePane.add(n);
        }
        retour.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 60)));
        imagePane.add(retour);
    }
    
}
