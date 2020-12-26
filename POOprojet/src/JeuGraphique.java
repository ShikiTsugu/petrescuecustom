import java.util.ArrayList;

public class JeuGraphique {
    private ArrayList<Niveaux> niveaux = new ArrayList<>();
    private Cube r = new Bloc(0,0,"R");
    private Cube g = new Bloc(0,0,"G");
    private Cube y = new Bloc(0,0,"Y");
    private Cube b = new Bloc(0,0,"B");
    private Cube p = new Bloc(0,0,"P");
    private Cube a = new Animaux(0,0);
    private Cube o = new Obstacle(0,0);
    private boolean valide;

    public JeuGraphique(){
        //Création des tableaux pour les plateaux
        Cube[][] c1 = {{null,a,null,null,null,a,null},{p,p,r,r,r,y,y},{p,p,b,b,b,y,y},{r,r,b,b,b,g,g},{r,r,b,b,b,g,g}
                ,{p,y,y,r,p,p,r},{p,y,y,r,p,p,r}};
        Cube[][] c2 = {{null,p,a,p,null},{a,p,a,p,a},{r,y,a,y,r},{r,y,r,y,r},{y,p,r,p,y},{y,p,r,p,y},{p,y,r,y,p},{p,y,r,y,p}};
        Cube[][] c3 = {{null,null,a,null,a,null,a},{null,null,p,y,b,y,b},{null,null,p,y,b,y,b},{null,null,y,b,p,y,p},
                {b,b,y,b,p,y,p},{p,b,b,p,b,p,b},{y,b,b,p,b,p,b},{p,p,y,b,y,o,o},{b,b,y,b,y,o,o}};
        Cube[][] c4 = {{null,null,null,null,a,a,a,a},{null,null,null,p,p,y,p,r},{null,null,p,r,p,y,p,r},{null,p,p,r,y,r,y,y},{r,y,r,y,y,r,y,o},
                {y,y,r,y,p,r,o,o},{r,r,p,r,p,o,o,o},{p,r,p,r,o,o,o,o}};

        //Création des plateaux pour les niveaux
        Plateau p1 = new Plateau(c1);Plateau p2 = new Plateau(c2);Plateau p3 = new Plateau(c3);Plateau p4 = new Plateau(c4);

        //Création des niveaux et ajout des niveaux
        Niveaux n1 = new Niveaux(p1);Niveaux n2 = new Niveaux(p2);Niveaux n3 = new Niveaux(p3);Niveaux n4 = new Niveaux(p4);
        niveaux.add(n1);niveaux.add(n2);niveaux.add(n3);niveaux.add(n4);
    }

    public ArrayList<Niveaux> getNiveaux(){
        return niveaux;
    }

    public void jouer(){
        IntGraphModel im = new IntGraphModel("background.png");
        IntGraphView iv = new IntGraphView(im);
        iv.pack();
        iv.setVisible(true);
    }
}
