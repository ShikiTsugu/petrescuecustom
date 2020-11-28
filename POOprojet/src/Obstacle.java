public class Obstacle extends Cube{
	private String type;
	private static final String black = "\u001B[100m";
	public static final String reset = "\u001B[0m";

	public Obstacle(int x, int y) {
		super.x = x;
		super.y = y;
		type = "X";
	}

	public String toString() {
		return black+" "+type+" "+reset;
	}
}
