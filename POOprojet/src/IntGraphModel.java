import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class IntGraphModel {
    private BufferedImage image;

    public IntGraphModel(String chemin){
        try{
            image = ImageIO.read(new File(chemin));
        } catch (IOException e) {
            System.out.println("Fichier non trouv�, chemin incorrecte.");
            System.exit(1);
        }
    }

    public BufferedImage getImage(){return image;}
}
