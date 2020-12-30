import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class JeuTerminal implements Serializable{
	private ArrayList<Niveaux> niveaux = new ArrayList<>();
	private Save save = new Save();
    private Cube r = new Bloc(0,0,"R");
    private Cube g = new Bloc(0,0,"G");
    private Cube y = new Bloc(0,0,"Y");
    private Cube b = new Bloc(0,0,"B");
    private Cube p = new Bloc(0,0,"P");
    private Cube a = new Animaux(0,0);
    private Cube o = new Obstacle(0,0);
    private final Robot robot;
    private final Joueur joueur;
    private boolean valide;

    public JeuTerminal(){
        joueur = new Joueur();
        robot = new Robot();

        //Création des tableaux pour les plateaux
        Cube[][] c1 = {{null,a,null,null,null,a,null},{p,p,r,r,r,y,y},{p,p,b,b,b,y,y},{r,r,b,b,b,g,g},{r,r,b,b,b,g,g}
                ,{p,y,y,r,p,p,r},{p,y,y,r,p,p,r}};
        Cube[][] c2 = {{null,p,a,p,null},{a,p,a,p,a},{r,y,a,y,r},{r,y,r,y,r},{y,p,r,p,y},{y,p,r,p,y},{p,y,r,y,p},{p,y,r,y,p}};
        Cube[][] c3 = {{null,null,a,null,a,null,a},{null,null,p,y,b,y,b},{null,null,p,y,b,y,b},{null,null,y,b,p,y,p},
                {b,b,y,b,p,y,p},{p,b,b,p,b,p,b},{y,b,b,p,b,p,b},{p,p,y,b,y,o,o},{b,b,y,b,y,o,o}};
        Cube[][] c4 = {{null,null,null,null,a,a,a,a},{null,null,null,p,p,y,p,r},{null,null,p,r,p,y,p,r},{null,p,p,r,y,r,y,y},{r,y,r,y,y,r,y,o},
                {y,y,r,y,p,r,o,o},{r,r,p,r,p,o,o,o},{p,r,p,r,o,o,o,o}};

        //Création des plateaux pour les niveaux
        Plateau p1 = new Plateau(c1);Plateau p2 = new Plateau(c2);Plateau p3 = new Plateau(c3);Plateau p4 = new Plateau(c4);

        //Création des niveaux et ajout des niveaux
        Niveaux n1 = new Niveaux(p1);Niveaux n2 = new Niveaux(p2);Niveaux n3 = new Niveaux(p3);Niveaux n4 = new Niveaux(p4);
        niveaux.add(n1);niveaux.add(n2);niveaux.add(n3);niveaux.add(n4);
        addNivtoSave();
        Load();
    	loadSave();
    }

    //montre la liste des niveaux débloqués, on affiche toujours le tout premier niveau
    public void montrerNiv(){
        System.out.println("Niveaux :");
        for(int i = 0; i<niveaux.size(); i++){
            if(niveaux.get(i).getClear()) {
                System.out.println(niveaux.get(i));
            }
            else {
                System.out.println(niveaux.get(i));
                break;
            }
        }
    }

    //demande à l'utilisateur un niveau à choisir
    public Niveaux selectNiv(){
        Scanner sc = new Scanner(System.in);
        montrerNiv();
        System.out.println("\nChoisissez un niveau en entrant le numéro (Tapez 'Reset' pour écraser votre sauvegarde):");
        //test si le scanner est correct
        try {
        	
        	String s = sc.next();
        	if (isNumeric(s)) {
        		int n = Integer.parseInt(s);
                //si oui on parcourt les niveaux et si ça correspond on renvoit ce niveau, on affiche seulement les niveaux débloqués
                for(Niveaux niv : niveaux){
                    if(n==niv.getNum()) {
                        //si c'est le premier niveau alors on le retourne
                        if (niveaux.indexOf(niv) == 0) {
                            valide = true;
                            return niv;
                        }
                        //sinon on test si le niveau précédent a été complété, si oui alors on accepte la requête de faire le niveau suivant
                        else {
                            if (niveaux.get(niveaux.indexOf(niv) - 1).getClear()) {
                                valide = true;
                                return niv;
                                // si on essaye de faire un niveau alors que le niveau précédent n'est pas terminé on retourne null
                            } else if (!niveaux.get(niveaux.indexOf(niv) - 1).getClear()) {
                                valide = false;
                                System.out.println("Niveau précédent non terminé.");
                                return null;
                            }
                        }
                    }
                }
        	} else {
        		if (s.equals("Reset")) {
        			resetSave();
        			return null;
        		}
        	}
            //si le scanner est incorrect au retourne null
        }catch(InputMismatchException e){
            valide=false;
            System.out.println("Mauvaise formulation.");
            return null;
        }
        //si aucun niveau n'a été trouvé c'est qu'il n'existe pas, on retourne null
        valide = false;
        System.out.println("Niveau inexistant.");
        return null;
    }
    
    public boolean isNumeric(String input) {
    	  try {
    	    Integer.parseInt(input);
    	    return true;
    	  }
    	  catch (NumberFormatException e) {
    	    return false;
    	  }
    }

    //vérifie si tous les niveaux ont été débloqués
    public boolean toutDebloque(){
        for(Niveaux niv : niveaux){
            if(!niv.getClear()) return false;
        }
        return true;
    }

    public void allCleared(){
        for(Niveaux niv : niveaux){
            niv.setAsCleared();
        }
    }
    
    public String choixJoueur(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Voulez vous jouer ou voulez vous laisser le robot jouer ? (J) pour vous, (R) pour le robot.");
        String s = sc.nextLine();
        return s;
    }

    public void jouer(){
        try{
            if(toutDebloque()) {
                System.out.println("Vous avez débloqué tous les niveaux.\n");
                allCleared();
            }
            Niveaux niv = selectNiv();
            if(valide) {
                Score s = new Score();
                int score = 0;
                int animIni = niv.getPlateau().getNbAnimIni();
                System.out.println("Score : " + score);
                System.out.println("Animaux : 0/"+animIni);
                niv.getPlateau().affiche();
                String choix = choixJoueur();
                while (!niv.clear()) {
                	if(choix.equals("J")) niv.getPlateau().supprimer();
                    else niv.getPlateau().supprimerRob();
                    s.calcul(niv.getPlateau().nbBlocSuppr());
                    s.animauxPoint(niv.getPlateau().nbAnimauxSuppr());
                    score = s.getScore();
                    System.out.println("Score : " + score);
                    System.out.println("Animaux : "+niv.getPlateau().nbAnimauxSuppr()+"/"+animIni);
                    niv.getPlateau().miseAJour();
                    niv.getPlateau().affiche();
                }
                if(niv.getPlateau().vide())score+=10000;
                niv.meilleurScore(score);
                Scanner sc = new Scanner(System.in);
                System.out.println(niv+"\nVoulez vous continuer ? Répondez par o si oui, n sinon.");
                String rep = sc.next();
                if(rep.equals("o")) jouer();
                else {
                    System.out.println("Progression sauvegardé.");
                    saveNiv();
                    Save();
                }
            }else{
                jouer();
            }
        }catch(NullPointerException e){
            jouer();
        }
    }
    
    public void Save() {
    	try {
        	FileOutputStream fos = new FileOutputStream("src/Save.ser");
        	
        	ObjectOutputStream oos = new ObjectOutputStream(fos);
        	
        	oos.writeObject(save);
        	
        	oos.close();
        } catch(IOException e) {
        	e.printStackTrace();
        }
    }
    
    public void Load() {
    	try {
        	FileInputStream fis = new FileInputStream("src/Save.ser");
        	
        	ObjectInputStream ois = new ObjectInputStream(fis);
        	
        	save = (((Save)ois.readObject()));
        	
        	ois.close();
        } catch (FileNotFoundException e) {
			System.out.println("Il n'y a pas de sauvegarde");
		} catch(IOException e) {
			File file = new File("src/Save.ser");
   		 
    		if (file.length() == 0) {
    			System.out.println("Il n'y a pas de sauvegarde");
    		}
        } catch (ClassNotFoundException e) {
        	System.out.println("test3");
		}
    }
    
    public void saveNiv() {
    	for (Niveaux niv : niveaux) {
    		save.setNiveauClear(niv.getClear(),niv.getNum()-1);
    		save.setNiveauScore(niv.getScore(),niv.getNum()-1);
    	}
    }
    
    public void addNivtoSave() {
    	for (Niveaux niv : niveaux) {
    		save.addNiveauClear(niv.getClear());
    		save.addNiveauScore(niv.getScore());
    	}
    }
    
    public void loadSave() {
    	int i = 0;
    	for (boolean nivclear : save.getNiveauxClear()) {
    		if (nivclear) {
    			niveaux.get(i).setAsCleared();
    		}
    		i++;
    	}
    	i = 0;
    	for (int nivscore : save.getNiveauxScore()) {
    		niveaux.get(i).meilleurScore(nivscore);
    		i++;
    	}
    }
    
    public void resetSave() {
    	File file = new File("src/Save.ser");
		
		if (file.length() == 0) {
			System.out.println("Il n'y a pas de sauvegarde");
		} else {
	    	try {
	    		
	    		PrintWriter writer = new PrintWriter("src/Save.ser");
	        	writer.print("");
	        	writer.close();
	        	for (Niveaux niv : niveaux) {
	        		niv.setAsUncleared();
	        		niv.setScore(0);
	        	}
	    	} catch (FileNotFoundException e) {
	    		e.printStackTrace();
	    	}
		}
    }
}
