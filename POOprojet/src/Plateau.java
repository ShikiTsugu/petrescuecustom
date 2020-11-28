public class Plateau {
    private Cube[][] cubes;

    public Plateau(Cube[][] c){
        cubes = c;
    }

    public void affiche(){
        for(Cube[] c : cubes){
            System.out.print("| ");
            for(Cube b : c){
                if(b instanceof Animaux){
                    System.out.print(" "+b.toString()+" ");
                }
                if(b instanceof Bloc){
                    System.out.print(" "+b.toString()+" ");
                }
                if(b instanceof Obstacle){
                    System.out.print(" "+b.toString()+" ");
                }
            }
            System.out.println(" |");
        }
    }

}
