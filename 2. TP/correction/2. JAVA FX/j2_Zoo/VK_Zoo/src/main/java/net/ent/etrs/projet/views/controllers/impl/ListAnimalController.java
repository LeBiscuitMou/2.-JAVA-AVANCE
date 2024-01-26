package net.ent.etrs.projet.views.controllers.impl;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.ent.etrs.projet.models.commons.CoUtils;
import net.ent.etrs.projet.models.entities.Animal;
import net.ent.etrs.projet.models.entities.references.Type;
import net.ent.etrs.projet.models.exceptions.BusinessException;
import net.ent.etrs.projet.models.facades.impl.FacadeMetierFactory;
import net.ent.etrs.projet.models.facades.interfaces.GenericFacade;
import net.ent.etrs.projet.models.facades.interfaces.IFacadeAnimal;
import net.ent.etrs.projet.start.Lanceur;
import net.ent.etrs.projet.views.commons.javaFX.ContextMenuCreator;
import net.ent.etrs.projet.views.controllers.impl.FicheAnimalController;
import net.ent.etrs.projet.views.controllers.interfaces.ListDisplayerController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.function.Function;

public class ListAnimalController implements ListDisplayerController<Animal, FicheAnimalController> {
    private final ObservableList<Animal> observableList = FXCollections.observableArrayList();

    private final IFacadeAnimal fMetAnimal = FacadeMetierFactory.fabriquerFacadeAnimal();

    @FXML
    public TableView<Animal> tvAnimal;
    @FXML
    public TableColumn<Animal, String> tcNom;
    @FXML
    public TableColumn<Animal, Double> tcPoids;
    @FXML
    public TableColumn<Animal, Double> tcTaille;
    @FXML
    public TableColumn<Animal, Type> tcType;
    @FXML
    public TableColumn<Animal, Boolean> tcDangerosite;
    @FXML
    public TableColumn<Animal, LocalDate> tcDdN;

    @FXML
    public void initialize(){
        observableList.clear();
        try {
            observableList.addAll(CoUtils.iterableToList(fMetAnimal.findAll()));
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }
        tvAnimal.setItems(observableList);
        tcNom.setCellValueFactory((p) -> new SimpleStringProperty(p.getValue().getNom()));
        tcPoids.setCellValueFactory((p) -> new SimpleObjectProperty<>(p.getValue().getPoids()));
        tcTaille.setCellValueFactory((p) -> new SimpleObjectProperty<>(p.getValue().getTaille()));
        tcType.setCellValueFactory((p) -> new SimpleObjectProperty<>(p.getValue().getType()));
        tcDangerosite.setCellValueFactory((p) -> new SimpleBooleanProperty(p.getValue().isDangereux()));
        tcDdN.setCellValueFactory((p) -> new SimpleObjectProperty<>(p.getValue().getDateDeNaissance()));

        ContextMenuCreator.ajouterContextMenu(this);

    }

    @Override
    public GenericFacade<Animal> getFMet() {
        return fMetAnimal;
    }

    @Override
    public String getClassName() {
        return Animal.class.getSimpleName();
    }

    @Override
    public Function<Animal, FicheAnimalController> getCaller() {
        return new FicheAnimalController();
    }

    @Override
    public TableView<Animal> getTableView() {
        return tvAnimal;
    }

    public void creerAnimal() throws IOException {
        Lanceur.loadFXML("ficheAnimal", new FicheAnimalController());
    }

    public void goToMainMenu() {
        try {
            Lanceur.loadFXML("menuPrincipal");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
