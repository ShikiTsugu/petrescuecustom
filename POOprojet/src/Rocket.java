
public class Rocket {
	
	private int compteur;
	private int limite;
	private Plateau p;
	private Joueur j;
	
	public void addCompt(int i) {
		compteur = compteur + i;
	}
	
	public boolean available() {
		if(compteur >= limite) {
			return true;
		}
		return false;
	}
	
	public void useRocket() {
		if (available()) {
			int[] coord = j.Coord();
			int y = coord[1];
			Cube[][] cubes = p.getCubes();
			for (int i = 0; i < cubes.length; i++) {
				if(cubes[i][y] instanceof Bloc) {
					p.setCube(i,y,null);
				}
			}
		}
	}
	
}
