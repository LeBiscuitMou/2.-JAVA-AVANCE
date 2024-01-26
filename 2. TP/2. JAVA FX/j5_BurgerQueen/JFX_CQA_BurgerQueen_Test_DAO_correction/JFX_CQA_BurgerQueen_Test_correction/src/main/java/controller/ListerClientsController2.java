package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Client;
import model.exceptions.BusinessException;
import view.utils.AlerteUtils;
import view.utils.Screens;

import java.util.Objects;

public class ListerClientsController2 extends AbstractController {

    @FXML
    Parent barreMenu;

    @FXML
    TableView<Client> tvClients;

    @FXML
    TableColumn<Client, String> tcNom;

    @FXML
    TableColumn<Client, String> tcPrenom;

    @FXML
    TableColumn<Client, String> tcCourriel;

    @FXML
    ContextMenu contextMenu;

    @FXML
    TextField tfFiltreClient;

    ObservableList<Client> olstClients = FXCollections.observableArrayList();

    FilteredList<Client> filteredLstClients = new FilteredList<>(olstClients, c -> true);

    SortedList<Client> sortedLstClients = new SortedList<>(filteredLstClients,
            (c1, c2) -> c1.getNom().compareTo(c2.getNom()));

    public ListerClientsController2() {
//		this.FACADE_CLIENT.initialisationClient();
    }

    @FXML
    public void initialize() {

        initFilteredList();

        chargerTvClients();

    }

    private void initFilteredList() {
        tfFiltreClient.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredLstClients.setPredicate(client -> {

                return predicatFiltreLstClients(newValue, client);
            });
        });

    }

    private boolean predicatFiltreLstClients(String newValue, Client client) {
        if (Objects.isNull(newValue) || newValue.isEmpty()) {
            return true;
        }
        if (client.getNom().toUpperCase().startsWith(newValue.toUpperCase())) {
            return true;
        }

        return false;
    }

    private void chargerTvClients() {
        chargerLstClients();

        tcNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tcPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tcCourriel.setCellValueFactory(new PropertyValueFactory<>("courriel"));

//		this.tvClients.setItems(olstClients);
//		this.tvClients.setItems(filteredLstClients);
        this.sortedLstClients.comparatorProperty().bind(this.tvClients.comparatorProperty());
        this.tvClients.setItems(sortedLstClients);

        tvClients.setRowFactory(r -> {
            return creationRow(r);
        });
    }

    private void chargerLstClients() {

        try {
            this.olstClients.clear();
            this.olstClients.addAll(FACADE_CLIENT.findAll());
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.WARNING);
        }
    }

    private TableRow<Client> creationRow(TableView<Client> r) {
        TableRow<Client> row = new TableRow<>();

        row.emptyProperty().addListener((obs, wasEmpty, isEmpty) -> {
            if (isEmpty) {
                row.setContextMenu(null);
            } else {
                row.setContextMenu(creationMenuContextuel());
            }
        });
        return row;
    }

    private ContextMenu creationMenuContextuel() {

        // création du menu contextuel
        ContextMenu contextMenu = new ContextMenu();
        // création du/des options du menu contextuel
        MenuItem itemModifier = new MenuItem("Modifier");
        MenuItem itemSupprimer = new MenuItem("Supprimer");
        MenuItem itemListeCommande = new MenuItem("Liste des commandes");
        MenuItem itemPasserCommande = new MenuItem("Passer une commande");
        // mappage de la méthode supprimer() sur le menuItem
        itemModifier.setOnAction(c -> modifierClient());
        itemSupprimer.setOnAction(c -> supprimerClient());
        itemListeCommande.setOnAction(c -> listerCommande());
        itemPasserCommande.setOnAction(c -> passerCommande());
        // ajout du menuItem au contextMenu
        contextMenu.getItems().add(itemModifier);
        contextMenu.getItems().add(itemSupprimer);
        contextMenu.getItems().add(itemListeCommande);
        contextMenu.getItems().add(itemPasserCommande);

        return contextMenu;

    }

    @FXML
    public void modifierClient() {
        chargerScene(this.barreMenu.getScene(), Screens.SCREEN_GERER_CLIENT, new GererClientController(this.tvClients.getSelectionModel().getSelectedItem()));
    }

    @FXML
    public void supprimerClient() {
        try {
            FACADE_CLIENT.delete(this.tvClients.getSelectionModel().getSelectedItem());
            this.chargerLstClients();
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.WARNING);
        }

    }

    @FXML
    public void passerCommande() {
        chargerScene(this.barreMenu.getScene(), Screens.SCREEN_CREER_COMMANDE, new CreerCommandeController(this.tvClients.getSelectionModel().getSelectedItem()));
    }

    @FXML
    public void listerCommande() {
        chargerScene(this.barreMenu.getScene(), Screens.SCREEN_LISTER_COMMANDES, new ListerCommandesClientController(this.tvClients.getSelectionModel().getSelectedItem()));
    }

    @FXML
    public void creerClient() {
        chargerScene(this.barreMenu.getScene(), Screens.SCREEN_GERER_CLIENT, new GererClientController(this.tvClients.getSelectionModel().getSelectedItem()));
    }
}
