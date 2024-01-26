package garage.presenter;

import java.io.IOException;
import java.util.Objects;

import garage.model.facade.FacadeFactory;
import garage.model.facade.IFacadeMetier;
import garage.view.utils.AlerteUtils;
import garage.view.utils.C_VIEW;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;

public abstract class AbstractPresenter {
	private IFacadeMetier leMetier;

	public AbstractPresenter() {
		this.leMetier = FacadeFactory.fabriquerFacadeMetier();
		this.leMetier.initialiser();
	}

	public IFacadeMetier getLeMetier() {
		return leMetier;
	}

	/**
	 * Permet de charger une page fxml dans une scene en spécifiant le controller.
	 * 
	 * @param sceneCourante
	 * @param screen        page fxml
	 * @param controller
	 * @throws IOException
	 */
	protected void chargerScene(Scene sceneCourante, String screen, Object controller) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(screen));
		if (!Objects.isNull(controller)) {
			loader.setController(controller);
		}
		sceneCourante.setRoot(loader.load());
	}

	protected void quitter() {
		//Ajouter une askyesnoDialog
//		Alert alert = new Alert(AlertType.CONFIRMATION);
//		alert.setTitle("Confirmation Dialog");
//		alert.setHeaderText("Confirmation");
//		alert.setContentText("Etes-vous sur de vouloir quitter?");
//		
//		//Je recupere le panneau du dialog
//		DialogPane dialogPane = alert.getDialogPane();
//		dialogPane.getStylesheets().add(getClass().getResource(Screens.SCREEN_APPLICATION_CSS).toExternalForm());
//		dialogPane.getStyleClass().add("myDialog");
//				
//		Optional<ButtonType> result = alert.showAndWait();
//		if (result.get() == ButtonType.OK){
//			System.out.println("Sortie de l'application...");
//			Platform.exit();
//		}	
		boolean sortie = AlerteUtils.afficherMessageDansAlerte(C_VIEW.CONFIRMATION_DIALOG, C_VIEW.CONFIRMATION,
				C_VIEW.CONFIRMATION_QUITTER, AlertType.CONFIRMATION);

		if (sortie) {
			System.out.println("Sortie de l'application...");
			Platform.exit();
		}
	}

	public void aPropos() {
		AlerteUtils.afficherMessageDansAlerte("Information Dialog", "Copyright", "Made by warti", AlertType.INFORMATION);
	}

}
