package net.ent.etrs.garage.views.marque;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.ent.etrs.garage.model.entities.Marque;
import net.ent.etrs.garage.model.entities.references.Nationalite;
import net.ent.etrs.garage.model.facade.IFacadeMetierMarque;
import net.ent.etrs.garage.model.facade.impl.FacadeMetierFactory;
import net.ent.etrs.garage.start.Lanceur;
import net.ent.etrs.garage.views.utils.AlerteUtils;

import java.io.IOException;

public class ListeMarquesController {
    @FXML
    public TableView<Marque> tblMarques;

    @FXML
    public TableColumn<Marque, String> tbcLibelle;

    @FXML
    public TableColumn<Marque, String> tbcChemin;

    @FXML
    public TableColumn<Marque, Nationalite> tbcNationalite;

    @FXML
    public TextField txtRechercheMarque;

    private ObservableList<Marque> marqueObservableList = FXCollections.observableArrayList();

    private IFacadeMetierMarque facadeMetierMarque;

    public ListeMarquesController() {
        this.facadeMetierMarque = FacadeMetierFactory.fabriquerFacadeMetierMarque();
    }

    @FXML
    public void initialize() {
        try {
            this.ajouterContextMenu();
        }
    }

    private void ajouterContextMenu() {
        this.tblMarques.setRowFactory(p -> {
            TableRow<Marque> row = new TableRow<>();
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
    }

    private void supprimer() {
        this.facadeMetierMarque.supprimerMarque();
    }

    private void modifier() {
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
    public void goToFicheMarque() {
    }
}