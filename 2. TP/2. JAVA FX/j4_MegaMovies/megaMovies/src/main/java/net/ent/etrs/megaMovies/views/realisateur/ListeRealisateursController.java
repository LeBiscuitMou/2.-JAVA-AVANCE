package net.ent.etrs.megaMovies.views.realisateur;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.NoArgsConstructor;
import net.ent.etrs.megaMovies.model.entities.Realisateur;
import net.ent.etrs.megaMovies.model.facade.IFacadeMetierRealisateur;
import net.ent.etrs.megaMovies.model.facade.exceptions.BusinessException;
import net.ent.etrs.megaMovies.model.facade.impl.FacadeMetierFactory;
import net.ent.etrs.megaMovies.start.Lanceur;
import net.ent.etrs.megaMovies.views.film.VoirFilmoController;
import net.ent.etrs.megaMovies.views.utils.AlerteUtils;

import java.io.IOException;
import java.util.Objects;

public class ListeRealisateursController {
    @FXML
    public TableView<Realisateur> tblRealisateur;

    @FXML
    public TableColumn<Realisateur, String> tbcNom;

    @FXML
    public TextField txtRechercherRealisateur;

    private ObservableList<Realisateur> realisateurObservableList = FXCollections.observableArrayList();

    private IFacadeMetierRealisateur facadeMetierRealisateur;

    public ListeRealisateursController() {
        this.facadeMetierRealisateur = FacadeMetierFactory.fabriquerFacadeMetierRealisateur();
    }

    @FXML
    public void initialize() {
        try {
            FilteredList<Realisateur> realisateurFilteredList = new FilteredList<>(realisateurObservableList, r -> true);

            this.txtRechercherRealisateur.textProperty().addListener(obs -> {
                realisateurFilteredList.setPredicate(r -> r.getNom().toLowerCase().startsWith(txtRechercherRealisateur.getText().toLowerCase()));
            });

            realisateurObservableList.clear();
            realisateurObservableList.addAll(this.facadeMetierRealisateur.recupererTousLesRealisateur());
            tblRealisateur.setItems(realisateurFilteredList);

            tbcNom.setCellValueFactory(r -> new SimpleStringProperty(r.getValue().getNom()));

            this.ajouterContextMenu();
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void goToAccueil() {
        try {
            Lanceur.loadFxml("accueil");
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void goToFicheRealisateur() {
        try {
            Lanceur.loadFxml("realisateur/ficheRealisateur", new FicheRealisateurController());
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void ajouterContextMenu() {
        this.tblRealisateur.setRowFactory(p -> {
            TableRow<Realisateur> row = new TableRow<>();
            row.setContextMenu(creerContextMenuVoirFilmo());
            row.emptyProperty().addListener((obs, wasEmpty, isEmpty) -> {
                row.setContextMenu(creerContextMenu());
            });
            return row;
        });
    }

    private ContextMenu creerContextMenuVoirFilmo() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem menuItemVoirFilmo = new MenuItem("Voir les filmographies");

        menuItemVoirFilmo.setOnAction(e -> voirLesFilmographies());

        contextMenu.getItems().add(menuItemVoirFilmo);

        return contextMenu;
    }

    private ContextMenu creerContextMenu() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem menuItemModifier = new MenuItem("Modifier");
        MenuItem menuItemSupprimer = new MenuItem("Supprimer");
        MenuItem menuItemVoirFilmo = new MenuItem("Voir les filmographies");

        menuItemModifier.setOnAction(e -> modifier());
        menuItemSupprimer.setOnAction(e -> supprimer());
        menuItemVoirFilmo.setOnAction(e -> voirLesFilmographies());

        contextMenu.getItems().add(menuItemModifier);
        contextMenu.getItems().add(menuItemSupprimer);
        contextMenu.getItems().add(menuItemVoirFilmo);

        return contextMenu;
    }

    private void voirLesFilmographies() {
        try {
            Realisateur realisateurSelected = tblRealisateur.getSelectionModel().getSelectedItem();
            if (Objects.nonNull(realisateurSelected)) {
                Lanceur.loadFxml("film/voirFilmo", new VoirFilmoController(realisateurSelected));
            } else {
                Lanceur.loadFxml("film/voirFilmo", new VoirFilmoController());
            }
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void supprimer() {
        try {
            Realisateur realisateurToDelete = tblRealisateur.getSelectionModel().getSelectedItem();
            if (AlerteUtils.afficherMessageDansAlerte("Supprimer réalisateur", "Êtes-vous sûr de vouloir supprimer " + realisateurToDelete.getNom() + " ?", Alert.AlertType.CONFIRMATION)) {
                this.facadeMetierRealisateur.supprimerRealisateur(realisateurToDelete);
                initialize();
            }
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void modifier() {
        try {
            Realisateur realisateurToModify = tblRealisateur.getSelectionModel().getSelectedItem();
            if (Objects.nonNull(realisateurToModify)) {
                Lanceur.loadFxml("realisateur/ficheRealisateur", new FicheRealisateurController(realisateurToModify));
            }
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
