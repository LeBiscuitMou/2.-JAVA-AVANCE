package net.ent.etrs.projet.views.controllers.impl;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import net.ent.etrs.projet.models.commons.CoUtils;
import net.ent.etrs.projet.models.entities.Soigneur;
import net.ent.etrs.projet.models.entities.references.Type;
import net.ent.etrs.projet.models.exceptions.BusinessException;
import net.ent.etrs.projet.models.facades.impl.FacadeMetierFactory;
import net.ent.etrs.projet.models.facades.interfaces.GenericFacade;
import net.ent.etrs.projet.models.facades.interfaces.IFacadeSoigneur;
import net.ent.etrs.projet.start.Lanceur;
import net.ent.etrs.projet.views.commons.javaFX.ContextMenuCreator;
import net.ent.etrs.projet.views.controllers.interfaces.ListDisplayerController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.function.Function;

public class ListSoigneurController implements ListDisplayerController<Soigneur, FicheSoigneurController> {
    protected final IFacadeSoigneur fMetSoigneur = FacadeMetierFactory.fabriquerFacadeSoigneur();
    protected final ObservableList<Soigneur> observableList = FXCollections.observableArrayList();

    public TableView<Soigneur> tvSoigneur;
    public TableColumn<Soigneur, String> tcNom;
    public TableColumn<Soigneur, String> tcPrenom;
    public TableColumn<Soigneur, LocalDate> tcDdN;
    public TableColumn<Soigneur, LocalDate> tcDA;
    public TableColumn<Soigneur, String> tcType;

    @FXML
    public void initialize(){
        initTable();
        tcNom.setCellValueFactory((p) -> new SimpleStringProperty(p.getValue().getNom()));
        tcPrenom.setCellValueFactory((p) -> new SimpleStringProperty(p.getValue().getPrenom()));
        tcDdN.setCellValueFactory((p) -> new SimpleObjectProperty<>(p.getValue().getDateDeNaissance()));
        tcDA.setCellValueFactory((p) -> new SimpleObjectProperty<>(p.getValue().getDateArrivee()));

        tcType.setCellValueFactory((p) -> {
            StringBuilder text = new StringBuilder();
            for(Type t : p.getValue().getExpertise()){
                text.append(t.toString()).append(' ');
            }
            return new SimpleStringProperty(text.toString());
        });

        ContextMenuCreator.ajouterContextMenu(this);

    }

    @Override
    public GenericFacade<Soigneur> getFMet() {
        return this.fMetSoigneur;
    }

    @Override
    public String getClassName() {
        return Soigneur.class.getSimpleName();
    }

    @Override
    public Function<Soigneur, FicheSoigneurController> getCaller() {
        return new FicheSoigneurController();
    }

    @Override
    public TableView<Soigneur> getTableView() {
        return this.tvSoigneur;
    }

    private void initTable() {
        observableList.clear();
        try {
            observableList.addAll(CoUtils.iterableToList(fMetSoigneur.findAll()));
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }
        tvSoigneur.setItems(observableList);
    }

    @FXML
    public void saisir() {
        try {
            Lanceur.loadFXML("ficheSoigneur", new FicheSoigneurController());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void goToMainMenu() {
        try {
            Lanceur.loadFXML("menuPrincipal");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
