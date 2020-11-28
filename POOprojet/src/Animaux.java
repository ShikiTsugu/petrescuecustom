public class Animaux extends Cube{
	
	private String type;
	
	public Animaux(int x, int y, String s) {
		super.x = x;
		super.y = y;
		type = s;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String s) {
		type = s;
	}

	public String getColor() {
		return type;
	}
}
