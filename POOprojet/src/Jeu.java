import java.util.Scanner;

public class Jeu {

    public void choixVersion(){
        Scanner sc = new Scanner(System.in);
        System.out.println("A quelle version voulez vous jouer ? Terminal(T) ou Graphique(G) ?");
        String s = sc.nextLine();
        if(s.equals("T")) {
            System.out.println("\n" +
                    "  ___     _     ___                       ___                 \n" +
                    " | _ \\___| |_  | _ \\___ ___ __ _  _ ___  / __| __ _ __ _ __ _ \n" +
                    " |  _/ -_)  _| |   / -_|_-</ _| || / -_) \\__ \\/ _` / _` / _` |\n" +
                    " |_| \\___|\\__| |_|_\\___/__/\\__|\\_,_\\___| |___/\\__,_\\__, \\__,_|\n" +
                    "                                                   |___/      \n");
            JeuTerminal j = new JeuTerminal();
            j.jouer();
        }else if(s.equals("G")){
            JeuGraphique j = new JeuGraphique();
            j.jouer();
        }
        else System.out.println("Je n'ai pas compris");
    }

    public static void main(String[] args){
        Jeu j = new Jeu();
        j.choixVersion();
    }
}
