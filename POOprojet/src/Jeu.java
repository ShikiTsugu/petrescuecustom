public class Jeu {

    public static void main(String[] args){
        Cube b = new Bloc(0,0,"R");
        Cube[][] cubes = {{b,b,b,b,b},{b,b,b,b,b},{b,b,b,b,b},{b,b,b,b,b},{b,b,b,b,b}};
        Plateau p = new Plateau(cubes);
        p.affiche();
    }
}
