package net.ent.etrs.firstjavafx.views;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.Getter;
import net.ent.etrs.firstjavafx.model.entities.Personne;
import net.ent.etrs.firstjavafx.start.Lanceur;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
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
        observableList.addAll(personneSet);
        tvPersonne.setItems(observableList);
        tcNom.setCellValueFactory((p) -> new SimpleStringProperty(p.getValue().getNom()));
        tcPrenom.setCellValueFactory((p) -> new SimpleStringProperty(p.getValue().getPrenom()));
        tcDdn.setCellValueFactory((p) -> new SimpleObjectProperty<>(p.getValue().getDdn()));
    }


    @FXML
    public void goToFiche() throws IOException {
        Lanceur.loadFxml("fiche");
    }
}
