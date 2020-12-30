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

    public int getScore(){return score;}
    
    public boolean getClear() {return clear;};
    
    public void setAsCleared(){
        clear = true;
    }
    
    public void setAsUncleared() {
    	clear = false;
    }
    
    public void setScore(int s) {
    	score = s;
    }

    //test si le niveau est terminé, si oui alors le boolean clear devient true
    public boolean clear(){
        if(plateau.nbAnimaux()!=0){
            clear = false;
            return false;
        }
        clear = true;
        return true;
    }

    //met à jour le nouveau record selon si le nouveau score est supérieur à l'ancien
    public void meilleurScore(int s){
        if(s>score) score = s;
    }

    public String toString(){
        System.out.print("Niveau "+niveau+" : ");
        return clear?"Terminé, meilleur score : "+score : "Non terminé.";
    }
}
