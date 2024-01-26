package garage.presenter;

import java.io.IOException;

import garage.model.dao.services.ListingVoituresService;
import garage.model.dao.services.SuppressionVoitureService;
import garage.model.entities.Voiture;
import garage.model.exceptions.BusinessException;
import garage.view.utils.AlerteUtils;
import garage.view.utils.Screens;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListerVoituresPresenter extends AbstractPresenter {
	@FXML
	private TableView<Voiture> tvVoitures;

	@FXML
	private TableColumn<Voiture, String> tcImmat;

	@FXML
	private TableColumn<Voiture, String> tcMarque;

	@FXML
	private TableColumn<Voiture, String> tcModele;

	@FXML
	private CheckBox chkBxTri;
	
	@FXML
	private ProgressIndicator pi;
	

	private BooleanProperty triPuissanceActif = new SimpleBooleanProperty(false);

	private ObservableList<Voiture> olstVoitures = FXCollections.observableArrayList();

	@FXML
	public void initialize() {
		initOLstVoitures();

		chargerTvVoitures();
	}

	private void chargerTvVoitures() {
		// Rien de visuel
		this.tvVoitures.setItems(olstVoitures);

		// Avec les lambdas JAVA 8
		tcImmat.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getImmatriculation()));

		tcMarque
				.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getMarque().getLibelle()));

		tcModele.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getModele()));
	}

	private void initOLstVoitures() {
		// traitement long donc appel au service de listing de voitures
		ListingVoituresService threadListingVoitures = new ListingVoituresService();

		// En cas de succès de l'execution du service
//		threadListingVoitures.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
//		public void handle(WorkerStateEvent event) {
//			olstVoitures.addAll(threadListingVoitures.getValue());						
//		};
//		});

		// ou avec une expression lambda
		threadListingVoitures
				.setOnSucceeded((WorkerStateEvent event) -> {
					
						olstVoitures.addAll(threadListingVoitures.getValue());
						pi.setVisible(false);}
				);
		threadListingVoitures.setOnRunning((WorkerStateEvent event) -> pi.setVisible(true));
		// voituresTv.itemsProperty().bind(threadListingVoitures.valueProperty());

		
		
		pi.progressProperty().bind(threadListingVoitures.progressProperty());
		
		// Demarrage du service (s'executera dans un autre thread que le JAVA FX
		// apllication Thread)
		threadListingVoitures.start();
	}

	// Reponses au menu Contextuel (détails et supprimer)

	@FXML
	public void detailler() {

		Voiture voitureDetail = tvVoitures.getSelectionModel().getSelectedItem();
//		// On crée juste une boite de dialogue d'information
//		Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setTitle("Détails pour: " + voitureDetail.getModele());
//		alert.setHeaderText("Détails pour: " + voitureDetail.getModele());
//
//		// Je recupere le pane de la boite de dialogue
//		DialogPane dialogPane = alert.getDialogPane();
//		// Je lui applique le css
//		dialogPane.getStylesheets()
//				.add(getClass().getResource("/garage/view/resources/application.css").toExternalForm());
//		dialogPane.getStyleClass().add("myDialog");
//
//		// Je mets en contenu le ToString de la voiture
//		alert.setContentText(voitureDetail.toString());
//
//		/// Chargement de l'image correspondant à la marque de la voiture
////		String chemin = String.format("/view/resources/%s.png", voitureDetail.getMarque().getLibelle().toString());
////		Image image = new Image(this.getClass().getResourceAsStream(chemin));
//
//
//		Image img;
//		try {
//			String chemin = voitureDetail.getMarque().getCheminComplet();
//			img = new Image(chemin, 100.0, 40.0, true, true);
//		} catch (NullPointerException | IllegalArgumentException e) {
//
//			img = new Image("/garage/view/resources/NULL.png");
//		}
//
//		// Creation d'un ImageView pour y mettre l'image
//		ImageView iv = new ImageView();
//		iv.setImage(img);
//		
//		
//		// Insertion de l'imageView dans le header du dialogPane
//		alert.setGraphic(iv);
//		alert.showAndWait();
		
		AlerteUtils.afficherMessageDansAlerte("Détails pour: " + voitureDetail.getModele(), 
											  "Détails pour: " + voitureDetail.getModele(), 
											  voitureDetail.toString(), 
											  AlertType.INFORMATION, 
											  voitureDetail.getMarque().getCheminComplet());
	}

	@FXML
	public void supprimer() {
		Voiture voitureSupp = tvVoitures.getSelectionModel().getSelectedItem();

		// Pas mal mais insuffisant ( il faut supprimer la voiture de la base de
		// données)
		olstVoitures.remove(voitureSupp);

		// Suppression de la bdd
//		try {
//			super.getLeMetier().supprimerUneVoiture(voitureSupp);
//		} catch (BusinessException e) {
//			AlerteUtils.afficherMessageDansAlerte(e.getMessage(), AlertType.ERROR);
//		}

		// Appel au service de suppression des voitures ( lancé dans un autre thread que
		// le JAVA FX Application Thread)
		 SuppressionVoitureService suppService = new SuppressionVoitureService(voitureSupp);
		 suppService.start();

	}

	public void editer() {
		// je recupere la voiture a editer
		Voiture voitureEdit = tvVoitures.getSelectionModel().getSelectedItem();
		// Ecran de modification "intégré" au meme Stage
		// Je recupere la scene
//		Scene scene = voituresTv.getScene();
//
//		FXMLLoader loader = new FXMLLoader(getClass().getResource(Screens.SCREEN_MODIFIER_VOITURE));
//		loader.setController(new ModifierVoiturePresenter(voitureEdit));
//		Parent root;
//		try {
//			root = (Parent) loader.load();
//			scene.setRoot(root);
//		} catch (IOException e) {
//			AlerteUtils.afficherMessageDansAlerte(e.getMessage(), AlertType.ERROR);
//		}
		
		
		try {
			chargerScene(tvVoitures.getScene(), Screens.SCREEN_MODIFIER_VOITURE, new ModifierVoiturePresenter(voitureEdit));
		} catch (IOException e) {
			AlerteUtils.afficherMessageDansAlerte(e.getMessage(), AlertType.ERROR);
		}

	}

	public void trierParPuissance() {
		// Binding de la propriete styleActiveProperty a l'etat du checkbox

		triPuissanceActif.bind(chkBxTri.selectedProperty());
		if (triPuissanceActif.get()) {
			olstVoitures.clear();
			olstVoitures.addAll(super.getLeMetier().listerVoituresParPuissance());
			tvVoitures.setItems(olstVoitures);
//			colonneImmat.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getImmatriculation()));
//			colonneMarque.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getMarque().getLibelleMarque()));
//			colonneModele.setCellValueFactory((param)-> new SimpleStringProperty(param.getValue().getModele()));

		} else {
			
			try {
				olstVoitures.clear();
				olstVoitures.addAll(super.getLeMetier().listerVoitures());
				tvVoitures.setItems(olstVoitures);
			} catch (BusinessException e) {
				AlerteUtils.afficherMessageDansAlerte(e.getMessage(), AlertType.ERROR);
			}
			
			
//			colonneImmat.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getImmatriculation()));
//			colonneMarque.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getMarque().getLibelleMarque()));
//			colonneModele.setCellValueFactory((param)-> new SimpleStringProperty(param.getValue().getModele()));
		}
	}

}
