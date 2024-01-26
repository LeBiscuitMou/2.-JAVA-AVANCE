package net.ent.etrs.garage.views;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import net.ent.etrs.garage.start.Lanceur;
import net.ent.etrs.garage.views.utils.AlerteUtils;

import java.io.IOException;

public class AccueilController {
    @FXML
    public void goToListeVoiture() {
        try {
            Lanceur.loadFxml("voiture/listerVoitures");
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void goToListesMarques() {
        try {
            Lanceur.loadFxml("marque/listerMarques");
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}