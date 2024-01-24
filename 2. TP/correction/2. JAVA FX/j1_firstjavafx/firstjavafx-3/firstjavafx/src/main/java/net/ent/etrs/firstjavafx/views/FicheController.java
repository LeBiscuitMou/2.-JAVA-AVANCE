package net.ent.etrs.firstjavafx.views;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import net.ent.etrs.firstjavafx.model.entities.Personne;
import net.ent.etrs.firstjavafx.start.Lanceur;

import java.io.IOException;

public class FicheController {

    @FXML
    public TextField tfNom;

    @FXML
    public TextField tfPrenom;

    @FXML
    public DatePicker dpDdn;

    @FXML
    public void click() throws IOException {
        Personne p = new Personne(tfPrenom.getText(), tfNom.getText(), dpDdn.getValue());
        ListeController.getPersonneSet().add(p);
        Lanceur.loadFxml("liste");
    }
}
