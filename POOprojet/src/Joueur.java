import java.util.Scanner;

public class Joueur {
	
	private Scanner sc;
	
	public int[] Coord() {
		sc = new Scanner(System.in);
		System.out.println("Entrez vos coordonées :");
		String s = sc.next();
		int[] coord = {-1,-1};
		if (s.length() == 2) {
			char c1 = s.charAt(0);
			char c2 = s.charAt(1);
			if (Character.isUpperCase(c1) && Character.isDigit(c2)) {
				coord[0] = c1-65;
				coord[1] = Character.getNumericValue(c2)-1;
				return coord;
			} else {
				coord[0] = -1;
				coord[1] = -1;
				System.out.println("Coordonées non valide");
			}
		} else {
			System.out.println("Coordonées non valide");
		}
		return coord;
	}
	
}
