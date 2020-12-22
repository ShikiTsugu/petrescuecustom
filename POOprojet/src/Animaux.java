public class Animaux extends Cube{
	
	private String type;
	
	public Animaux(int x, int y) {
		super.x = x;
		super.y = y;
		type = "A";
	}

	public String toString() {
		return type;
	}
}
