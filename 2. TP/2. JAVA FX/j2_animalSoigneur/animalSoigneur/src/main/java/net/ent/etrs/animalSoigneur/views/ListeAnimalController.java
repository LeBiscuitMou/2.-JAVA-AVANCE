package net.ent.etrs.animalSoigneur.views;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.Getter;
import net.ent.etrs.animalSoigneur.model.entities.Animal;
import net.ent.etrs.animalSoigneur.model.entities.exceptions.EntitiesFactoryException;
import net.ent.etrs.animalSoigneur.start.Lanceur;
import net.ent.etrs.animalSoigneur.views.exceptions.ListeAnimalControllerException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ListeAnimalController {
    private static List<Animal> animals = new ArrayList<>();

    private ObservableList<Animal> observableList = FXCollections.observableArrayList();

    @FXML
    public TableView<Animal> tblAnimals;

    @FXML
    public TableColumn<Animal, String> tblColNom;

    @FXML
    public TableColumn<Animal, Integer> tblColPoids;

    @FXML
    public TableColumn<Animal, Float> tblColTaille;

    @FXML
    public TableColumn<Animal, Boolean> tblColDangereux;

    @FXML
    public TableColumn<Animal, LocalDate> tblColDateNaissance;

    public static List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }

    @FXML
    public void initialize() {
        observableList.clear();
        observableList.addAll(animals);
        tblAnimals.setItems(observableList);

        tblColNom.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getNom()));
        tblColPoids.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getPoids()));
        tblColTaille.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getTaille()));
        tblColDangereux.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getDangerous()));
        tblColDateNaissance.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getDateNaissance()));

        this.ajouterContextMenu();
    }

    private void ajouterContextMenu() {
        tblAnimals.setRowFactory(p -> {
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
                afficherFicheCreationEtModificationAnimal();
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
    public void afficherFicheCreationEtModificationAnimal() throws ListeAnimalControllerException {
        try {
            Lanceur.loadFXML("ficheCreationAnimale", new CreationAnimalController());
        } catch (IOException e) {
            throw new ListeAnimalControllerException(e.getMessage(), e);
        }
    }

    @FXML
    public void supprimerAnimal() {
        Animal animalDelete = tblAnimals.getSelectionModel().getSelectedItem();
        if (Objects.nonNull(animalDelete)) {
            animals.remove(animalDelete);
            initialize();
        }
    }

    public void afficherFicheModificationAnimal() throws ListeAnimalControllerException {
        try {
            Animal animalEdit = tblAnimals.getSelectionModel().getSelectedItem();
            if (Objects.nonNull(animalEdit)) {
                Lanceur.loadFXML("ficheCreationAnimale", new CreationAnimalController(animalEdit));
            }
        } catch (IOException | EntitiesFactoryException e) {
            throw new ListeAnimalControllerException(e.getMessage(), e);
        }
    }
}