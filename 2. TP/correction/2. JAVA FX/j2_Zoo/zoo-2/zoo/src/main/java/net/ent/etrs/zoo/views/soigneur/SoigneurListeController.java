package net.ent.etrs.zoo.views.soigneur;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.ent.etrs.zoo.models.entities.Animal;
import net.ent.etrs.zoo.models.entities.Soigneur;
import net.ent.etrs.zoo.models.facades.FacadeAnimal;
import net.ent.etrs.zoo.models.facades.FacadeSoigneur;
import net.ent.etrs.zoo.start.Lanceur;
import net.ent.etrs.zoo.views.animal.AnimalFicheController;
import net.ent.etrs.zoo.views.utils.AlerteUtils;

import java.time.LocalDate;
import java.util.Objects;

public class SoigneurListeController {

    @FXML
    public TableView<Soigneur> tvSoigneur;
    @FXML
    public TableColumn<Soigneur, String> tcNom;
    @FXML
    public TableColumn<Soigneur, String> tcPrenom;
    @FXML
    public TableColumn<Soigneur, LocalDate> tcDdn;
    @FXML
    public TableColumn<Soigneur, LocalDate> tcDa;

    private FacadeSoigneur facadeSoigneur;

    private ObservableList<Soigneur> soigneurObservableList = FXCollections.observableArrayList();

    public SoigneurListeController() {
        this.facadeSoigneur = new FacadeSoigneur();
    }

    @FXML
    public void initialize() {
        try {
            soigneurObservableList.clear();
            soigneurObservableList.addAll(this.facadeSoigneur.findAll());
            tvSoigneur.setItems(soigneurObservableList);

            tcNom.setCellValueFactory(s -> new SimpleStringProperty(s.getValue().getNom()));
            tcPrenom.setCellValueFactory(s -> new SimpleStringProperty(s.getValue().getPrenom()));
            tcDdn.setCellValueFactory(s -> new SimpleObjectProperty<>(s.getValue().getDateDeNaissance()));
            tcDa.setCellValueFactory(s -> new SimpleObjectProperty<>(s.getValue().getDateArrivee()));

            this.ajouterContextMenu();

        } catch (Exception e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void goToFiche() {
        try {
            Lanceur.loadFxml("soigneur/fiche", new SoigneurFicheController());
        } catch (Exception e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void edit() {
        try {
            Soigneur soigneurToEdit = tvSoigneur.getSelectionModel().getSelectedItem();
            if (!Objects.isNull(soigneurToEdit)) {
                Lanceur.loadFxml("soigneur/fiche", new SoigneurFicheController(soigneurToEdit));
            }
        } catch (Exception e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            Soigneur soigneurToDelete = tvSoigneur.getSelectionModel().getSelectedItem();
            if (!Objects.isNull(soigneurToDelete)) {
                if (AlerteUtils.afficherMessageDansAlerte("Etes vous sure ?", Alert.AlertType.CONFIRMATION)) {
                    this.facadeSoigneur.delete(soigneurToDelete);
                    initialize();
                }
            }
        } catch (Exception e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void goToAccueil() {
        try {
            Lanceur.loadFxml("accueil");
        } catch (Exception e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void ajouterContextMenu() {
        this.tvSoigneur.setRowFactory( p -> {
            TableRow<Soigneur> row = new TableRow<>();
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

        menuItemModifier.setOnAction(e -> edit());
        menuItemSupprimer.setOnAction(e -> delete());

        contextMenu.getItems().add(menuItemModifier);
        contextMenu.getItems().add(menuItemSupprimer);

        return  contextMenu;
    }
}
