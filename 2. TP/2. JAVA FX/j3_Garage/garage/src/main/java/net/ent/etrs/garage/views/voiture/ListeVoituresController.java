package net.ent.etrs.garage.views.voiture;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.ent.etrs.garage.model.entities.Marque;
import net.ent.etrs.garage.model.entities.Voiture;
import net.ent.etrs.garage.model.entities.references.ConstantesMetier;
import net.ent.etrs.garage.model.facade.IFacadeMetierVoiture;
import net.ent.etrs.garage.model.facade.exceptions.BusinessException;
import net.ent.etrs.garage.model.facade.impl.FacadeMetierFactory;
import net.ent.etrs.garage.start.Lanceur;
import net.ent.etrs.garage.views.utils.AlerteUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

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
        try {
            FilteredList<Voiture> voitureFilteredList = new FilteredList<>(voitureObservableList, v -> true);

            this.txtRechercheVoiture.textProperty().addListener(obs -> {
                voitureFilteredList.setPredicate(v -> v.getImmatriculation().toLowerCase().startsWith(txtRechercheVoiture.getText().toLowerCase()) ||
                                                        v.getModele().toLowerCase().startsWith(txtRechercheVoiture.getText().toLowerCase()));
            });

            voitureObservableList.clear();
            voitureObservableList.addAll(facadeMetierVoiture.recupererToutesLesVoitures());
            tblVoitures.setItems(voitureFilteredList);

            tbcImmatriculation.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getImmatriculation()));
            tbcModele.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getModele()));
            tbcMarque.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getMarque().getLibelle()));
            tbcPuissance.setCellValueFactory(v -> new SimpleObjectProperty<>(v.getValue().getPuissance()));
            tbcMiseEnCirculation.setCellValueFactory(v -> new SimpleObjectProperty<>(v.getValue().getMiseEnCirculation()));

            this.ajouterContextMenu();
        } catch (BusinessException e) {
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
    public void goToFicheVoiture() {
        try {
            Lanceur.loadFxml("voiture/ficheVoiture", new FicheVoitureController());
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
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

        MenuItem menuItemDetails = new MenuItem("Détails");
        MenuItem menuItemModifier = new MenuItem("Modifier");
        MenuItem menuItemSupprimer = new MenuItem("Supprimer");

        menuItemDetails.setOnAction(e -> afficherDetails());
        menuItemModifier.setOnAction(e -> modifier());
        menuItemSupprimer.setOnAction(e -> supprimer());

        contextMenu.getItems().add(menuItemDetails);
        contextMenu.getItems().add(menuItemModifier);
        contextMenu.getItems().add(menuItemSupprimer);

        return contextMenu;
    }

    private void afficherDetails() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Immatriculation : " + tblVoitures.getSelectionModel().getSelectedItem().getImmatriculation() + "\n" +
                                "Modèle : " + tblVoitures.getSelectionModel().getSelectedItem().getModele() + "\n" +
                                "Puissance : " + tblVoitures.getSelectionModel().getSelectedItem().getPuissance() + "\n" +
                                "Date de mise en circulation : " + tblVoitures.getSelectionModel().getSelectedItem().getMiseEnCirculation());
        alert.setHeaderText("Détails pour : " + tblVoitures.getSelectionModel().getSelectedItem().getModele());
        alert.showAndWait().ifPresent(rs -> {
            if (rs.equals(ButtonType.OK)) {
                System.out.println("Pressed OK.");
            }
        });
    }

    private void supprimer() {
        try {
            Voiture voitureToDelete = tblVoitures.getSelectionModel().getSelectedItem();
            if (AlerteUtils.afficherMessageDansAlerte("Êtes-vous sûr ?", Alert.AlertType.CONFIRMATION)) {
                this.facadeMetierVoiture.supprimerVoiture(voitureToDelete);
                initialize();
            }
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void modifier() {
        try {
            Voiture voitureToEdit = tblVoitures.getSelectionModel().getSelectedItem();
            if (Objects.nonNull(voitureToEdit)) {
                Lanceur.loadFxml("voiture/ficheVoiture", new FicheVoitureController(voitureToEdit));
            }
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
