package net.ent.etrs.firstjavafx.views;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.Getter;
import net.ent.etrs.firstjavafx.model.entities.Personne;
import net.ent.etrs.firstjavafx.start.Lanceur;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ListeController {

    @Getter
    private static Set<Personne> personneSet = new HashSet<>();

    @FXML
    public TableView<Personne> tvPersonne;

    @FXML
    public TableColumn<Personne, String> tcNom;

    @FXML
    public TableColumn<Personne, String> tcPrenom;

    @FXML
    public TableColumn<Personne, LocalDate> tcDdn;

    private ObservableList<Personne> observableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        observableList.clear();
        observableList.addAll(personneSet);
        tvPersonne.setItems(observableList);
        tcNom.setCellValueFactory((p) -> new SimpleStringProperty(p.getValue().getNom()));
        tcPrenom.setCellValueFactory((p) -> new SimpleStringProperty(p.getValue().getPrenom()));
        tcDdn.setCellValueFactory((p) -> new SimpleObjectProperty<>(p.getValue().getDdn()));

        this.ajouterContextMenu();
    }


    @FXML
    public void goToFiche() {
        try {
            Lanceur.loadFxml("fiche", new FicheController());
        } catch (Exception e) {
            //TODO
        }
    }

    @FXML
    public void delete() {
        try {
            Personne personneToDelete = tvPersonne.getSelectionModel().getSelectedItem();
            if (!Objects.isNull(personneToDelete)) {
                personneSet.remove(personneToDelete);
                initialize();
            }
        } catch (Exception e) {
            //TODO
        }
    }

    @FXML
    public void modifier() {
        try {
            Personne personneToEdit = tvPersonne.getSelectionModel().getSelectedItem();
            if (!Objects.isNull(personneToEdit)) {
                Lanceur.loadFxml("fiche", new FicheController(personneToEdit));
            }
        } catch (Exception e) {
            //TODO
        }
    }

    private void ajouterContextMenu() {
        this.tvPersonne.setRowFactory( p -> {
            TableRow<Personne> row = new TableRow<>();
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

        menuItemModifier.setOnAction(e -> goToFiche());
        menuItemSupprimer.setOnAction(e -> delete());

        contextMenu.getItems().add(menuItemModifier);
        contextMenu.getItems().add(menuItemSupprimer);

        return  contextMenu;
    }
}
