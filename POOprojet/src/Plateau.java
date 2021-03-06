public class Plateau {
	
    private Cube[][] cubes;
    private SavePlateau save;
    private Joueur joueur;
    private Robot robot;
    private int blocIni;
    private final int nbAnimIni;
    private int nbAnimCour;
    private int nbAnimSuppr;

    public Plateau(Cube[][] c){
        cubes = c;
        save = new SavePlateau(c);
        joueur = new Joueur();
        robot = new Robot();
        blocIni = nbBlocInitial();
        nbAnimIni = nbAnimaux();
        nbAnimCour = nbAnimIni;
        nbAnimSuppr = 0;
    }

    public int getNbAnimSuppr(){
        return nbAnimSuppr;
    }

    public int getNbAnimIni(){
        return nbAnimIni;
    }

    public Cube[][] getCubes(){
        return cubes;
    }
    
    public void setCube(int x, int y, Cube c) {
    	cubes[x][y] = c;
    }

    public boolean vide(){
        for(Cube[]c : cubes){
            for(Cube d : c) {
                if(d!=null&&!(d instanceof Obstacle)) return false;
            }
        }
        return true;
    }

    public int nbAnimaux(){
        int nbAnim = 0;
        for(Cube[]c : cubes){
            for(Cube d : c){
                try{
                    if(d instanceof Animaux){
                        nbAnim++;
                    }
                }catch(NullPointerException e){}
            }
        }
        return nbAnim;
    }

    public void detectAnimaux() {
        for(int i = 0; i < cubes[cubes.length-1].length; i++) {
            if (cubes[cubes.length-1][i] instanceof Animaux) {
                cubes[cubes.length-1][i] = null;
                nbAnimSuppr++;
            }
        }
    }

    public int nbAnimauxSuppr() {
        int AnimSuppr = 0;
        int AnimCourant = nbAnimCour;
        for(Cube[]c : cubes){
            for(Cube d : c){
                try{
                    if(d instanceof Animaux){
                        AnimSuppr++;
                    }
                }catch(NullPointerException e){}
            }
        }
        nbAnimCour = AnimSuppr;
        miseAJour();
        return AnimCourant-AnimSuppr;
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

    //compte le nombre de bloc supprim?
    public int nbBlocSuppr(){
        int blocSuppr = 0;
        int blocCourant = blocIni;
        for(Cube[]c : cubes){
            for(Cube d : c){
                try{
                    if(d!=null&&!(d instanceof Obstacle)){
                        blocSuppr++;
                    }
                }catch(NullPointerException e){}
            }
        }
        blocIni = blocSuppr;
        return blocCourant-blocSuppr;
    }

    //test si la colonne pass?e en argument est vide ou pas
    public boolean colVide(int x){
        for(int i = 0; i<cubes.length; i++){
            try {
                if(cubes[i][x] instanceof Obstacle && i!=0) return true; //on s'arr?te si il y a un obstacle
                else if(cubes[i][x] != null) return false;
            }catch(NullPointerException e){}
        }
        return true;
    }

    //fait descendre les blocs d'un cran s'il y a du vide
    public void faireDescendre(int x){
        int posNull=0;
        //cherche la position null
        for(int i = 0; i< cubes.length; i++){
            if(cubes[i][x]==null){
                posNull=i;
            }
        }
        //parcourt de la colonne o? se trouve la position null et fait descendre tous les ?l?ments se trouvant au dessus de la position null
        for(int i = posNull; i>0 ; i--){
            if(!(cubes[i-1][x] instanceof Obstacle)) {
                cubes[i][x] = cubes[i - 1][x];
                cubes[i - 1][x] = null;
            }
        }
    }

    //remplis la colonne vide par les elements ? sa droite sauf les obstacles
    public void remplirCol(int x){
        if(x!=cubes.length-1) {
            for (int i = 0; i < cubes.length; i++) {
                for (int j = x; j< cubes[i].length-1;j++) {
                    if(!(cubes[i][j+1] instanceof Obstacle)){
                        cubes[i][j] = cubes[i][j + 1];
                        cubes[i][j + 1] = null;
                    }
                }
            }
        }
    }

    //met ? jour le plateau en remplissant les colonnes vides etc...
    public void miseAJour(){
        for(int i = 0; i<cubes.length; i++){
            for(int j = 0; j<cubes[i].length; j++) {
                detectAnimaux();
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
    	try {
            if (x != -1 && y != -1 && cubes[x][y] instanceof Bloc) {
                Bloc b = getBloc(x, y);
                if (VerifSeul(x, y, b)) {
                    supprimerAux(x, y, b);
                } else {
                    System.out.println("Ce bloc est seul, il ne peut pas ?tre supprimer");
                }
            } else {
                System.out.println("Ceci n'est pas un bloc");
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Coordon?es non valide");
        }
    }

    public void supprimerRob(){
        int[] coord = robot.Coord(cubes);
        int x = coord[0];
        int y = coord[1];
        try {
            if (x != -1 && y != -1 && cubes[x][y] instanceof Bloc) {
                Bloc b = getBloc(x, y);
                if (VerifSeul(x, y, b)) {
                    supprimerAux(x, y, b);
                } else {
                    System.out.println("Ce bloc est seul, il ne peut pas ?tre supprimer");
                }
            } else {
                System.out.println("Ceci n'est pas un bloc");
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Coordon?es non valide");
        }
    }
    
    
    public void supprimerAux(int x, int y, Cube c) {
		cubes[x][y] = null;
    	if (!outOfBound(x,y)) {
	    	if(c.equals(getBloc(x-1,y))) {		
	        	supprimerAux(x-1, y, c);
	    	}
	    	if(c.equals(getBloc(x,y-1))) {
	    		supprimerAux(x, y-1, c);
	    	}
	    	if(c.equals(getBloc(x+1,y))) {
	    		supprimerAux(x+1, y, c);
	    	}
	    	if(c.equals(getBloc(x,y+1))) {
	    		supprimerAux(x, y+1, c);
	    	}
    	}
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
    
    public void resetPlateau() {
    	for (int x = 0; x < save.getSave().length; x++) {
			for (int y = 0; y < save.getSave()[0].length; y++) {
				cubes[x][y] = save.getSave()[x][y];
			}
		}
    	blocIni = nbBlocInitial();
        nbAnimCour = nbAnimaux();
        nbAnimSuppr = 0;
    }
}