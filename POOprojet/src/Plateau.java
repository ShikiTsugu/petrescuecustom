public class Plateau {
    private Cube[][] cubes;

    public Plateau(Cube[][] c){
        cubes = c;
    }

    public void affiche(){
        for(Cube[] c : cubes){
            for(Cube b : c){
                if(b instanceof Animaux){
                    System.out.print(" A ");
                }
                if(b instanceof Bloc){
                    System.out.print(" "+Bloc.getColor()+" ");
                }
                if(b instanceof Obstacle){
                    System.out.print(" X ");
                }
            }
        }
    }

}
