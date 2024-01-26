package net.ent.etrs.garage.views.marque;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@NoArgsConstructor
public class FicheMarqueController {
    @FXML
    public TextField txtLibelle;

    @FXML
    public ButtonBar btnBarImageBouton;

    @FXML
    public Button btnChoisirImage;

    @FXML
    public ImageView imgViewCheminComplet;

    @FXML
    public ComboBox cmbNationalite;

    @FXML
    public Button btnValider;

    @FXML
    public void valider() {
    }

    @FXML
    public void chosirImage() {
    }
}