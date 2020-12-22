public class Animaux extends Cube{
	
	private String type;
	private final int score;
	
	public Animaux(int x, int y) {
		super.x = x;
		super.y = y;
		type = "A";
		score = 1000;
	}

	public String toString() {
		return type;
	}
}
