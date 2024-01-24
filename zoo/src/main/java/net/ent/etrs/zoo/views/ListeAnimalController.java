package net.ent.etrs.zoo.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.Getter;
import net.ent.etrs.zoo.models.Animal;
import net.ent.etrs.zoo.models.Type;
import net.ent.etrs.zoo.start.Lanceur;
import net.ent.etrs.zoo.views.exceptions.ListeAnimalControllerException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
            throw new ListeAnimalControllerException(e);
        }
    }

    @FXML
    public void initialize() {

    }


}