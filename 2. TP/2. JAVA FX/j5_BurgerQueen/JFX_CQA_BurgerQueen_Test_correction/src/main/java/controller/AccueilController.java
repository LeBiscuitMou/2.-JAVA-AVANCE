package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import model.exceptions.BusinessException;
import view.utils.AlerteUtils;
import view.utils.Screens;

public class AccueilController extends AbstractController {

    @FXML
    VBox barreMenu;

    public static void init() {
        System.out.println("init");
        try {
            FACADE_PRODUIT.initialisationProduit();
            FACADE_CLIENT.initialisationClient();
            FACADE_COMMANDE.initialisation(FACADE_CLIENT, FACADE_PRODUIT);
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.INFORMATION);
        }

    }

    @FXML
    public void initialize() {

    }

    @FXML
    public void commander() {
        this.chargerScene(this.barreMenu.getScene(), Screens.SCREEN_CREER_COMMANDE, new CreerCommandeController(null));
    }

}
