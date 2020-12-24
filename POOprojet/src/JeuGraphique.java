import java.awt.*;
import java.io.File;

public class JeuGraphique {
    public void jouer(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                File f = new File("background.png");
                String s = f.getAbsolutePath();
                IntGraphModel im = new IntGraphModel(s);
                IntGraphView iv = new IntGraphView(im);
                iv.pack();
                iv.setVisible(true);
            }
        });
    }
}
