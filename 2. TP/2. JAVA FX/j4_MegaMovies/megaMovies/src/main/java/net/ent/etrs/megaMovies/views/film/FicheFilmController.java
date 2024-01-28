package net.ent.etrs.megaMovies.views.film;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import lombok.NoArgsConstructor;
import net.ent.etrs.megaMovies.model.entities.Film;
import net.ent.etrs.megaMovies.model.entities.Realisateur;
import net.ent.etrs.megaMovies.model.entities.references.Genre;
import net.ent.etrs.megaMovies.model.facade.IFacadeMetierFilm;
import net.ent.etrs.megaMovies.model.facade.IFacadeMetierRealisateur;
import net.ent.etrs.megaMovies.model.facade.exceptions.BusinessException;
import net.ent.etrs.megaMovies.model.facade.impl.FacadeMetierFactory;
import net.ent.etrs.megaMovies.start.Lanceur;
import net.ent.etrs.megaMovies.views.utils.AlerteUtils;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;

@NoArgsConstructor
public class FicheFilmController {
    @FXML
    public TextField txtTitre;

    @FXML
    public DatePicker dtpSortie;

    @FXML
    public ComboBox<Genre> cmbGenre;

    @FXML
    public ComboBox<String> cmbRealisateur;

    private IFacadeMetierFilm facadeMetierFilm = FacadeMetierFactory.fabriquerFacadeMetierFilm();

    private IFacadeMetierRealisateur facadeMetierRealisateur = FacadeMetierFactory.fabriquerFacadeMetierRealisateur();

    private ObservableList<Realisateur> realisateurObservableList = FXCollections.observableArrayList();

    private ObservableList<Genre> genreObservableList = FXCollections.observableArrayList();

    private Film film = new Film();

    public FicheFilmController(Film filmToModify) {
        this.film = filmToModify;
    }

    // Factory to create Cell of DatePicker
    private Callback<DatePicker, DateCell> getDayCellFactory() {

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        // Disable Monday, Tueday, Wednesday.
                        if (item.getDayOfWeek() != DayOfWeek.WEDNESDAY) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }

    @FXML
    public void initialize() {
        try {
            txtTitre.setText(film.getTitre());

            realisateurObservableList.addAll(facadeMetierRealisateur.recupererTousLesRealisateur());
            for(Realisateur r : realisateurObservableList){
                cmbRealisateur.getItems().add(r.getNom());
            }
            if (null != film.getRealisateur()) {
                cmbRealisateur.setValue(film.getRealisateur().getNom());
            }

            dtpSortie.setValue(film.getDateSortie());
            dtpSortie.setShowWeekNumbers(true);

            Callback<DatePicker, DateCell> dayCellFactory = this.getDayCellFactory();
            dtpSortie.setDayCellFactory(dayCellFactory);

            genreObservableList.addAll(Genre.values());
            cmbGenre.setItems(genreObservableList);
            cmbGenre.setValue(film.getGenre());
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void valider() {
        try {
            if (null == txtTitre.getText() || txtTitre.getText().isBlank()
                    || null == cmbRealisateur.getValue() || cmbRealisateur.getValue().isBlank()
                    || null == dtpSortie.getValue()
                    || null == cmbGenre.getValue()) {
                AlerteUtils.afficherMessageDansAlerte("Attention !!!", "Il faut saisir toutes les informations !", Alert.AlertType.WARNING);
            } else {
                film.setTitre(txtTitre.getText());

                for(Realisateur r : realisateurObservableList){
                    if (r.getNom().equals(cmbRealisateur.getValue())) {
                        film.setRealisateur(r);
                        break;
                    }
                }

                film.setDateSortie(dtpSortie.getValue());

                film.setGenre(cmbGenre.getValue());

                facadeMetierFilm.creerFilm(film);
                Lanceur.loadFxml("film/listeFilms");
            }
        } catch (BusinessException | IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void goToListeFilms() {
        try {
            Lanceur.loadFxml("film/listeFilms");
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
