package net.ent.etrs.garage.views.marque;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.ent.etrs.garage.model.entities.Marque;
import net.ent.etrs.garage.model.entities.references.Nationalite;
import net.ent.etrs.garage.model.facade.IFacadeMetierMarque;
import net.ent.etrs.garage.model.facade.exceptions.BusinessException;
import net.ent.etrs.garage.model.facade.impl.FacadeMetierFactory;
import net.ent.etrs.garage.start.Lanceur;
import net.ent.etrs.garage.views.utils.AlerteUtils;

import java.io.IOException;
import java.util.Objects;

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
            FilteredList<Marque> marqueFilteredList = new FilteredList<>(marqueObservableList, m -> true);

            this.txtRechercheMarque.textProperty().addListener(obs -> {
                marqueFilteredList.setPredicate(m -> m.getLibelle().toLowerCase().startsWith(txtRechercheMarque.getText().toLowerCase()));
            });

            marqueObservableList.clear();
            marqueObservableList.addAll(facadeMetierMarque.recupererToutesLesMarques());
            tblMarques.setItems(marqueFilteredList);

            tbcLibelle.setCellValueFactory(m -> new SimpleStringProperty(m.getValue().getLibelle()));
            tbcChemin.setCellValueFactory(m -> new SimpleStringProperty(m.getValue().getCheminComplet()));
            tbcNationalite.setCellValueFactory(m -> new SimpleObjectProperty<>(m.getValue().getNationalite()));

            this.ajouterContextMenu();

        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
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

        return contextMenu;
    }

    private void supprimer() {
        try {
            Marque marqueToDelete = tblMarques.getSelectionModel().getSelectedItem();
            if (AlerteUtils.afficherMessageDansAlerte("Etes vous sure ?", Alert.AlertType.CONFIRMATION)) {
                this.facadeMetierMarque.supprimerMarque(marqueToDelete);
                initialize();
            }
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void modifier() {
        try {
            Marque marqueToEdit = tblMarques.getSelectionModel().getSelectedItem();
            if (Objects.nonNull(marqueToEdit)) {
                Lanceur.loadFxml("marque/ficheMarque", new FicheMarqueController(marqueToEdit));
            }
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
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
        try {
            Lanceur.loadFxml("marque/ficheMarque", new FicheMarqueController());
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}