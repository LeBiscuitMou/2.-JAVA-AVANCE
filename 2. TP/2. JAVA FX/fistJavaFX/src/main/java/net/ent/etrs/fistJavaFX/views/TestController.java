package net.ent.etrs.fistJavaFX.views;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lombok.NoArgsConstructor;
import net.ent.etrs.fistJavaFX.models.Personne;
import net.ent.etrs.fistJavaFX.start.Lanceur;

import java.io.IOException;
import java.util.Objects;

@NoArgsConstructor
public class TestController {

    @FXML
    public TextField nomTextField;

    @FXML
    public TextField prenomTextField;

    @FXML
    public DatePicker datePickerNaissance;

    private Personne personne = new Personne();

    public TestController(Personne personne) {
        this.personne = personne;
    }

    @FXML
    public void initialize() {
        nomTextField.setText(this.personne.getNom());
        prenomTextField.setText(this.personne.getPrenom());
        datePickerNaissance.setValue(this.personne.getDateDeNaissance());
    }

    @FXML
    public void click() throws IOException {
        if (null == nomTextField || null == prenomTextField || null == datePickerNaissance ||
            null == nomTextField.getText() || null == prenomTextField.getText() || null == datePickerNaissance.getValue() ||
            nomTextField.getText().isBlank() || prenomTextField.getText().isBlank() || null == datePickerNaissance.getValue()) {

            System.out.println("NULL");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Il faut saisir toutes les informations !");
            alert.showAndWait().ifPresent(rs -> {
                if (rs.equals(ButtonType.OK)) {
                    System.out.println("Pressed OK.");
                }
            });
            
        } else {
            this.personne.setNom(nomTextField.getText());
            this.personne.setPrenom(prenomTextField.getText());
            this.personne.setDateDeNaissance(datePickerNaissance.getValue());

            ListeController.getPersonnes().remove(personne);
            ListeController.getPersonnes().add(personne);
            Lanceur.loadFXML("liste");
        }
    }
}