import java.io.Serializable;
import java.util.ArrayList;

public class Save implements Serializable{

	private ArrayList<Boolean> niveauxclear;
	private ArrayList<Integer> niveauxscore;
	
	public Save() {
		niveauxclear = new ArrayList<Boolean>();
		niveauxscore = new ArrayList<Integer>();
	}
	
	public ArrayList<Boolean> getNiveauxClear() {
		return niveauxclear;
	}
	
	public ArrayList<Integer> getNiveauxScore() {
		return niveauxscore;
	}
	
	public void addNiveauClear(boolean b) {
		niveauxclear.add(b);
	}
	
	public void addNiveauScore(int i) {
		niveauxscore.add(i);
	}
	
	public void setNiveauClear(boolean b, int j) {
		niveauxclear.set(j,b);
	}
	
	public void setNiveauScore(int i, int j) {
		niveauxscore.set(j,i);
	}
	
}
