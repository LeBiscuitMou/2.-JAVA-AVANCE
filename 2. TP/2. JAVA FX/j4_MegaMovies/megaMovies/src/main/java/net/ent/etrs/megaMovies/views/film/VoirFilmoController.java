package net.ent.etrs.megaMovies.views.film;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import lombok.NoArgsConstructor;
import net.ent.etrs.megaMovies.model.entities.Film;
import net.ent.etrs.megaMovies.model.entities.Realisateur;
import net.ent.etrs.megaMovies.model.facade.IFacadeMetierFilm;
import net.ent.etrs.megaMovies.model.facade.IFacadeMetierRealisateur;
import net.ent.etrs.megaMovies.model.facade.exceptions.BusinessException;
import net.ent.etrs.megaMovies.model.facade.impl.FacadeMetierFactory;
import net.ent.etrs.megaMovies.start.Lanceur;
import net.ent.etrs.megaMovies.views.utils.AlerteUtils;
import net.ent.etrs.megaMovies.views.utils.Item;

import java.io.IOException;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@NoArgsConstructor
public class VoirFilmoController {
    @FXML
    public ComboBox<String> cmbRealisateur;

    @FXML
    public Label lblFilmo;

    private Realisateur realisateur = new Realisateur();

    private IFacadeMetierFilm facadeMetierFilm = FacadeMetierFactory.fabriquerFacadeMetierFilm();

    private IFacadeMetierRealisateur facadeMetierRealisateur = FacadeMetierFactory.fabriquerFacadeMetierRealisateur();

    private ObservableList<Realisateur> realisateurObservableList = FXCollections.observableArrayList();

    private ObservableList<Film> filmObservableList = FXCollections.observableArrayList();

    public VoirFilmoController(Realisateur realisateur) {
        this.realisateur = realisateur;
    }

    @FXML
    public void initialize() {
        try {
            realisateurObservableList.addAll(facadeMetierRealisateur.recupererTousLesRealisateur());
            filmObservableList.addAll(facadeMetierFilm.recupererTousLesFilm());
            for (Realisateur r : realisateurObservableList){
                cmbRealisateur.getItems().add(r.getNom());
            }
            cmbRealisateur.setValue(realisateur.getNom());
            afficherFilmo();
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
    public void afficherFilmo() {
        StringBuilder filmo = new StringBuilder();
        for (Film f : filmObservableList){
            if (f.getRealisateur().getNom().equals(cmbRealisateur.getValue())) {
                DateTimeFormatter dt = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
                filmo.append(f.getTitre()).append(" sorti le ").append(f.getDateSortie().format(dt)).append("\n");
            }
        }
        lblFilmo.setText(filmo.toString());
    }
}
