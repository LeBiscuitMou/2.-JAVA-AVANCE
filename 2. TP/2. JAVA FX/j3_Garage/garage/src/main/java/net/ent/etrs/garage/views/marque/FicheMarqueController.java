package net.ent.etrs.garage.views.marque;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import lombok.NoArgsConstructor;
import net.ent.etrs.garage.model.entities.Marque;
import net.ent.etrs.garage.model.entities.references.Nationalite;
import net.ent.etrs.garage.model.facade.IFacadeMetierMarque;
import net.ent.etrs.garage.model.facade.exceptions.BusinessException;
import net.ent.etrs.garage.model.facade.impl.FacadeMetierFactory;
import net.ent.etrs.garage.start.Lanceur;
import net.ent.etrs.garage.views.utils.AlerteUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

@NoArgsConstructor
public class FicheMarqueController {
    @FXML
    public TextField txtLibelle;

    @FXML
    public Button btnChoisirImage;

    private String cheminComplet;

    @FXML
    public ImageView imgViewCheminComplet;

    @FXML
    public ComboBox<Nationalite> cmbNationalite;

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
            if (null == txtLibelle.getText() || txtLibelle.getText().isBlank()
            || null == imgViewCheminComplet.getImage()
            || cheminComplet.isBlank()
            || null == cmbNationalite.getValue()) {
                AlerteUtils.afficherMessageDansAlerte("Attention !!!", "Il faut saisir toutes les informations !", Alert.AlertType.WARNING);
            } else {
                marque.setLibelle(txtLibelle.getText());
                marque.setCheminComplet(cheminComplet);
                marque.setNationalite(cmbNationalite.getValue());

                facadeMetierMarque.creerMarque(marque);

                Lanceur.loadFxml("marque/listerMarques");
            }
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

        if (Objects.nonNull(selectedFile)) {
            this.cheminComplet = selectedFile.toURI().toString();
            Image selectedImage = new Image(cheminComplet);

            this.btnChoisirImage.setText("Image trouvée !");
            this.imgViewCheminComplet.setImage(selectedImage);
        }
    }

    @FXML
    public void goToListeMarques() {
        try {
            Lanceur.loadFxml("marque/listerMarques");
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}