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

    //test si le niveau est termin�, si oui alors le boolean clear devient true
    public boolean clear(){
        for(Cube[] c : plateau.getCubes()){
            for(Cube d : c){
                try {
                    if (d instanceof Animaux) {
                        return false;
                    }
                }catch(NullPointerException e){}
            }
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
