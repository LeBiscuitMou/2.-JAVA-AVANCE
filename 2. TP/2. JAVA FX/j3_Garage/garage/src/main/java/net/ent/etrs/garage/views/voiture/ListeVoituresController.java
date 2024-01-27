package net.ent.etrs.garage.views.voiture;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.ent.etrs.garage.model.entities.Marque;
import net.ent.etrs.garage.model.entities.Voiture;
import net.ent.etrs.garage.model.facade.IFacadeMetierVoiture;
import net.ent.etrs.garage.model.facade.impl.FacadeMetierFactory;
import net.ent.etrs.garage.start.Lanceur;
import net.ent.etrs.garage.views.utils.AlerteUtils;

import java.io.IOException;
import java.time.LocalDate;

public class ListeVoituresController {
    @FXML
    public TableView<Voiture> tblVoitures;

    @FXML
    public TableColumn<Voiture, String> tbcImmatriculation;

    @FXML
    public TableColumn<Voiture, String> tbcModele;

    @FXML
    public TableColumn<Voiture, Integer> tbcPuissance;

    @FXML
    public TableColumn<Voiture, LocalDate> tbcMiseEnCirculation;

    @FXML
    public TableColumn<Voiture, String> tbcMarque;

    @FXML
    public TextField txtRechercheVoiture;

    private ObservableList<Voiture> voitureObservableList = FXCollections.observableArrayList();

    private IFacadeMetierVoiture facadeMetierVoiture;

    public ListeVoituresController() {
        this.facadeMetierVoiture = FacadeMetierFactory.fabriquerFacadeMetierVoiture();
    }

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

    @FXML
    public void goToFicheVoiture() {
    }

    private void ajouterContextMenu() {
        this.tblVoitures.setRowFactory(p -> {
            TableRow<Voiture> row = new TableRow<>();
            row.emptyProperty().addListener((obs, wasEmpty, isEmpty) -> {
                if (isEmpty) {
                    row.setContextMenu(null);
                } else {
                    row.setContextMenu(creerContextMenu());
                }
            });
            return row;
        });
    }

    private ContextMenu creerContextMenu() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem menuItemModifier = new MenuItem("Modifier");
        MenuItem menuItemSupprimer = new MenuItem("Supprimer");

        menuItemModifier.setOnAction(e -> modifier());
        menuItemSupprimer.setOnAction(e -> supprimer());

        contextMenu.getItems().add(menuItemModifier);
        contextMenu.getItems().add(menuItemSupprimer);

        return contextMenu;
    }

    private void supprimer() {
    }

    private void modifier() {
    }
}
