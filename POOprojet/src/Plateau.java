public class Plateau {
    private Cube[][] cubes;

    public Plateau(Cube[][] c){
        cubes = c;
    }

    public Cube[][] getCubes(){
        return cubes;
    }

    public void miseAJour(){
        for(int i = 0; i<cubes.length; i++){
            for(int j = 0; j<cubes[i].length; j++){
                if(cubes[i][j]==null&&cubes[i-1][j]!=null){
                    Cube tmp = cubes[i-1][j];
                    cubes[i][j]=tmp;
                    cubes[i-1][j]=null;
                }
                if(cubes[i]==null&&cubes[i+1]!=null){
                    Cube[]tmp = cubes[i+1];
                    cubes[i]=tmp;
                    cubes[i+1]=null;
                }
            }
        }
    }

    public void affiche(){
        for(Cube[] c : cubes){
            System.out.print("| ");
            for(Cube b : c){
                if(b!=null) {
                    if (b instanceof Animaux) System.out.print(" " + b.toString() + " ");
                    if (b instanceof Bloc) System.out.print(b.toString());
                    if (b instanceof Obstacle) System.out.print(b.toString());
                }else System.out.print("   ");
            }
            System.out.println(" |");
        }
    }

}
