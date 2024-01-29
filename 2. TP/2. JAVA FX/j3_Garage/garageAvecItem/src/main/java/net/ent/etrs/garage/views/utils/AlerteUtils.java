package net.ent.etrs.garage.views.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AlerteUtils {
	/**
	 * Affiche le messages de l'exceptione passée en paramètre avec le niveau d'alerte aussi passé en paramètre.
	 * @param e : l'exception.
	 * @param niveauAlerte: INFO,ERROR,WARNING
	 */
	public static void afficherExceptionDansAlerte(Exception e, AlertType niveauAlerte) {
		Alert a = new Alert(niveauAlerte);
		a.setContentText(e.getMessage());
		a.showAndWait();
	}
	
	/**
	 * Affiche le messages passé en paramètre avec le niveau d'alerte aussi passé en paramètre.
	 * @param msg : le message.
	 * @param niveauAlerte: INFO,ERROR,WARNING
	 * @return 
	 */
	public static Boolean afficherMessageDansAlerte(String msg, AlertType niveauAlerte) {
		Boolean retour = false;
		Alert a = new Alert(niveauAlerte);
		a.setContentText(msg);
		Optional<ButtonType> oBtnType = a.showAndWait();

		if(oBtnType.get().getButtonData().equals(ButtonType.OK.getButtonData())) {
			retour = true;
		}
		
		return retour;
	}
	
	/**
	 * Affiche le messages passé en paramètre avec le niveau d'alerte aussi passé en paramètre.
	 * @param titre : le titre de la fenêtre.
	 * @param msg : le message.
	 * @param niveauAlerte: INFO,ERROR,WARNING
	 * @return 
	 */
	public static Boolean afficherMessageDansAlerte(String titre, String msg, AlertType niveauAlerte) {
		Boolean retour = false;
		Alert a = new Alert(niveauAlerte);
		a.setTitle(titre);
		a.setContentText(msg);
		Optional<ButtonType> oBtnType = a.showAndWait();

		if(oBtnType.get().getButtonData().equals(ButtonType.OK.getButtonData())) {
			retour = true;
		}
		
		return retour;
	}
}
