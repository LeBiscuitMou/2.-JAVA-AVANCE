package net.ent.etrs.megaMovies.views;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import net.ent.etrs.megaMovies.start.Lanceur;
import net.ent.etrs.megaMovies.views.utils.AlerteUtils;

import java.io.IOException;

public class AccueilController {
    @FXML
    public void goToListeRealisateur() {
        try {
            Lanceur.loadFxml("realisateur/listeRealisateurs");
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void goToListeFilm() {
        try {
            Lanceur.loadFxml("film/listeFilms");
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void quitter() {
        if (AlerteUtils.afficherMessageDansAlerte("Quitter", "Êtes-vous sûr de vouloir quitter ?", Alert.AlertType.CONFIRMATION)) {
            Platform.exit();
        }
    }
}
