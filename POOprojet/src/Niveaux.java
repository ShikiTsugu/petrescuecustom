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

    //test si le niveau est terminé, si oui alors le boolean clear devient true
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

    //met à jour le nouveau record selon si le nouveau score est supérieur à l'ancien
    public void meilleurScore(int s){
        if(s>score) score = s;
    }

    public String toString(){
        System.out.print("Niveau "+niveau+" : ");
        return clear?"Terminé, meilleur score : "+score:"Non terminé.";
    }
}
