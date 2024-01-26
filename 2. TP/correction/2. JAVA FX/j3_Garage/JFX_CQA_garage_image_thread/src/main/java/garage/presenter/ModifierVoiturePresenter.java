package garage.presenter;

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

public class ModifierVoiturePresenter extends AbstractPresenter {

	ObservableList<Marque> oLstMarques = FXCollections.observableArrayList();

//	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

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

	private Voiture laVoiture;

	public ModifierVoiturePresenter(Voiture voitureModifiee) {
		this.laVoiture = voitureModifiee;
	}

	@FXML
	private void initialize() {
		initOLstMarques();
		initListenerSlPuissance();
		
		this.tfImmat.setText(this.laVoiture.getImmatriculation());
		this.tfModele.setText(this.laVoiture.getModele());
		this.slPuissance.setValue(this.laVoiture.getPuissance());
		this.lblPuissance.setText(String.format("Puissance:%1d hp", this.laVoiture.getPuissance()));
		this.dpMec.setValue(laVoiture.getMiseEnCirculation());

		

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
		});
		listService.start();
		
		this.cbMarque.converterProperty().setValue(new MarqueConverter());
		this.cbMarque.setValue(laVoiture.getMarque());
	}

	@FXML
	public void modifier() {
		Marque marqueChoisie = cbMarque.getSelectionModel().getSelectedItem();

		this.laVoiture.setImmatriculation(tfImmat.getText());
		this.laVoiture.setMarque(marqueChoisie);
		this.laVoiture.setModele(tfModele.getText());
		this.laVoiture.setPuissance((int) slPuissance.getValue());
		this.laVoiture.setMiseEnCirculation(dpMec.getValue());

		try {
			ValidatorUtils.validate(this.laVoiture);

			super.getLeMetier().mettreAJour(this.laVoiture);
			initComposantsPage();

		} catch (ValidationException e) {
			AlerteUtils.afficherMessageDansAlerte(C_VIEW.ERREUR_SAISIE, C_VIEW.IMPOSSIBLE_MODIFER_VOITURE,
					e.getMessage(), AlertType.ERROR);
		} catch (BusinessException e) {
			AlerteUtils.afficherMessageDansAlerte(e.getMessage(), AlertType.ERROR);
		}

//		// je passe la nouvelle voiture à créer au Validator
//		Set<ConstraintViolation<Voiture>> violations = this.validator.validate(this.laVoiture);
//
//		// Aucune contrainte violée
//		if (violations.size() == 0) {
//
//			try {
//				super.getLeMetier().mettreAJour(this.laVoiture);
//				tfImmat.clear();
//				cbMarque.setValue(null);
//				tfModele.clear();
//				slPuissance.setValue(100);
//				;
//				dpMec.setValue(null);
//			} catch (BusinessException e) {
//				AlerteUtils.afficherMessageDansAlerte(e.getMessage(), AlertType.ERROR);
//			}
//
//		} else {
//			// Sinon popup d'erreur
//			StringBuilder sb = new StringBuilder();
//			for (ConstraintViolation<Voiture> constraintViolation : violations) {
//				sb.append(constraintViolation.getMessage() + "\n");
//			}
////			Alert alert = new Alert(AlertType.ERROR);
////			alert.setTitle("Erreur de saisie");
////			alert.setHeaderText("Impossible de mettre à jour la voiture");
////			alert.setContentText(sb.toString());		
////			
////			//Je recupere le panneau du dialog pour appliquer le style a la dialogbox
////			DialogPane dialogPane = alert.getDialogPane();
////			dialogPane.getStylesheets().add(getClass().getResource(Screens.SCREEN_APPLICATION_CSS).toExternalForm());
////			dialogPane.getStyleClass().add("myDialog");			
////			alert.showAndWait();
//			AlerteUtils.afficherMessageDansAlerte("Erreur de saisie", "Impossible de mettre à jour la voiture",
//					sb.toString(), AlertType.ERROR);
//		}
	}

	private void initComposantsPage() {
		tfImmat.clear();
		cbMarque.setValue(null);
		tfModele.clear();
		slPuissance.setValue(100);
		dpMec.setValue(null);
	}
}
