import java.awt.*;

public class JeuGraphique {
    public void jouer(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                IntGraphModel im = new IntGraphModel("background.png");
                IntGraphView iv = new IntGraphView(im);
                iv.pack();
                iv.setVisible(true);
            }
        });
    }
}
