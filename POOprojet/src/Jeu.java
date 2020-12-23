import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Jeu {
    private ArrayList<Niveaux> niveaux = new ArrayList<>();
    private Cube r = new Bloc(0,0,"R");
    private Cube g = new Bloc(0,0,"G");
    private Cube y = new Bloc(0,0,"Y");
    private Cube b = new Bloc(0,0,"B");
    private Cube p = new Bloc(0,0,"P");
    private Cube a = new Animaux(0,0);
    private Cube o = new Obstacle(0,0);
    private final Joueur joueur;
    private boolean valide;

    public Jeu(){
        joueur = new Joueur();

        //Création des tableaux pour les plateaux
        Cube[][] c1 = {{null,a,null,null,null,a,null},{p,p,r,r,r,y,y},{p,p,b,b,b,y,y},{r,r,b,b,b,g,g},{r,r,b,b,b,g,g}
        ,{p,y,y,r,p,p,r},{p,y,y,r,p,p,r}};
        Cube[][] c2 = {{null,p,a,p,null},{a,p,a,p,a},{r,y,a,y,r},{r,y,r,y,r},{y,p,r,p,y},{y,p,r,p,y},{p,y,r,y,p},{p,y,r,y,p}};
        Cube[][] c3 = {{null,null,a,null,a,null,a},{null,null,p,y,b,y,b},{null,null,p,y,b,y,b},{null,null,y,b,p,y,p},
                {b,b,y,b,p,y,p},{p,b,b,p,b,p,b},{y,b,b,p,b,p,b},{p,p,y,b,y,o,o},{b,b,y,b,y,o,o}};

        //Création des plateaux pour les niveaux
        Plateau p1 = new Plateau(c1);
        Plateau p2 = new Plateau(c2);
        Plateau p3 = new Plateau(c3);

        //Création des niveaux et ajout des niveaux
        Niveaux n1 = new Niveaux(p1);
        Niveaux n2 = new Niveaux(p2);
        Niveaux n3 = new Niveaux(p3);
        niveaux.add(n1);
        niveaux.add(n2);
        niveaux.add(n3);
    }

    //montre la liste des niveaux débloqués, on affiche toujours le tout premier niveau
    public void montrerNiv(){
        System.out.println("Niveaux :");
        for(int i = 0; i<niveaux.size(); i++){
            if(niveaux.get(i).clear()) {
                System.out.println(niveaux.get(i));
            }
            else {
                System.out.println(niveaux.get(i));
                break;
            }
        }
    }

    //demande à l'utilisateur un niveau à choisir
    public Niveaux selectNiv(){
        Scanner sc = new Scanner(System.in);
        montrerNiv();
        System.out.println("\nChoisissez un niveau en entrant le numéro :");
        //test si le scanner est correct
        try {
            int n = sc.nextInt();
            //si oui on parcourt les niveaux et si ça correspond on renvoit ce niveau, on affiche seulement les niveaux débloqués
            for(Niveaux niv : niveaux){
                if(n==niv.getNum()) {
                    //si c'est le premier niveau alors on le retourne
                    if (niveaux.indexOf(niv) == 0) {
                        valide = true;
                        return niv;
                    }
                    //sinon on test si le niveau précédent a été complété, si oui alors on accepte la requête de faire le niveau suivant
                    else {
                        if (niveaux.get(niveaux.indexOf(niv) - 1).clear()) {
                            valide = true;
                            return niv;
                            // si on essaye de faire un niveau alors que le niveau précédent n'est pas terminé on retourne null
                        } else if (!niveaux.get(niveaux.indexOf(niv) - 1).clear()) {
                            valide = false;
                            System.out.println("Niveau précédent non terminé.");
                            return null;
                        }
                    }
                }
            }
            //si le scanner est incorrect au retourne null
        }catch(InputMismatchException e){
            valide=false;
            System.out.println("Mauvaise formulation.");
            return null;
        }
        //si aucun niveau n'a été trouvé c'est qu'il n'existe pas, on retourne null
        valide = false;
        System.out.println("Niveau inexistant.");
        return null;
    }

    //vérifie si tous les niveaux ont été débloqués
    public boolean toutDebloque(){
        for(Niveaux niv : niveaux){
            if(!niv.clear()) return false;
        }
        return true;
    }

    public void allCleared(){
        for(Niveaux niv : niveaux){
            niv.setAsCleared();
        }
    }

    public void jouer(){
        try{
            if(toutDebloque()) {
                System.out.println("Vous avez débloqué tous les niveaux.\n");
                allCleared();
            }
            Niveaux niv = selectNiv();
            if(valide) {
                Score s = new Score();
                int score = 0;
                int animIni = niv.getPlateau().getNbAnimIni();
                System.out.println("Score : " + score);
                System.out.println("Animaux : 0/"+animIni);
                niv.getPlateau().affiche();
                while (!niv.clear()) {
                    niv.getPlateau().supprimer();
                    s.calcul(niv.getPlateau().nbBlocSuppr());
                    s.animauxPoint(niv.getPlateau().nbAnimauxSuppr());
                    score = s.getScore();
                    System.out.println("Score : " + score);
                    System.out.println("Animaux : "+niv.getPlateau().nbAnimauxSuppr()+"/"+animIni);
                    niv.getPlateau().miseAJour();
                    niv.getPlateau().affiche();
                }
                niv.meilleurScore(score);
                Scanner sc = new Scanner(System.in);
                System.out.println(niv+"\nVoulez vous continuer ? Répondez par o si oui, n sinon.");
                String rep = sc.next();
                if(rep.equals("o")) jouer();
                else {
                    System.out.println("Progression sauvegardé.");
                }
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
