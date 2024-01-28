package net.ent.etrs.megaMovies.views.realisateur;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lombok.NoArgsConstructor;
import net.ent.etrs.megaMovies.model.entities.Realisateur;
import net.ent.etrs.megaMovies.model.facade.IFacadeMetierRealisateur;
import net.ent.etrs.megaMovies.model.facade.exceptions.BusinessException;
import net.ent.etrs.megaMovies.model.facade.impl.FacadeMetierFactory;
import net.ent.etrs.megaMovies.start.Lanceur;
import net.ent.etrs.megaMovies.views.utils.AlerteUtils;

import java.io.IOException;

@NoArgsConstructor
public class FicheRealisateurController {
    @FXML
    public TextField txtNom;

    private Realisateur realisateur = new Realisateur();

    private IFacadeMetierRealisateur facadeMetierRealisateur = FacadeMetierFactory.fabriquerFacadeMetierRealisateur();

    public FicheRealisateurController(Realisateur realisateurToModify) {
        this.realisateur = realisateurToModify;
    }

    @FXML
    public void initialize() {
        txtNom.setText(realisateur.getNom());
    }

    @FXML
    public void valider() {
        try {
            if (null == txtNom.getText() || txtNom.getText().isBlank()) {
                AlerteUtils.afficherMessageDansAlerte("Attention !!!", "Il faut saisir le nom et le prénom du réalisateur !", Alert.AlertType.WARNING);
            } else {
                realisateur.setNom(txtNom.getText());

                facadeMetierRealisateur.creerRealisateur(realisateur);

                Lanceur.loadFxml("realisateur/listeRealisateurs");
            }
        } catch (BusinessException | IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void goToListeRealisateur() {
        try {
            Lanceur.loadFxml("realisateur/listeRealisateurs");
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
