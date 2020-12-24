import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class IntGraphView extends JFrame {
    private ImagePane imagePane;
    private JLabel titre = new JLabel();
    private JButton jouer,quitter;
    private IntGraphModel model;

    public IntGraphView(IntGraphModel m){
        model = m;
        setTitle("Pet Rescue Saga");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        jouer = new JButton("Jouer");
        quitter = new JButton("Quitter");
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
}
