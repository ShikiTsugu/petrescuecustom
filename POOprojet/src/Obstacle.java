public class Obstacle extends Cube{
	private String type;

	public Obstacle(int x, int y, String s) {
		super.x = x;
		super.y = y;
		type = s;
	}

	public String getColor() {
		return type;
	}
}
