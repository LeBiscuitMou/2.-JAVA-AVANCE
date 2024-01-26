package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import view.utils.ClickableMenu;
import view.utils.Screens;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class BarreMenuController extends AbstractController {

	@FXML 
	VBox laBarre;
	
	@FXML
	MenuBar menuBar;
	
	@FXML
	public void initialize() {
		ClickableMenu clickableMenu =new ClickableMenu("Accueil");
		clickableMenu.setOnAction(e->this.accueil());
		this.menuBar.getMenus().add(0, clickableMenu);
	}
	
	@FXML
	public void listerClients() {
			chargerScene(this.laBarre.getScene(), Screens.SCREEN_LISTER_CLIENTS, null);
	}

	@FXML
	public void ajouterClient() {
			chargerScene(this.laBarre.getScene(), Screens.SCREEN_GERER_CLIENT, new GererClientController(null));
	}

	@FXML
	public void creerCommande() {
			chargerScene(this.laBarre.getScene(), Screens.SCREEN_CREER_COMMANDE, new CreerCommandeController(null));
	}
	
	@FXML
	public void accueil() {
			chargerScene(this.laBarre.getScene(), Screens.SCREEN_ACCUEIL, null);
	}
	

	@FXML
	public void listerCommandes() {
			chargerScene(this.laBarre.getScene(), Screens.SCREEN_LISTER_COMMANDES, null);
	}
	
	@FXML
	public void listerCommandesClient() {
			chargerScene(this.laBarre.getScene(), Screens.SCREEN_LISTER_COMMANDES, new ListerCommandesClientController(null));
	}
	

//	private void chargerScene(Scene sceneCourante, String screen, GererContactController controller) throws IOException {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource(screen));
//		if(!Objects.isNull(controller)) {
//			loader.setController(controller);
//		}
//		sceneCourante.setRoot(loader.load());
//	}

	@FXML
	public void quitter() {
		super.quitter();
	}
	
	@FXML
    public void notifyClicked(Event event) {
		System.out.println(event);
		this.accueil();
    }

	@FXML
	public void apropos() {
		super.aPropos();
	}
}
