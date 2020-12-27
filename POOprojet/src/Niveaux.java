public class Niveaux {
    private Plateau plateau;
    private static int compteur=1;
    private final int niveau;
    private boolean clear;
    private int score;

    public Niveaux(Plateau p){
        plateau=p;
        niveau=compteur++;
        clear = false;
        score = 0;
    }

    public static void resetCompteur(){compteur=1;}

    public int getNum(){ return niveau; }

    public Plateau getPlateau(){
        return plateau;
    }

    public void setAsCleared(){
        clear = true;
    }

    //test si le niveau est termin�, si oui alors le boolean clear devient true
    public boolean clear(){
        if(plateau.nbAnimaux()!=0){
            clear = false;
            return false;
        }
        clear = true;
        return true;
    }

    //met � jour le nouveau record selon si le nouveau score est sup�rieur � l'ancien
    public void meilleurScore(int s){
        if(s>score) score = s;
    }

    public String toString(){
        System.out.print("Niveau "+niveau+" : ");
        return clear?"Termin�, meilleur score : "+score:"Non termin�.";
    }
}
