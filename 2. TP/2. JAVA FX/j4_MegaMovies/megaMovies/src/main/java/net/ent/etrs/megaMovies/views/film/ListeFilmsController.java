package net.ent.etrs.megaMovies.views.film;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.ent.etrs.megaMovies.model.entities.Film;
import net.ent.etrs.megaMovies.model.entities.Realisateur;
import net.ent.etrs.megaMovies.model.entities.references.Genre;
import net.ent.etrs.megaMovies.model.facade.IFacadeMetierFilm;
import net.ent.etrs.megaMovies.model.facade.exceptions.BusinessException;
import net.ent.etrs.megaMovies.model.facade.impl.FacadeMetierFactory;
import net.ent.etrs.megaMovies.start.Lanceur;
import net.ent.etrs.megaMovies.views.utils.AlerteUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class ListeFilmsController {
    @FXML
    public TableView<Film> tblFilms;

    @FXML
    public TableColumn<Film, String> tbcTitre;

    @FXML
    public TableColumn<Film, LocalDate> tbcDateSortie;

    @FXML
    public TableColumn<Film, Genre> tbcGenre;

    @FXML
    public TextField txtRechercherFilm;

    private IFacadeMetierFilm facadeMetierFilm;

    private ObservableList<Film> filmObservableList = FXCollections.observableArrayList();

    public ListeFilmsController() {
        this.facadeMetierFilm = FacadeMetierFactory.fabriquerFacadeMetierFilm();
    }

    @FXML
    public void initialize() {
        try {
            FilteredList<Film> filmFilteredList = new FilteredList<>(filmObservableList, f -> true);

            this.txtRechercherFilm.textProperty().addListener(obs -> {
                filmFilteredList.setPredicate(f -> f.getTitre().toLowerCase().startsWith(txtRechercherFilm.getText().toLowerCase()));
            });

            filmObservableList.clear();
            filmObservableList.addAll(facadeMetierFilm.recupererTousLesFilm());
            tblFilms.setItems(filmFilteredList);

            tbcTitre.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getTitre()));
            tbcDateSortie.setCellValueFactory(f -> new SimpleObjectProperty<>(f.getValue().getDateSortie()));
            tbcGenre.setCellValueFactory(f -> new SimpleObjectProperty<>(f.getValue().getGenre()));

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
    public void goToFicheFilm() {
        try {
            Lanceur.loadFxml("film/ficheFilm", new FicheFilmController());
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void ajouterContextMenu() {
        this.tblFilms.setRowFactory(p -> {
            TableRow<Film> row = new TableRow<>();
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

        MenuItem menuItemModifier = new MenuItem("Modifier le film");
        MenuItem menuItemSupprimer = new MenuItem("Supprimer le film");
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
            Film filmSelected = tblFilms.getSelectionModel().getSelectedItem();
            if (Objects.nonNull(filmSelected)) {
                Realisateur realisateurSelected = filmSelected.getRealisateur();
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
            Film filmToDelete = tblFilms.getSelectionModel().getSelectedItem();
            if (AlerteUtils.afficherMessageDansAlerte("Supprimer", "Êtes-vous sûr de vouloir supprimer le film " + filmToDelete.getTitre() + " ?", Alert.AlertType.CONFIRMATION)) {
                facadeMetierFilm.supprimerFilm(filmToDelete);
                initialize();
            }
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void modifier() {
        try {
            Film filmToModify = tblFilms.getSelectionModel().getSelectedItem();
            if (Objects.nonNull(filmToModify)) {
                Lanceur.loadFxml("film/ficheFilm", new FicheFilmController(filmToModify));
            }
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
