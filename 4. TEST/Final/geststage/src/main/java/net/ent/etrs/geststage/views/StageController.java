package net.ent.etrs.geststage.views;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.NoArgsConstructor;
import net.ent.etrs.geststage.model.entities.Stage;
import net.ent.etrs.geststage.model.entities.Stagiaire;
import net.ent.etrs.geststage.model.entities.exceptions.StagiaireException;
import net.ent.etrs.geststage.model.entities.references.Matiere;
import net.ent.etrs.geststage.model.facade.IFacadeMetierStage;
import net.ent.etrs.geststage.model.facade.IFacadeMetierStagiaire;
import net.ent.etrs.geststage.model.facade.exceptions.BusinessException;
import net.ent.etrs.geststage.model.facade.impl.FacadeMetierFactory;
import net.ent.etrs.geststage.start.Lanceur;
import net.ent.etrs.geststage.views.utils.AlerteUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

public class StageController {
    @FXML
    public Label lblTitre;

    @FXML
    public ComboBox<Matiere> cmbMatiere;

    @FXML
    public TextField txtNote;

    @FXML
    public Button btnAjouterNoteAuStagiaire;

    @FXML
    public TableView<Stagiaire> tblStagiaire;

    @FXML
    public TableColumn<Stagiaire, String> tbcNom;

    @FXML
    public TableColumn<Stagiaire, String> tbcPrenom;

    @FXML
    public TableColumn<Stagiaire, LocalDate> tbcDateDeNaissance;

    @FXML
    public TableColumn<Stagiaire, Float> tbcMoyenne;

    @FXML
    public TextField txtRechercheStagiaire;

    private Stage stage = new Stage();

    private ObservableList<Stagiaire> stagiaireObservableList = FXCollections.observableArrayList();

    private IFacadeMetierStage facadeMetierStage = FacadeMetierFactory.fabriquerFacadeMetierStage();

    private IFacadeMetierStagiaire facadeMetierStagiaire = FacadeMetierFactory.fabriquerFacadeMetierStagiaire();

    public StageController(Stage stageToAfficher) {
        this.stage = stageToAfficher;
    }

    @FXML
    public void initialize() {
        lblTitre.setText(stage.getCode() + " - Du " + stage.getDateDebut() + " au " + stage.getDateFin());

        FilteredList<Stagiaire> stagiaireFilteredList = new FilteredList<>(stagiaireObservableList, stagiaire -> true);

        this.txtRechercheStagiaire.textProperty().addListener(obs -> {
            stagiaireFilteredList.setPredicate(stagiaire -> stagiaire.getNom().toLowerCase().startsWith(txtRechercheStagiaire.getText().toLowerCase()) ||
                    stagiaire.getPrenom().toLowerCase().startsWith(txtRechercheStagiaire.getText().toLowerCase()));
        });

        stagiaireObservableList.clear();
        stagiaireObservableList.addAll(stage.getStagiaires());
        tblStagiaire.setItems(stagiaireFilteredList);

        tbcNom.setCellValueFactory(s -> new SimpleStringProperty(s.getValue().getNom()));
        tbcPrenom.setCellValueFactory(s -> new SimpleStringProperty(s.getValue().getPrenom()));
        tbcDateDeNaissance.setCellValueFactory(s -> new SimpleObjectProperty<>(s.getValue().getDateNaissance()));

        for(Stagiaire stagiaire : stagiaireObservableList){
            Float moyenne = 0.0f;
            int i = 0;
            for(Map.Entry<Matiere, Float> notesEntry : stagiaire.getNotes().entrySet()){
                moyenne += notesEntry.getValue();
                i++;
            }
            moyenne /= i;
            stagiaire.setMoyenne(moyenne);
        }

        tbcMoyenne.setCellValueFactory(s -> new SimpleObjectProperty<>(s.getValue().getMoyenne()));

        ObservableList<Matiere> matiereObservableList = FXCollections.observableArrayList();
        matiereObservableList.addAll(Matiere.values());
        cmbMatiere.setItems(matiereObservableList);

        this.ajouterContextMenu();
    }

    private void ajouterContextMenu() {
        this.tblStagiaire.setRowFactory(p -> {
            TableRow<Stagiaire> row = new TableRow<>();
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

        MenuItem menuItemAfficherNotes = new MenuItem("Afficher notes");

        menuItemAfficherNotes.setOnAction(e -> afficherNotes());

        contextMenu.getItems().add(menuItemAfficherNotes);

        return contextMenu;
    }

    private void afficherNotes() {
        Stagiaire stagiaireSelected = tblStagiaire.getSelectionModel().getSelectedItem();
        StringBuilder message = new StringBuilder("Notes de " + stagiaireSelected.getNom() + " " + stagiaireSelected.getPrenom() + "\r\n" +
                "-".repeat(50) + "\r\n");
        for(Map.Entry<Matiere, Float> notesEntry : stagiaireSelected.getNotes().entrySet()){
            message.append(notesEntry.getKey()).append(" : ").append(notesEntry.getValue()).append("\r\n");
        }
        AlerteUtils.afficherMessageDansAlerte(String.valueOf(message), Alert.AlertType.INFORMATION);
    }

    @FXML
    public void ajouterNoteAuStagiaire() {
        try {
            if (null == cmbMatiere.getValue()
                    || null == txtNote.getText() || txtNote.getText().isBlank()
                    || null == tblStagiaire.getSelectionModel().getSelectedItem()) {
                AlerteUtils.afficherMessageDansAlerte("Attention !!!", "Il faut saisir toutes les informations !", Alert.AlertType.WARNING);
            } else if (stage.getDateDebut().isAfter(LocalDate.now())) {
                AlerteUtils.afficherMessageDansAlerte("Attention !!!", "Impossible d'ajouter une note à un stage qui n'a pas encore commencé", Alert.AlertType.WARNING);
            } else if (stage.getDateFin().isBefore(LocalDate.now())) {
                AlerteUtils.afficherMessageDansAlerte("Attention !!!", "Impossible d'ajouter une note à un stage terminé", Alert.AlertType.WARNING);
            } else {
                Stagiaire stagiaireSelected = tblStagiaire.getSelectionModel().getSelectedItem();
                stagiaireSelected.ajouterNoteAMatiere(cmbMatiere.getValue(), Float.parseFloat(txtNote.getText()));
                initialize();
            }
        } catch (StagiaireException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void goToFicheAccueil() {
        try {
            Lanceur.loadFxml("accueil");
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
