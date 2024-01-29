package net.ent.etrs.squelette.views;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import net.ent.etrs.squelette.start.Lanceur;
import net.ent.etrs.squelette.views.utils.AlerteUtils;

import java.io.IOException;

public class AccueilController {
    @FXML
    public Menu mnuAide;

    @FXML
    public MenuBar mnbBarre;

    @FXML
    public void initialize() {

    }

    @FXML
    public void goToAccueil() {
        try {
            Lanceur.loadFxml("accueil");
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void goToTest1() {
        try {
            Lanceur.loadFxml("test");
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void goToTest2() {
        try {
            Lanceur.loadFxml("test");
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
