package garage.presenter;

import java.io.IOException;

import garage.view.utils.AlerteUtils;
import garage.view.utils.JfxUtils;
import garage.view.utils.Screens;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FragmentBarreMenuPresenter extends AbstractPresenter {	
	@FXML	private VBox laBarre;
	
	@FXML	private MenuItem listerVoitures;
	
	@FXML	private MenuItem ajouterVoiture;
	
	@FXML 	private MenuItem quitter;	
	
	@FXML	private MenuItem camembert;
	
	
	@FXML
	public void quitter(){
			
		super.quitter();
		
		
	}
	
	@FXML
	public void listerVoitures(){
		//Soit instancier le controleur de la vue ListerVoitures.fxml
		//Soit charger directement ListerVoitures.fxml ( et donc lui indiquer fx:controller....)
//		Stage stage = (Stage) laBarre.getScene().getWindow();			
//		AnchorPane listeVue = (AnchorPane) JfxUtils.loadFxml(Screens.SCREEN_LISTER_VOITURES);
//    	stage.setScene(new Scene(listeVue));
//    	stage.show();
		
		try {
			super.chargerScene(laBarre.getScene(), Screens.SCREEN_LISTER_VOITURES, null);
		} catch (IOException e) {
			e.printStackTrace();
			AlerteUtils.afficherMessageDansAlerte(e.getMessage(), AlertType.ERROR);
		}
		
	}
	
	@FXML
	public void ajouterVoiture(){
		//Soit instancier le controleur de la vue ajouterVoiture.fxml
		//Soit charger directement ajouterVoiture.fxml ( et donc lui indiquer fx:controller....)
		Stage stage = (Stage) laBarre.getScene().getWindow();			
		GridPane ajoutVue = (GridPane) JfxUtils.loadFxml(Screens.SCREEN_AJOUTER_VOITURE);
    	stage.setScene(new Scene(ajoutVue));
    	stage.show();	
	}
	
	@FXML
	public void apropos(){
		super.aPropos();
	}
	
	@FXML
	public void statistiquer(){
		Scene sceneCourante =  laBarre.getScene();			
		//je charge le nouveau SceneGraph
		AnchorPane statsVue = (AnchorPane) JfxUtils.loadFxml(Screens.SCREEN_STATS);
		//je remplace la scene courante avec le nouveau graphe de scene
    	sceneCourante.setRoot(statsVue);         
	}
	
	
}
