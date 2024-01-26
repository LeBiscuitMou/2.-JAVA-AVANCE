package net.ent.etrs.zoo.views;

import javafx.event.ActionEvent;
import net.ent.etrs.zoo.start.Lanceur;

public class AccueilController {
    public void goToListeAnimaux() {
        try {
            Lanceur.loadFxml("animal/liste");
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
        }
    }

    public void goToListeSoigneurs() {
        try {
            Lanceur.loadFxml("soigneur/liste");
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
        }
    }
}
