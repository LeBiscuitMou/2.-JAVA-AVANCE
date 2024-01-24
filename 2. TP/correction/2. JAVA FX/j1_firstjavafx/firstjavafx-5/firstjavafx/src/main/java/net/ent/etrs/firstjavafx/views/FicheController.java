package net.ent.etrs.firstjavafx.views;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lombok.NoArgsConstructor;
import net.ent.etrs.firstjavafx.model.entities.Personne;
import net.ent.etrs.firstjavafx.start.Lanceur;

import java.io.IOException;

@NoArgsConstructor
public class FicheController {

    @FXML
    public TextField tfNom;

    @FXML
    public TextField tfPrenom;

    @FXML
    public DatePicker dpDdn;

    private Personne personne = new Personne();

    public FicheController(Personne personne) {
        this.personne = personne;
    }

    @FXML
    public void initialize() {
        tfNom.setText(this.personne.getNom());
        tfPrenom.setText(this.personne.getPrenom());
        dpDdn.setValue(this.personne.getDdn());
    }

    @FXML
    public void click() throws IOException {
        personne.setNom(tfNom.getText());
        personne.setPrenom(tfPrenom.getText());
        personne.setDdn(dpDdn.getValue());
        ListeController.getPersonneSet().remove(personne);
        ListeController.getPersonneSet().add(personne);
        Lanceur.loadFxml("liste");
    }
}
