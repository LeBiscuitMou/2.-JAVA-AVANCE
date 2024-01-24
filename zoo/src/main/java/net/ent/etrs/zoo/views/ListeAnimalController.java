package net.ent.etrs.zoo.views;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.Getter;
import net.ent.etrs.zoo.models.entities.Animal;
import net.ent.etrs.zoo.models.references.Type;
import net.ent.etrs.zoo.start.Lanceur;
import net.ent.etrs.zoo.views.exceptions.ListeAnimalControllerException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListeAnimalController {
    @Getter
    private static List<Animal> animals = new ArrayList<>();

    @FXML
    public TableView<Animal> tblAnimal;

    @FXML
    public TableColumn<Animal, String> tbcNom;

    @FXML
    public TableColumn<Animal, Integer> tbcPoids;

    @FXML
    public TableColumn<Animal, Float> tbcTaille;

    @FXML
    public TableColumn<Animal, Boolean> tbcDangereux;

    @FXML
    public TableColumn<Animal, LocalDate> tbcDateNaissance;

    @FXML
    public TableColumn<Animal, Type> tbcType;

    private ObservableList<Animal> observableList = FXCollections.observableArrayList();

    @FXML
    public void goToFicheAnimal() throws ListeAnimalControllerException {
        try {
            Lanceur.loadFXML("ficheAnimal", new FicheAnimalController());
        } catch (IOException e) {
            throw new ListeAnimalControllerException(e.getMessage(), e);
        }
    }

    @FXML
    public void initialize() {
        observableList.clear();
        observableList.addAll(animals);
        tblAnimal.setItems(observableList);

        tbcNom.setCellValueFactory((p) -> new SimpleStringProperty(p.getValue().getNom()));
        tbcPoids.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getPoids()));
        tbcTaille.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getTaille()));
        tbcDangereux.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getDangerous()));
        tbcDateNaissance.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getDateNaissance()));
        tbcType.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getType()));

        this.ajouterContextMenu();
    }

    private void ajouterContextMenu() {
        tblAnimal.setRowFactory(p -> {
            TableRow<Animal> row = new TableRow<>();
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

        menuItemModifier.setOnAction(e -> {
            try {
                goToFicheAnimal();
            } catch (ListeAnimalControllerException ex) {
                throw new RuntimeException(ex);
            }
        });
        menuItemSupprimer.setOnAction(e -> supprimerAnimal());

        contextMenu.getItems().add(menuItemModifier);
        contextMenu.getItems().add(menuItemSupprimer);

        return  contextMenu;
    }

    @FXML
    public void supprimerAnimal() {
        Animal animalToDelete = tblAnimal.getSelectionModel().getSelectedItem();
        if (Objects.nonNull(animalToDelete)) {
            animals.remove(animalToDelete);
            initialize();
        }
    }

    @FXML
    public void modifier() throws ListeAnimalControllerException {
        try {
            Animal animalToEdit = tblAnimal.getSelectionModel().getSelectedItem();
            if (Objects.nonNull(animalToEdit)) {
                Lanceur.loadFXML("ficheAnimal", new FicheAnimalController(animalToEdit));
            }
        } catch (IOException e) {
            throw new ListeAnimalControllerException(e.getMessage(), e);
        }
    }
}