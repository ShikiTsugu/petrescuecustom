public class Bloc extends Cube {
	
	private String color;
	
	public Bloc(int x, int y, String s) {
		super.x = x;
		super.y = y;
		color = s;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String s) {
		color = s;
	}
	
}
