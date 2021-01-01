import java.io.Serializable;

public class SavePlateau implements Serializable{

	private Cube[][] savePlateau;
	
	public SavePlateau(Cube[][] c) {
		savePlateau = new Cube[c.length][c[0].length];
		for (int x = 0; x < c.length; x++) {
			for (int y = 0; y < c[0].length; y++) {
				savePlateau[x][y] = c[x][y];
			}
		}
	}
	
	public Cube[][] getSave() {
		return savePlateau;
	}
	
}
