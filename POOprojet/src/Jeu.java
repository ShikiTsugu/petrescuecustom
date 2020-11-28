public class Jeu {

    public static void main(String[] args){
        Cube b1 = new Bloc(0,0,"R");
        Cube b2 = new Bloc(0,0,"G");
        Cube b3 = new Bloc(0,0,"Y");
        Cube b4 = new Bloc(0,0,"B");
        Cube a = new Animaux(0,0);
        Cube o = new Obstacle(0,0);
        Cube[][] cubes = {{b1,a,b1,a,b2},{b1,b1,b2,b2,b1},{b1,b1,b4,b1,o},{b3,b3,b2,o,o},{b4,b4,o,o,o}};
        Plateau p = new Plateau(cubes);
        p.affiche();
    }
}
