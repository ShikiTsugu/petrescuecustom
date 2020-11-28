public class Bloc extends Cube {
	
	private String color;
	public static final String red = "\u001B[41m";
	public static final String green = "\u001B[42m";
	public static final String yellow = "\u001B[43m";
	public static final String blue = "\u001B[44m";
	public static final String contour = "\u001B[52m";
	public static final String reset = "\u001B[0m";
	
	public Bloc(int x, int y, String s) {
		super.x = x;
		super.y = y;
		color = s;
	}
	
	public String toString() {
		if(color.equals("R")) return red+"   "+reset;
		if(color.equals("G")) return green+"   "+reset;
		if(color.equals("Y")) return yellow+"   "+reset;
		if(color.equals("B")) return blue+"   "+reset;
		return "   ";
	}
	
	public void setColor(String s) {
		color = s;
	}

}
