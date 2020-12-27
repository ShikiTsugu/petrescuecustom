import java.awt.event.ActionEvent;

public class IntGraphControl {
    private IntGraphModel model;
    private IntGraphView view;

    public IntGraphControl(IntGraphModel m, IntGraphView v){
        model = m;
        view = v;
        view.getJouer().addActionListener((ActionEvent e) -> jouer());
        view.getQuitter().addActionListener((ActionEvent e) -> System.exit(0));
        view.getRetour().addActionListener((ActionEvent e) -> {
            view.afficheIni();
            view.update();
        });
        for(int i = 0; i<view.getNiveaux().size(); i++) {
            int pos = i;
            view.getNiveaux().get(i).addActionListener((ActionEvent e) -> {
                if (view.getNiveaux().get(pos).isEnabled()) {
                    view.affichePlateau(view.initialisationPlateau(view.getJg().getNiveaux().get(pos)), view.getJg().getNiveaux().get(pos));
                }
            });
        }
    }

    public void jouer() {
        view.reset();
        view.affichemenu();
        view.update();
    }
}
