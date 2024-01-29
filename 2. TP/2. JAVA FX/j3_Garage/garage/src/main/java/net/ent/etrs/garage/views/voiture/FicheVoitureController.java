package net.ent.etrs.garage.views.voiture;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.NoArgsConstructor;
import net.ent.etrs.garage.model.entities.Marque;
import net.ent.etrs.garage.model.entities.Voiture;
import net.ent.etrs.garage.model.facade.IFacadeMetierMarque;
import net.ent.etrs.garage.model.facade.IFacadeMetierVoiture;
import net.ent.etrs.garage.model.facade.exceptions.BusinessException;
import net.ent.etrs.garage.model.facade.impl.FacadeMetierFactory;
import net.ent.etrs.garage.start.Lanceur;
import net.ent.etrs.garage.views.utils.AlerteUtils;

import java.io.IOException;

@NoArgsConstructor
public class FicheVoitureController {
    @FXML
    public TextField txtImmatriculation;

    @FXML
    public ComboBox<String> cmbMarque;

    @FXML
    public TextField txtModele;

    @FXML
    public Slider sldPuissance;

    @FXML
    public DatePicker dtpMiseEnCirculation;

    @FXML
    public Spinner<Integer> spnPuissance;

    private IFacadeMetierVoiture facadeMetierVoiture = FacadeMetierFactory.fabriquerFacadeMetierVoiture();

    private IFacadeMetierMarque facadeMetierMarque = FacadeMetierFactory.fabriquerFacadeMetierMarque();

    private ObservableList<Marque> marqueObservableList = FXCollections.observableArrayList();

    private Voiture voiture = new Voiture();

    public FicheVoitureController(Voiture voitureToEdit) {
        this.voiture = voitureToEdit;
    }

    @FXML
    public void initialize() {
        try {
            txtImmatriculation.setText(voiture.getImmatriculation());

            marqueObservableList.addAll(facadeMetierMarque.recupererToutesLesMarques());
            for(Marque m : marqueObservableList){
                cmbMarque.getItems().add(m.getLibelle());
            }

            txtModele.setText(voiture.getModele());

            sldPuissance.setValue(voiture.getPuissance() != null ? voiture.getPuissance() : 1);

            spnPuissance.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000,
                    voiture.getPuissance() != null ? voiture.getPuissance() : 0));

            sldPuissance.valueProperty().addListener(obs -> spnPuissance.getValueFactory().setValue((int) sldPuissance.getValue()));
            spnPuissance.valueProperty().addListener(obs -> sldPuissance.setValue(spnPuissance.getValue()));

            dtpMiseEnCirculation.setValue(voiture.getMiseEnCirculation());
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void valider() {
        try {
            if (null == txtImmatriculation.getText() || txtImmatriculation.getText().isBlank()
            || null == cmbMarque.getValue()
            || null == txtModele.getText() || txtModele.getText().isBlank()
            || null == dtpMiseEnCirculation.getValue()) {
                AlerteUtils.afficherMessageDansAlerte("Attention !!!", "Il faut saisir toutes les informations !", Alert.AlertType.WARNING);
            } else if (!txtImmatriculation.getText().matches("[A-Z]{2}-\\d{3}-[A-Z]{2}")) {
                AlerteUtils.afficherMessageDansAlerte("Attention !!!", "Il faut saisir l'immatriculation correctement !\nExemple : AA-123-BB", Alert.AlertType.WARNING);
            } else {
                Marque marque = null;
                voiture.setImmatriculation(txtImmatriculation.getText());

                for(Marque m : marqueObservableList){
                    if (m.getLibelle().equals(cmbMarque.getValue())) {
                        marque = m;
                    }
                }
                voiture.setMarque(marque);

                voiture.setModele(txtModele.getText());
                voiture.setPuissance((int) sldPuissance.getValue());
                voiture.setMiseEnCirculation(dtpMiseEnCirculation.getValue());

                facadeMetierVoiture.creerVoiture(voiture);

                Lanceur.loadFxml("voiture/listerVoitures");
            }
        } catch (BusinessException | IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void goToListeVoitures() {
        try {
            Lanceur.loadFxml("voiture/listerVoitures");
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
