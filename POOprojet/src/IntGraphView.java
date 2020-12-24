import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class IntGraphView extends JFrame {
    private ImagePane imagePane;
    private JButton jouer,quitter;
    private IntGraphModel model;

    public IntGraphView(IntGraphModel m){
        model = m;
        JPanel boutons = new JPanel();
        boutons.setLayout(new GridLayout(2,1));
        setTitle("Pet Rescue Saga");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        jouer = new JButton("Jouer");
        quitter = new JButton("Quitter");
        quitter.addActionListener((ActionEvent e) -> System.exit(0));
        boutons.add(jouer);
        boutons.add(quitter);
        Dimension bd = boutons.getPreferredSize();
        imagePane = new ImagePane();
        Dimension d = imagePane.getSize();
        imagePane.setLayout(new FlowLayout(FlowLayout.CENTER, 0, d.height/2-bd.height/2));
        imagePane.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                JPanel c = (JPanel) evt.getSource();
                c.setLayout(new java.awt.FlowLayout(FlowLayout.CENTER, 0, c.getSize().height / 2 - bd.height / 2));
            }
        });
        imagePane.add(boutons);
        setContentPane(imagePane);
    }

    public class ImagePane extends JPanel{
        public ImagePane(){
            setPreferredSize(new Dimension(model.getImage().getWidth(), model.getImage().getHeight()));
        }
    }
}
