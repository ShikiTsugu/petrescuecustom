public class Obstacle extends Cube{
	private String type;

	public Obstacle(int x, int y) {
		super.x = x;
		super.y = y;
		type = "X";
	}

	public String toString() {
		return type;
	}
}
