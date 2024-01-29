package controller;

import common.validator.ValidException;
import common.validator.ValidatorUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import model.entities.Client;
import model.entities.EntitiesFactory;
import model.exceptions.BusinessException;
import view.utils.AlerteUtils;
import view.utils.Screens;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GererClientController extends AbstractController {

    @FXML
    VBox barreMenu;

    @FXML
    TextField tfNom;

    @FXML
    TextField tfPrenom;

    @FXML
    TextField tfCourriel;

    @FXML
    Button btnCreerModifier;

    @FXML
    Button btnAnnuler;

    Client clientAModifier;

    protected GererClientController(Client client) {
        this.clientAModifier = client;
    }

    @FXML
    public void initialize() {
        initialiserClient();
    }

    private void initialiserClient() {
        if (!Objects.isNull(this.clientAModifier)) {
            this.tfNom.setText(this.clientAModifier.getNom());
            this.tfPrenom.setText(this.clientAModifier.getPrenom());
            this.tfCourriel.setText(this.clientAModifier.getCourriel());

            this.btnCreerModifier.setText("Modifier");
        }
    }

    public void creerModifierClient() {
//		Client client = EntitiesFactory.fabriquerClient(tfNom.getText(), tfPrenom.getText(), tfCourriel.getText());

        try {
            if (Objects.nonNull(this.clientAModifier)) {
                this.clientAModifier.setNom(tfNom.getText());
                this.clientAModifier.setPrenom(tfPrenom.getText());
                this.clientAModifier.setCourriel(tfCourriel.getText());
                ValidatorUtils.validate(this.clientAModifier);
                FACADE_CLIENT.save(this.clientAModifier);
            } else {
                this.clientAModifier = EntitiesFactory.fabriquerClient(tfNom.getText(), tfPrenom.getText(), tfCourriel.getText());
                ValidatorUtils.validate(this.clientAModifier);
                FACADE_CLIENT.save(this.clientAModifier);
            }

            this.annuler();
        } catch (ValidException | BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), AlertType.WARNING);
//			Alert a = new Alert(AlertType.ERROR);
//			a.setTitle("Ajout impossible");
//			a.setContentText(e.getMessage());
//			a.showAndWait();
        }
    }

    public void annuler() {
        this.chargerScene(this.barreMenu.getScene(), Screens.SCREEN_ACCUEIL, null);
    }
}
