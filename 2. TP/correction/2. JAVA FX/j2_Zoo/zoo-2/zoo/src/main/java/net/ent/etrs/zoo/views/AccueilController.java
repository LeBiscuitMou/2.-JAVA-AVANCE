package net.ent.etrs.zoo.views;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import net.ent.etrs.zoo.start.Lanceur;
import net.ent.etrs.zoo.views.utils.AlerteUtils;

public class AccueilController {
    public void goToListeAnimaux() {
        try {
            Lanceur.loadFxml("animal/liste");
        } catch (Exception e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void goToListeSoigneurs() {
        try {
            Lanceur.loadFxml("soigneur/liste");
        } catch (Exception e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
