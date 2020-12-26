import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class IntGraphView extends JFrame {
    private ImagePane imagePane;
    private JLabel titre = new JLabel();
    private JButton jouer,quitter,niv1;
    private IntGraphModel model;

    public IntGraphView(IntGraphModel m){
        model = m;
        setTitle("Pet Rescue Saga");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        jouer = new JButton("Jouer");
        jouer.addActionListener((ActionEvent e) -> jouer());
        quitter = new JButton("Quitter");
        quitter.addActionListener((ActionEvent e) -> affichemenu());
        titre.setText("Pet Rescue Saga");
        titre.setFont(new Font("Monospaced",Font.BOLD,60));
        niv1 = new JButton("Niveau 1");
        

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
    	jouer.setVisible(false);
    	quitter.setVisible(false);
    	titre.setVisible(false);
    	affichemenu();
    }
    
    public void affichemenu() {
    	imagePane.removeAll();
    	for (int i = 0; i < 5; i++) {
    		imagePane.add(new JButton("i"));
    	}
    	JButton niv2 = new JButton("niveau 2");
    	JButton niv3 = new JButton("niveau 3");
    	JButton niv4 = new JButton("niveau 4");
    	GridLayout gridlayout = new GridLayout(5,5);
    	imagePane.setLayout(gridlayout);
    	imagePane.add(niv1);
    	imagePane.add(niv2);
    	imagePane.add(niv3);
    	imagePane.add(niv4);
    }
    
}
