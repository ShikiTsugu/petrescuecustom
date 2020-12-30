public class Robot {
    public int[] Coord(Cube[][] c) {
        int randx = 0 + (int) (Math.random()*((c[0].length-1-0)+1));
        int randy = 0 + (int) (Math.random()*((c.length-1-0)+1));
        int[] coord = {randx,randy};
        System.out.println("Choix du robot : ("+coord[0]+", "+coord[1]+")");
        return coord;
    }
}
