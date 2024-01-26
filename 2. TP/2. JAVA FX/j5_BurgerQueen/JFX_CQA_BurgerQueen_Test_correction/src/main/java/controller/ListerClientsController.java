package controller;

import common.utils.C;
import controller.exceptions.ViewException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.entities.Client;
import model.exceptions.BusinessException;
import view.utils.AlerteUtils;
import view.utils.Screens;

import javax.persistence.RollbackException;
import java.util.Objects;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListerClientsController extends AbstractController {

    final ObservableList<Client> oLstClients = FXCollections.observableArrayList();
    @FXML
    VBox barreMenu;
    @FXML
    TableView<Client> tvClients;
    @FXML
    TableColumn<Client, String> tcNom;
    @FXML
    TableColumn<Client, String> tcPrenom;
    @FXML
    TableColumn<Client, String> tcCourriel;
    @FXML
    TextField tfFiltreClient;
    @FXML
    Button btnCreer;
    @FXML
    Button btnModifier;
    @FXML
    Button btnSupprimer;
    FilteredList<Client> filteredLstClients = new FilteredList<>(oLstClients, s -> true);

    SortedList<Client> sortedLstClients;

    @FXML
    public void initialize() {
        rechargerListeClients();

        initFilteredLstClients();

        chargerTvClients();
    }

    private void rechargerListeClients() {

        try {
            this.oLstClients.clear();
            this.oLstClients.addAll(FACADE_CLIENT.findAll().stream().sorted((c1, c2) -> comparerClients(c1, c2))
                    .collect(Collectors.toList()));
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), AlertType.WARNING);
        }
    }

    private void initFilteredLstClients() {
        this.filteredLstClients = new FilteredList<>(oLstClients, s -> true);
        tfFiltreClient.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredLstClients.setPredicate(contact -> {
                boolean filtre = predicatFiltreLstClients(newValue, contact);
                return filtre;
            });
        });
    }

    private boolean predicatFiltreLstClients(String newValue, Client client) {
        // si le texte de filtre est vide ou null, on renvoit toute la liste.
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }

        String lowerCaseFilter = newValue.toLowerCase();

        // on compare le nom ou le prénom avec le texte de filtre
        return client.getNom().toLowerCase().contains(lowerCaseFilter)
                || client.getPrenom().toLowerCase().contains(lowerCaseFilter);// Does not match.
    }

    private int comparerClients(Client c1, Client c2) {
        int retour;
        retour = c1.getNom().toLowerCase().compareTo(c2.getNom().toLowerCase());
        if (retour == 0) {
            retour = c1.getPrenom().toLowerCase().compareTo(c2.getPrenom().toLowerCase());
        }
        return retour;
    }

    private void chargerTvClients() {
        // Construction des cellules de chacune des colonnes
        tcNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tcPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tcCourriel.setCellValueFactory(new PropertyValueFactory<>("courriel"));

        this.sortedLstClients = new SortedList<>(this.filteredLstClients);
        this.sortedLstClients.comparatorProperty().bind(this.tvClients.comparatorProperty());
        this.tvClients.setItems(this.sortedLstClients);

    }

    @FXML
    public void creerClient() {
        this.chargerScene(this.barreMenu.getScene(), Screens.SCREEN_GERER_CLIENT, new GererClientController(null));
    }

    @FXML
    public void modifierClient() {
        try {
            Client client = controlerClientSelection();
            this.chargerScene(this.barreMenu.getScene(), Screens.SCREEN_GERER_CLIENT,
                    new GererClientController(client));

        } catch (ViewException e) {

            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), AlertType.WARNING);
        }
    }

    @FXML
    public void supprimerClient() {
        try {

            Client c = this.controlerClientSelection();
            FACADE_CLIENT.delete(c);
            rechargerListeClients();
        } catch (ViewException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), AlertType.WARNING);
        } catch (RollbackException | BusinessException e) {
            if (e.getMessage().contains("Error Code: 1451")) {
                AlerteUtils.afficherMessageDansAlerte(C.MSG_SUPPRESSION_IMPOSSIBLE_COMMANDE_EXISTANTE, AlertType.WARNING);
            }
        }

    }

    @FXML
    public void passerCommande() {
        try {
            Client c = this.controlerClientSelection();
            chargerScene(this.barreMenu.getScene(), Screens.SCREEN_CREER_COMMANDE, new CreerCommandeController(c));
        } catch (ViewException e) {
            e.printStackTrace();
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), AlertType.WARNING);
        }
    }

    private Client controlerClientSelection() throws ViewException {
        Client client = this.tvClients.getSelectionModel().getSelectedItem();
        if (!Objects.isNull(client)) {
            return client;
        } else {
            throw new ViewException(C.MSG_CLIENT_NON_SELECTIONNE);
        }
    }

    @FXML
    public void listerCommande() {
        try {
            Client c = this.controlerClientSelection();
            chargerScene(this.barreMenu.getScene(), Screens.SCREEN_LISTER_COMMANDES, new ListerCommandesClientController(c));
        } catch (ViewException e) {
            e.printStackTrace();
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), AlertType.WARNING);
        }
    }
}
