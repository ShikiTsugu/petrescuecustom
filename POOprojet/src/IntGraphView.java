import javax.swing.*;
import java.awt.*;

public class IntGraphView extends JFrame {
    private ImagePane imagePane;
    private IntGraphModel model;

    public IntGraphView(IntGraphModel m){
        model = m;
        setTitle("Pet Rescue Saga");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        imagePane = new ImagePane();
        setContentPane(imagePane);
    }

    public class ImagePane extends JPanel{
        public ImagePane(){
            setPreferredSize(new Dimension(model.getImage().getWidth(), model.getImage().getHeight()));
        }
    }
}
