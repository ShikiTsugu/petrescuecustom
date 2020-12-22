import java.util.ArrayList;
import java.util.Scanner;

public class Jeu {
    private ArrayList<Niveaux> niveaux;
    private Cube r = new Bloc(0,0,"R");
    private Cube g = new Bloc(0,0,"G");
    private Cube y = new Bloc(0,0,"Y");
    private Cube b = new Bloc(0,0,"B");
    private Cube a = new Animaux(0,0);
    private Cube o = new Obstacle(0,0);
    private final Joueur joueur;
    private boolean valide;

    public Jeu(){
        joueur = new Joueur();
        niveaux = new ArrayList<Niveaux>();
        Cube[][] c1 = {{null,a,null,null,null,a,null},{g,g,r,r,r,y,y},{g,g,b,b,b,y,y},{r,r,b,b,b,g,g},{r,r,b,b,b,g,g}
        ,{g,y,y,r,g,g,r},{g,y,y,r,g,g,r}};
        Plateau p1 = new Plateau(c1);
        Niveaux n1 = new Niveaux(p1);
        Cube[][] c2 = {{null,g,a,g,null},{a,g,a,g,a},{r,y,a,y,r},{r,y,r,y,r},{y,g,r,g,y},{y,g,r,g,y},{g,y,r,y,g},{g,y,r,y,g}};
        Plateau p2 = new Plateau(c2);
        Niveaux n2 = new Niveaux(p2);
        Cube[][] c3 = {{null,null,a,null,a,null,a},{null,null,g,y,b,y,b},{null,null,g,y,b,y,b},{null,null,y,b,g,y,g},
                {b,b,y,b,g,y,g},{g,b,b,g,b,g,b},{y,b,b,g,b,g,b},{g,g,y,b,y,o,o},{b,b,y,b,y,o,o}};
        Plateau p3 = new Plateau(c3);
        Niveaux n3 = new Niveaux(p3);
        niveaux.add(n1);
        niveaux.add(n2);
        niveaux.add(n3);
    }

    public ArrayList<Niveaux> getNiv(){
        return niveaux;
    }

    public void montrerNiv(){
        System.out.println("Niveaux :");
        for(int i = 0; i<niveaux.size(); i++){
            if(niveaux.get(i).clear()) System.out.println(niveaux.get(i+1));
            else {
                System.out.println(niveaux.get(i));
                break;
            }
        }
    }

    public Niveaux selectNiv(){
        Scanner sc = new Scanner(System.in);
        montrerNiv();
        System.out.println("\nChoisissez un niveau en entrant le numéro :");
        int n = sc.nextInt();
        for(Niveaux niv : niveaux){
            if(n==niv.getNum()) {
                if (niveaux.indexOf(niv) == 0) {
                    valide = true;
                    return niv;
                }
                else {
                    if (niveaux.get(niveaux.indexOf(niv) - 1).clear()) {
                        valide = true;
                        return niv;
                    } else if (!niveaux.get(niveaux.indexOf(niv) - 1).clear()) {
                        valide = false;
                        System.out.println("Niveau précédent non terminé.");
                        return null;
                    }
                }
            }
        }
        valide = false;
        System.out.println("Niveau inexistant.");
        return null;
    }

    public void jouer(){
        try{
            Niveaux niv = selectNiv();
            if(valide) {
                Score s = new Score();
                int score = 0;
                System.out.println("Score : " + score);
                niv.getPlateau().affiche();
                while (!niv.clear()) {
                    niv.getPlateau().supprimer();
                    s.calcul(niv.getPlateau().nbBlocSuppr());
                    niv.getPlateau().miseAJour();
                    s.animauxPoint(niv.getPlateau().nbAnimauxSuppr());
                    score = s.getScore();
                    System.out.println("Score : " + score);
                    niv.getPlateau().affiche();
                }
                niv.meilleurScore(niv.calculScoreFinal());
                System.out.println(niv);
            }else{
                jouer();
            }
        }catch(NullPointerException e){
            jouer();
        }
    }

    public static void main(String[] args){
        System.out.println("\n" +
                "  ___     _     ___                       ___                 \n" +
                " | _ \\___| |_  | _ \\___ ___ __ _  _ ___  / __| __ _ __ _ __ _ \n" +
                " |  _/ -_)  _| |   / -_|_-</ _| || / -_) \\__ \\/ _` / _` / _` |\n" +
                " |_| \\___|\\__| |_|_\\___/__/\\__|\\_,_\\___| |___/\\__,_\\__, \\__,_|\n" +
                "                                                   |___/      \n");
        Jeu j = new Jeu();
        j.jouer();
    }
}
