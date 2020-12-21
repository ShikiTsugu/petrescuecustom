public class Plateau {
    private Cube[][] cubes;

    public Plateau(Cube[][] c){
        cubes = c;
    }

    public Cube[][] getCubes(){
        return cubes;
    }

    //test si la colonne passée en argument est vide ou pas
    public boolean colVide(int x){
        for(int i = 0; i<cubes.length; i++){
            try {
                if(cubes[i][x] instanceof Obstacle && i!=0) return true; //on s'arrête si il y a un obstacle
                else if(cubes[i][x] != null) return false;
            }catch(NullPointerException e){}
        }
        return true;
    }

    //fait descendre les blocs d'un cran s'il y a du vide
    public void faireDescendre(int x){
        int posNull=0;
        for(int i = 0; i< cubes.length; i++){
            if(cubes[i][x]==null){
                posNull=i;
            }
        }
        for(int i = posNull; i>0 ; i--){
            cubes[i][x]=cubes[i-1][x];
            cubes[i-1][x]=null;
        }
    }

    //remplis la colonne vide par les elements à sa droite sauf les obstacles
    public void remplirCol(int x){
        if(x!=cubes.length-1) {
            for (int i = 0; i < cubes.length-1; i++) {
                for (int j = x; j< cubes[i].length-1;j++) {
                    if(!(cubes[i][j+1] instanceof Obstacle)){
                        cubes[i][j] = cubes[i][j + 1];
                        cubes[i][j+1]=null;
                    }
                }
            }
        }
    }

    //met à jour le plateau en remplissant les colonnes vides etc...
    public void miseAJour(){
        for(int i = 0; i<cubes.length; i++){
            for(int j = 0; j<cubes[i].length; j++) {
                if(colVide(j)){
                    remplirCol(j);
                }
                faireDescendre(j);
            }
        }
    }

    //affiche le plateau
    public void affiche(){
        char l = 'A';
        int n = 1;
        System.out.print("    ");
        for(Cube c : cubes[0]){
            System.out.print(n+"  ");
            n++;
        }
        System.out.println();
        for(Cube[] c : cubes){
            System.out.print(l+" |");
            l++;
            for(Cube b : c){
                if(b!=null) {
                    if (b instanceof Animaux) System.out.print(" " + b.toString() + " ");
                    if (b instanceof Bloc) System.out.print(b.toString());
                    if (b instanceof Obstacle) System.out.print(b.toString());
                }else System.out.print("   ");
            }
            System.out.println("|");
        }
    }

}