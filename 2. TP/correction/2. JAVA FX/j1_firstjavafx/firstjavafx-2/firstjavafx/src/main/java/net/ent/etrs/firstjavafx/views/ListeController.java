package net.ent.etrs.firstjavafx.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import net.ent.etrs.firstjavafx.start.Lanceur;

import java.io.IOException;

public class ListeController {

    @FXML
    public void goToFiche() throws IOException {
        Lanceur.loadFxml("test");
    }
}
