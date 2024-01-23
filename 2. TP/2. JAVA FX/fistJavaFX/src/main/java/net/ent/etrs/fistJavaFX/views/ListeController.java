package net.ent.etrs.fistJavaFX.views;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import lombok.Getter;
import net.ent.etrs.fistJavaFX.models.Personne;
import net.ent.etrs.fistJavaFX.start.Lanceur;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ListeController {
    @Getter
    private static Set<Personne> personnes = new HashSet<>();
    @FXML
    public TableView<Personne> tblViewSuperListe;

    @FXML
    public TableColumn<Personne, String> tblColPrenom;

    @FXML
    public TableColumn<Personne, String> tblColNom;

    @FXML
    public TableColumn<Personne, LocalDate> tblColDateNaissance;

    private ObservableList<Personne> observableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        observableList.clear();
        observableList.addAll(personnes);
        tblViewSuperListe.setItems(observableList);
        tblColNom.setCellValueFactory((p) -> new SimpleStringProperty(p.getValue().getNom()));
        tblColPrenom.setCellValueFactory((p) -> new SimpleStringProperty(p.getValue().getPrenom()));
        tblColDateNaissance.setCellValueFactory((p) -> new SimpleObjectProperty<>(p.getValue().getDateDeNaissance()));
    }

    public void goToFiche() throws IOException {
        Lanceur.loadFXML("test", new TestController());
    }

    public void supprimerPersonne() {
        personnes.remove(tblViewSuperListe.getSelectionModel().getSelectedItem());
        initialize();
    }

    public void modifierPersonne() throws IOException {
        Personne personneEdit = tblViewSuperListe.getSelectionModel().getSelectedItem();
        if (Objects.nonNull(personneEdit)) {
            Lanceur.loadFXML("test", new TestController(personneEdit));
        }
    }
}