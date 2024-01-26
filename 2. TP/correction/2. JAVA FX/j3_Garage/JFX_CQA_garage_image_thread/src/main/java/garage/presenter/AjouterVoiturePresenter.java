package garage.presenter;

import java.time.LocalDate;

import javax.validation.ValidationException;

import common.ValidatorUtils;
import garage.model.dao.services.ListingMarquesService;
import garage.model.entities.Marque;
import garage.model.entities.Voiture;
import garage.model.exceptions.BusinessException;
import garage.view.utils.AlerteUtils;
import garage.view.utils.C_VIEW;
import garage.view.utils.MarqueConverter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class AjouterVoiturePresenter extends AbstractPresenter {

	private ObservableList<Marque> oLstMarques = FXCollections.observableArrayList();

	@FXML
	private TextField tfImmat;
	@FXML
	private ComboBox<Marque> cbMarque;
	@FXML
	private TextField tfModele;
	@FXML
	private Slider slPuissance;
	@FXML
	private Label lblPuissance;
	@FXML
	private DatePicker dpMec;

	@FXML
	public void initialize() {
		initOLstMarques();

		initListenerSlPuissance();

	}

	private void initListenerSlPuissance() {
		slPuissance.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				lblPuissance.setText(String.format("Puissance:%.0f hp", newValue));

			}
		});
	}

	private void initOLstMarques() {
		// Appel au service de listing des marques ( lancé dans un autre thread que le
		// JAVA FX Application Thread)
		ListingMarquesService listService = new ListingMarquesService();

		listService.setOnSucceeded((WorkerStateEvent event) -> {
			// La tâche s'est correctement terminée.
			this.oLstMarques = listService.getValue();
			cbMarque.getItems().addAll(oLstMarques);
			AlerteUtils.afficherMessageDansAlerte("Les marques sont chargées", AlertType.INFORMATION);
		});

		listService.start();
		
		cbMarque.converterProperty().setValue(new MarqueConverter());
		cbMarque.getItems().addAll(oLstMarques);
	}

	public void ajouter() {

		String immatSaisi = tfImmat.getText();
		String modeleSaisi = tfModele.getText();
		int puissanceChoisie = (int) slPuissance.getValue();
		LocalDate dateMecChoisie = dpMec.getValue();
		Marque marqueChoisie = cbMarque.getSelectionModel().getSelectedItem();

		Voiture voitureCreee = new Voiture(immatSaisi, modeleSaisi, puissanceChoisie, dateMecChoisie, marqueChoisie);

		try {
			ValidatorUtils.validate(voitureCreee);

			super.getLeMetier().mettreAJour(voitureCreee);
			initComposantsPage();

		} catch (ValidationException e) {
			AlerteUtils.afficherMessageDansAlerte(C_VIEW.ERREUR_SAISIE, C_VIEW.IMPOSSIBLE_CREER_VOITURE,
					e.getMessage(), AlertType.ERROR);
		} catch (BusinessException e) {
			AlerteUtils.afficherMessageDansAlerte(e.getMessage(), AlertType.ERROR);
		}

	}

	private void initComposantsPage() {
		tfImmat.clear();
		cbMarque.setValue(null);
		tfModele.clear();
		slPuissance.setValue(100);
		dpMec.setValue(null);
	}

}
