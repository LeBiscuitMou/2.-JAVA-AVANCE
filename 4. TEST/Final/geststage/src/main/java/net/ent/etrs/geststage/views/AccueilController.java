package net.ent.etrs.geststage.views;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import net.ent.etrs.geststage.model.entities.Stage;
import net.ent.etrs.geststage.model.entities.Stagiaire;
import net.ent.etrs.geststage.model.entities.exceptions.StagiaireException;
import net.ent.etrs.geststage.model.entities.references.ConstantesMetier;
import net.ent.etrs.geststage.model.facade.IFacadeMetierStage;
import net.ent.etrs.geststage.model.facade.IFacadeMetierStagiaire;
import net.ent.etrs.geststage.model.facade.exceptions.BusinessException;
import net.ent.etrs.geststage.model.facade.impl.FacadeMetierFactory;
import net.ent.etrs.geststage.start.Lanceur;
import net.ent.etrs.geststage.views.utils.AlerteUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AccueilController {
    @FXML
    public Button btnParcourir;

    @FXML
    public TextField txtCode;

    @FXML
    public DatePicker dtpDebut;

    @FXML
    public DatePicker dtpFin;

    @FXML
    public Button btnImporter;

    @FXML
    public TableView<Stage> tblStage;

    @FXML
    public TableColumn<Stage, String> tbcCode;

    @FXML
    public TableColumn<Stage, LocalDate> tbcDateDebut;

    @FXML
    public TableColumn<Stage, LocalDate> tbcDateFin;

    @FXML
    public TableColumn<Stage, Integer> tbcNbStagiaire;

    @FXML
    public TextField txtRechercheCodeStage;

    @FXML
    public Label lblCheminFichier;

    private File selectedFile;

    private Stage stage = new Stage();

    private IFacadeMetierStage facadeMetierStage;

    private IFacadeMetierStagiaire facadeMetierStagiaire;

    private ObservableList<Stage> stageObservableList = FXCollections.observableArrayList();

    public AccueilController() {
        facadeMetierStage = FacadeMetierFactory.fabriquerFacadeMetierStage();
        facadeMetierStagiaire = FacadeMetierFactory.fabriquerFacadeMetierStagiaire();
    }

    @FXML
    public void initialize() {
        try {
            FilteredList<Stage> stageFilteredList = new FilteredList<>(stageObservableList, stage -> true);

            this.txtRechercheCodeStage.textProperty().addListener(obs -> {
                stageFilteredList.setPredicate(stage -> stage.getCode().toLowerCase().startsWith(txtRechercheCodeStage.getText().toLowerCase()));
            });

            stageObservableList.clear();
            stageObservableList.addAll(facadeMetierStage.recupererTousLesStages());
            tblStage.setItems(stageFilteredList);

            tbcCode.setCellValueFactory(stage -> new SimpleStringProperty(stage.getValue().getCode()));
            tbcDateDebut.setCellValueFactory(stage -> new SimpleObjectProperty<>(stage.getValue().getDateDebut()));
            tbcDateFin.setCellValueFactory(stage -> new SimpleObjectProperty<>(stage.getValue().getDateFin()));
            tbcNbStagiaire.setCellValueFactory(stage -> new SimpleObjectProperty<>(stage.getValue().getStagiaires().size()));

            this.ajouterContextMenu();
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void ajouterContextMenu() {
        this.tblStage.setRowFactory(p -> {
            TableRow<Stage> row = new TableRow<>();
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

        MenuItem menuItemAfficherStage = new MenuItem("Afficher Stage");
        MenuItem menuItemSupprimerStage = new MenuItem("Supprimer Stage");

        menuItemAfficherStage.setOnAction(e -> afficherStage());
        menuItemSupprimerStage.setOnAction(e -> supprimerStage());

        contextMenu.getItems().add(menuItemAfficherStage);
        contextMenu.getItems().add(menuItemSupprimerStage);

        return contextMenu;
    }

    private void supprimerStage() {
        try {
            Stage stageToDelete = tblStage.getSelectionModel().getSelectedItem();
            if (AlerteUtils.afficherMessageDansAlerte("Supprimer", "Êtes-vous sûr de vouloir supprimer ce stage ?", Alert.AlertType.CONFIRMATION)) {
                this.facadeMetierStage.supprimerStage(stageToDelete);
                AlerteUtils.afficherMessageDansAlerte("Victoire !", "Bravo, vous avez réussi à supprimer ce stage ! Et en plus vous êtes le plus beau !", Alert.AlertType.INFORMATION);
                initialize();
            }
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void afficherStage() {
        try {
            Stage stageToAfficher = tblStage.getSelectionModel().getSelectedItem();
            if (Objects.nonNull(stageToAfficher)) {
                Lanceur.loadFxml("stage", new StageController(stageToAfficher));
            }
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void parcourir() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner un fichier csv");

        FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("Fichier CSV", "*.csv");
        fileChooser.getExtensionFilters().add(csvFilter);

        this.selectedFile = fileChooser.showOpenDialog(Lanceur.getScene().getWindow());

        if (Objects.nonNull(selectedFile)) {
            lblCheminFichier.setText(selectedFile.getAbsolutePath());
        }
    }

    @FXML
    public void creerStage() {
        try {
            if (null == txtCode.getText() || txtCode.getText().isBlank()
                    || null == dtpDebut.getValue()
                    || null == dtpFin.getValue()
                    || null == lblCheminFichier.getText() || lblCheminFichier.getText().equals("chemin du fichier")) {
                AlerteUtils.afficherMessageDansAlerte("Attention !!!", "Il faut saisir toutes les informations !", Alert.AlertType.WARNING);
            } else if (!txtCode.getText().matches("[A-Z]{2}\\d{2}"))  {
                AlerteUtils.afficherMessageDansAlerte("Attention !!!", "Il faut saisir correctement le code de stage !\nExemple : AA99", Alert.AlertType.WARNING);
            } else if (dtpFin.getValue().isBefore(dtpDebut.getValue())) {
                AlerteUtils.afficherMessageDansAlerte("Attention !!!", "La date de fin doit être après la date de début !", Alert.AlertType.WARNING);
            } else {
                stage.setCode(txtCode.getText());
                stage.setDateDebut(dtpDebut.getValue());
                stage.setDateFin(dtpFin.getValue());

                for (String line : Files.readAllLines(selectedFile.toPath())) {
                    String[] data = line.split(";");
                    Stagiaire stagiaire = new Stagiaire();

                    stagiaire.setNom(data[0]);
                    stagiaire.setPrenom(data[1]);
                    stagiaire.setDateNaissance(LocalDate.parse(data[2].trim(), ConstantesMetier.DATE_FORMAT));

                    facadeMetierStagiaire.creerStagiaire(stagiaire);

                    stage.ajouterStagiaire(stagiaire);
                }

                facadeMetierStage.creerStage(stage);
                initialize();
            }
        } catch (IOException | StagiaireException | BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
