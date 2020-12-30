import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class IntGraphView extends JFrame {
    private ImagePane imagePane;
    private IntGraphModel model;
    private JLabel titre = new JLabel();
    private JButton jouer = new JButton("Jouer");
    private JButton quitter = new JButton("Quitter");
    private JButton niv1 = new JButton("Niveau 1");
    private JButton niv2 = new JButton("Niveau 2");
    private JButton niv3 = new JButton("Niveau 3");
    private JButton niv4 = new JButton("Niveau 4");
    private ArrayList<JButton> niveaux = new ArrayList<>();
    private JButton retour = new JButton("Retour");
    private JButton retourMenu = new JButton("Retour");
    private int scoreNiv,meilleurScoreNiv,nbScouts;
    private final JeuGraphique jg = new JeuGraphique();
    private boolean done=false;

    public IntGraphView(IntGraphModel m){
        model = m;
        niveaux.add(niv1);niveaux.add(niv2);niveaux.add(niv3);niveaux.add(niv4);
        setResizable(false);
        afficheIni();
    }

    public ArrayList<JButton> getNiveaux(){
        return niveaux;
    }

    public JButton getJouer(){return jouer;}

    public JButton getQuitter(){return quitter;}

    public JButton getRetour(){return retour;}

    public JButton getRetourMenu(){return retourMenu;}

    public JeuGraphique getJg(){return jg;}

    public void update(){imagePane.updateUI();}

    public void reset(){imagePane.removeAll();}

    public void afficheIni(){
        setTitle("Scout Rescue Saga");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        jouer.setFont(new Font("Monospaced",Font.BOLD,20));
        jouer.setBackground(new Color(93,125,101));
        jouer.setForeground(Color.WHITE);

        quitter.setFont(new Font("Monospaced",Font.BOLD,20));
        quitter.setBackground(new Color(95,105,60));
        quitter.setForeground(Color.WHITE);

        titre.setIcon(new ImageIcon("logo.png"));

        imagePane = new ImagePane();

        BoxLayout boxlayout = new BoxLayout(imagePane, BoxLayout.Y_AXIS);
        imagePane.setLayout(boxlayout);
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 80)));
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

    public JPanel initialisationPlateau(Niveaux n){
        JPanel plateau = new JPanel();
        plateau.setOpaque(false);
        GridLayout tab = new GridLayout(n.getPlateau().getCubes().length,n.getPlateau().getCubes()[0].length);
        plateau.setLayout(tab);
        return plateau;
    }

    public void supprimer(JButton bt, Niveaux n){
        int y = bt.getX()/50;
        int x = bt.getY()/50;
        System.out.println(n.getPlateau().getCubes()[x][y]);
        try {
            if (x != -1 && y != -1 && n.getPlateau().getCubes()[x][y] instanceof Bloc) {
                Bloc b = n.getPlateau().getBloc(x, y);
                if (n.getPlateau().VerifSeul(x, y, b)) {
                    n.getPlateau().supprimerAux(x, y, b);
                } else {
                    System.out.println("Ce bloc est seul, il ne peut pas être supprimer");
                }
            } else {
                System.out.println("Ceci n'est pas un bloc");
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Coordonées non valide");
        }
    }

    public JButton couleurBloc(Bloc b){
        if(b.getColor().equals("R")){
            JButton r = new JButton();
            r.setContentAreaFilled(false);
            r.setOpaque(false);
            r.setIcon(new ImageIcon("red.png"));
            return r;
        }
        if(b.getColor().equals("G")){
            JButton g = new JButton();
            g.setContentAreaFilled(false);
            g.setOpaque(false);
            g.setIcon(new ImageIcon("green.png"));
            return g;
        }
        if(b.getColor().equals("Y")){
            JButton y = new JButton();
            y.setContentAreaFilled(false);
            y.setOpaque(false);
            y.setIcon(new ImageIcon("yellow.png"));
            return y;
        }
        if(b.getColor().equals("B")){
            JButton bl = new JButton();
            bl.setContentAreaFilled(false);
            bl.setOpaque(false);
            bl.setIcon(new ImageIcon("blue.png"));
            return bl;
        }
        if(b.getColor().equals("P")){
            JButton p = new JButton();
            p.setContentAreaFilled(false);
            p.setOpaque(false);
            p.setIcon(new ImageIcon("purple.png"));
            return p;
        }
        return null;
    }

    public void afficheScore(Niveaux n){
        JLabel bestscore = new JLabel("Meilleur score : " + n.getScore());
        bestscore.setFont(new Font("Monospaced", Font.BOLD, 40));
        JLabel score = new JLabel("Score : " + scoreNiv);
        score.setFont(new Font("Monospaced", Font.BOLD, 40));
        retourMenu.setFont(new Font("Monospaced",Font.BOLD,20));
        retourMenu.setBackground(new Color(95,105,60));
        retourMenu.setForeground(Color.WHITE);
        bestscore.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.setLayout(new BoxLayout(imagePane, BoxLayout.Y_AXIS));
        imagePane.add(Box.createRigidArea(new Dimension(0, 120)));
        imagePane.add(bestscore);
        score.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 15)));
        imagePane.add(score);
        retourMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 240)));
        imagePane.add(retourMenu);
    }

    public void affichePlateau(JPanel p, Niveaux n){
        if(n.clear()){
            reset();
            afficheScore(n);
            scoreNiv = 0;
            meilleurScoreNiv = 0;
            update();
        }else {
            reset();
            nbScouts = n.getPlateau().getNbAnimIni();
            imagePane.setLayout(null);
            for (Cube[] c : n.getPlateau().getCubes()) {
                for (Cube b : c) {
                    if (b != null) {
                        if (b instanceof Animaux) {
                            JButton a = new JButton();
                            a.setBorderPainted(false);
                            a.setContentAreaFilled(false);
                            a.setFocusPainted(false);
                            a.setOpaque(false);
                            a.setIcon(new ImageIcon("levi.png"));
                            p.add(a);
                        }
                        if (b instanceof Bloc) {
                            JButton bt = couleurBloc((Bloc) b);
                            bt.addActionListener((ActionEvent e) -> {
                                Score s = new Score();
                                supprimer(bt, n);
                                n.getPlateau().miseAJour();
                                s.calcul(n.getPlateau().nbBlocSuppr());
                                s.animauxPoint(n.getPlateau().nbAnimauxSuppr());
                                scoreNiv += s.getScore();
                                n.meilleurScore(scoreNiv);
                                meilleurScoreNiv = n.getScore();
                                JPanel pnew = initialisationPlateau(n);
                                affichePlateau(pnew, n);
                            });
                            p.add(bt);
                        }
                        if (b instanceof Obstacle) {
                            JButton o = new JButton();
                            o.setBorderPainted(false);
                            o.setContentAreaFilled(false);
                            o.setFocusPainted(false);
                            o.setOpaque(false);
                            o.setIcon(new ImageIcon("obstacle.png"));
                            p.add(o);
                        }
                    } else {
                        JButton vide = new JButton();
                        vide.setBorderPainted(false);
                        vide.setContentAreaFilled(false);
                        vide.setFocusPainted(false);
                        vide.setOpaque(false);
                        vide.setEnabled(false);
                        p.add(vide);
                    }
                }
            }
            JPanel m = new JPanel();
            m.setOpaque(false);
            m.setLayout(new BoxLayout(m, BoxLayout.Y_AXIS));
            JLabel bestscore = new JLabel("Meilleur score : " + meilleurScoreNiv);
            bestscore.setFont(new Font("Monospaced", Font.BOLD, 20));
            JLabel score = new JLabel("Score : " + scoreNiv);
            score.setFont(new Font("Monospaced", Font.BOLD, 20));
            JLabel scout = new JLabel("Scouts : " + n.getPlateau().nbAnimauxSuppr() + "/" + nbScouts);
            scout.setFont(new Font("Monospaced", Font.BOLD, 20));
            m.add(bestscore);
            m.add(score);
            m.add(scout);
            imagePane.add(m);
            imagePane.add(p);
            m.setBounds(0, 0, 400, 100);
            switch (n.getNum()) {
                case 1:
                    p.setBounds(model.getImage().getWidth() / 3, model.getImage().getHeight() / 3, 350, 350);
                    break;
                case 2:
                    p.setBounds(model.getImage().getWidth() / 3 + 50, model.getImage().getHeight() / 5 + 20, 250, 400);
                    break;
                case 3:
                    p.setBounds(model.getImage().getWidth() / 3, model.getImage().getHeight() / 6, 360, 440);
                    break;
                case 4:
                    p.setBounds(model.getImage().getWidth() / 4 + 50, model.getImage().getHeight() / 5, 400, 420);
                    break;
            }
            update();
        }
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
        reset();
        niveauxDispo();

        retour.setFont(new Font("Monospaced",Font.BOLD,20));
        retour.setBackground(new Color(95,105,60));
        retour.setForeground(Color.WHITE);

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
            n.setFont(new Font("Monospaced",Font.BOLD,20));
            n.setBackground(new Color(93,125,101));
            n.setForeground(Color.WHITE);
            n.setAlignmentX(Component.CENTER_ALIGNMENT);
            imagePane.add(Box.createRigidArea(new Dimension(0, 15)));
            imagePane.add(n);
        }
        retour.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 60)));
        imagePane.add(retour);
    }

}
