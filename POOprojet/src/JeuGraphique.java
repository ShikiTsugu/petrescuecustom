import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class JeuGraphique {
    private ArrayList<Niveaux> niveaux = new ArrayList<>();
    private Save save = new Save();
    private Cube r = new Bloc(0,0,"R");
    private Cube g = new Bloc(0,0,"G");
    private Cube y = new Bloc(0,0,"Y");
    private Cube b = new Bloc(0,0,"B");
    private Cube p = new Bloc(0,0,"P");
    private Cube a = new Animaux(0,0);
    private Cube o = new Obstacle(0,0);

    public JeuGraphique(){
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
        Niveaux.resetCompteur();
        Niveaux n1 = new Niveaux(p1);Niveaux n2 = new Niveaux(p2);Niveaux n3 = new Niveaux(p3);Niveaux n4 = new Niveaux(p4);
        niveaux.add(n1);niveaux.add(n2);niveaux.add(n3);niveaux.add(n4);
        addNivtoSave();
        Load();
    	loadSave();
    }

    public ArrayList<Niveaux> getNiveaux(){
        return niveaux;
    }

    public void jouer(){
        IntGraphModel im = new IntGraphModel("src/background.png");
        IntGraphView iv = new IntGraphView(im);
        IntGraphControl ic = new IntGraphControl(im,iv);
        iv.pack();
        iv.setVisible(true);
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
        	System.out.println(e);
		} catch(IOException e) {
			File file = new File("src/Save.ser");
   		 
    		if (file.length() == 0) {
    			System.out.println(e);
    		}
        } catch (ClassNotFoundException e) {
        	System.out.println(e);
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
