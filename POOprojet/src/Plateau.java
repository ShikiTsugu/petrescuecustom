public class Plateau {
	
    private Cube[][] cubes;
    private Joueur joueur;
    private int blocIni;

    public Plateau(Cube[][] c){
        cubes = c;
        joueur = new Joueur();
        blocIni = nbBlocInitial();
    }

    public Cube[][] getCubes(){
        return cubes;
    }

    //compte le nombre de bloc non null et qui n'est pas un obstacle initialement
    public int nbBlocInitial(){
        int nbBloc = 0;
        for(Cube[]c : cubes){
            for(Cube d : c){
                try{
                    if(d!=null&&!(d instanceof Obstacle)){
                        nbBloc++;
                    }
                }catch(NullPointerException e){}
            }
        }
        return nbBloc;
    }

    //compte le nombre de bloc supprimé
    public int nbBlocSuppr(){
        int blocSuppr = 0;
        for(Cube[]c : cubes){
            for(Cube d : c){
                try{
                    if(d!=null&&!(d instanceof Obstacle)){
                        blocSuppr++;
                    }
                }catch(NullPointerException e){}
            }
        }
        return blocIni-blocSuppr;
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
    
    //supprime le bloc choisi et les blocs autour
    public void supprimer(){
    	int[] coord = joueur.Coord();
    	int x = coord[0];
    	int y = coord[1];
    	if (x != -1 && y != -1 && cubes[x][y] instanceof Bloc) {
    		Bloc b = getBloc(x,y);
    		if(VerifSeul(x, y, b)) {
    			supprimerAux(x, y, b);
    		} else {
    			System.out.println("Ce bloc est seul, il ne peut pas être supprimer");
    		}
    	} else {
    		System.out.println("Ceci n'est pas un bloc");
    	}
    }
    
    
    public int supprimerAux(int x, int y, Cube c) {
		cubes[x][y] = null;
		int i = 1;
    	if (!outOfBound(x,y)) {
	    	if(c.equals(getBloc(x-1,y))) {		
	        	i = i + supprimerAux(x-1, y, c);
	    	}
	    	if(c.equals(getBloc(x,y-1))) {
	    		i = i + supprimerAux(x, y-1, c);
	    	}
	    	if(c.equals(getBloc(x+1,y))) {
	    		i = i + supprimerAux(x+1, y, c);
	    	}
	    	if(c.equals(getBloc(x,y+1))) {
	    		i = i + supprimerAux(x, y+1, c);
	    	}
    	}
    	return i;
    }
    
    public boolean VerifSeul(int x, int y, Cube c) {
    	Cube g = null;
    	Cube h = null;
    	Cube d = null;
    	Cube b = null;
    	if(x > 0) {
    		g = getBloc(x-1,y);
    	}
    	if(y > 0) {
    		h = getBloc(x,y-1);
    	}
    	if(x < cubes.length-1) {
    		d = getBloc(x+1,y);
    	}
    	if(y < cubes[x].length-1) {
    		b = getBloc(x,y+1);
    	}
    	if(c.equals(g) || c.equals(h) || c.equals(d) || c.equals(b)) {
    		return true;
    	}
    	return false;
    }
    
    public Bloc getBloc(int x, int y) {
    	if (!outOfBound(x,y)) {
	    	if (cubes[x][y] instanceof Bloc) {
	    		return (Bloc)cubes[x][y];
	    	}
    	}
    	return null;
    }
    
    public boolean outOfBound(int x, int y) {
    	if(x < 0 || x > cubes.length-1 || y < 0 || y > cubes[x].length-1)
    		return true;
    	return false;
    }

}