package net.ent.etrs.garage.views.marque;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.*;
import net.ent.etrs.garage.model.entities.Marque;
import net.ent.etrs.garage.model.entities.references.Nationalite;
import net.ent.etrs.garage.model.facade.IFacadeMetierMarque;
import net.ent.etrs.garage.model.facade.exceptions.BusinessException;
import net.ent.etrs.garage.model.facade.impl.FacadeMetierFactory;
import net.ent.etrs.garage.start.Lanceur;
import net.ent.etrs.garage.views.utils.AlerteUtils;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Objects;

@NoArgsConstructor
public class FicheMarqueController {
    @FXML
    public TextField txtLibelle;

    @FXML
    public ButtonBar btnBarImageBouton;

    @FXML
    public Button btnChoisirImage;

    private String cheminComplet;

    @FXML
    public ImageView imgViewCheminComplet;

    @FXML
    public ComboBox<Nationalite> cmbNationalite;

    @FXML
    public Button btnValider;

    private IFacadeMetierMarque facadeMetierMarque = FacadeMetierFactory.fabriquerFacadeMetierMarque();

    private Marque marque = new Marque();

    public FicheMarqueController(Marque marqueToEdit) {
        this.marque = marqueToEdit;
    }

    @FXML
    public void initialize() {
        txtLibelle.setText(marque.getLibelle());

        if (Objects.nonNull(marque.getCheminComplet()) && !marque.getCheminComplet().isBlank()) {
            this.cheminComplet = marque.getCheminComplet();
            Image selectedImage = new Image(cheminComplet);

            this.btnChoisirImage.setText("Image trouvée !");
            this.imgViewCheminComplet.setImage(selectedImage);
        } else {
            cheminComplet = "";

            this.btnChoisirImage.setText("Choisir image");
            this.imgViewCheminComplet.setImage(null);
        }

        ObservableList<Nationalite> nationaliteObservableList = FXCollections.observableArrayList();
        nationaliteObservableList.addAll(Nationalite.values());
        cmbNationalite.setItems(nationaliteObservableList);
        cmbNationalite.setValue(marque.getNationalite());
    }

    @FXML
    public void valider() {
        try {
            marque.setLibelle(txtLibelle.getText());
            marque.setCheminComplet(cheminComplet);
            marque.setNationalite(cmbNationalite.getValue());

            facadeMetierMarque.creerMarque(marque);

            Lanceur.loadFxml("marque/listerMarques");
        } catch (BusinessException | IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void choisirImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner une image");

        FileChooser.ExtensionFilter imgFilter = new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp");
        fileChooser.getExtensionFilters().add(imgFilter);

        File selectedFile = fileChooser.showOpenDialog(Lanceur.stage);

        try {
            if (Objects.nonNull(selectedFile)) {
                this.cheminComplet = Objects.requireNonNull(FicheMarqueController.class.getResource("/" + selectedFile.getName())).toURI().toString();
                Image selectedImage = new Image(cheminComplet);

                this.btnChoisirImage.setText("Image trouvée !");
                this.imgViewCheminComplet.setImage(selectedImage);
            }
        } catch (URISyntaxException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}