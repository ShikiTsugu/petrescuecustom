public class Score {
	
	private int score;
	
	public Score() { score = 0; }

	public int getScore(){
		return score;
	}
	
	public void calcul(int i){
		score = score + i*i*10;
	}
	
	public void animauxPoint(int i) {
		score = score + i*1000;
	}
	
	public void affiche() {
		System.out.println("Score = " + score);
	}
	
}
