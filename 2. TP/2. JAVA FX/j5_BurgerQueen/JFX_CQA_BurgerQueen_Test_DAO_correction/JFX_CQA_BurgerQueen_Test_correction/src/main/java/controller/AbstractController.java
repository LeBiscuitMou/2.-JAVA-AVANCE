package controller;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import model.facade.ClientFacade;
import model.facade.CommandeFacade;
import model.facade.FacadeFactory;
import model.facade.ProduitFacade;
import view.C_VIEW;
import view.utils.AlerteUtils;

/**
 * Classe abstraite pour les controller.
 * @author christophe.cerqueira
 *
 */
public abstract class AbstractController {

	/**
	 * Facade client.
	 */
	public static final ClientFacade FACADE_CLIENT = FacadeFactory.fabriquerClientFacade();
	/**
	 * Facade commande.
	 */
	public static final CommandeFacade FACADE_COMMANDE = FacadeFactory.fabriquerCommandeFacade();
	/**
	 * Facade produit.
	 */
	public static final ProduitFacade FACADE_PRODUIT = FacadeFactory.fabriquerProduitFacade();

	/**
	 * Permet de charger une page fxml dans une scene en spécifiant le controller.
	 * 
	 * @param sceneCourante
	 * @param screen        page fxml
	 * @param controller
	 * @throws IOException
	 */
	protected void chargerScene(Scene sceneCourante, String screen, Object controller) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(screen));
		if (!Objects.isNull(controller)) {
			loader.setController(controller);
		}
		try {
			sceneCourante.setRoot(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
			AlerteUtils.afficherMessageDansAlerte("Une erreure s'est produite lors du chargement de la page.", AlertType.ERROR);
		}
	}

	protected void quitter() {
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
