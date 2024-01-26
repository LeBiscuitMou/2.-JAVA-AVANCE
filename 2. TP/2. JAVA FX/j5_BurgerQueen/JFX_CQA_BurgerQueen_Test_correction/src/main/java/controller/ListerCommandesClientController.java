package controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.VBox;
import model.entities.Client;
import model.entities.Commande;
import model.entities.Produit;
import model.references.TailleProduit;
import view.converter.CommandeConverter;
import view.utils.Screens;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class ListerCommandesClientController extends AbstractController {

    @FXML
    VBox barreMenu;

    @FXML
    Label lblClient;

    @FXML
    ListView<Commande> lvCommandes;

    @FXML
    TableView<Map.Entry<Produit, Integer>> tvProduitsCommande;

    @FXML
    TableColumn<Map.Entry<Produit, Integer>, String> tcNomProduitCommande;

    @FXML
    TableColumn<Map.Entry<Produit, Integer>, TailleProduit> tcTailleProduitCommande;

    @FXML
    TableColumn<Map.Entry<Produit, Integer>, Integer> tcQteProduitCommande;

    @FXML
    Button btnValider;

    @FXML
    Button btnAccueil;

    /**
     * Liste observable de commandes.
     */
    ObservableList<Commande> oLstCommandes = FXCollections.observableArrayList();

    /**
     * Liste observable de produits.
     */
    ObservableList<Entry<Produit, Integer>> oLstProduitsCommande = FXCollections.observableArrayList();

    /**
     * Liste filtrée de produits.
     */
    FilteredList<Entry<Produit, Integer>> filteredLstProduitsCommande = new FilteredList<>(this.oLstProduitsCommande,
            s -> false);

    Commande commande;

    Client clientSelectionne;

    public ListerCommandesClientController(Client client) {
        this.clientSelectionne = client;
    }

    @FXML
    public void initialize() {
        initLblClient();
        initLstViewCommande();
        chargerTvProduitCommande();

    }

    private void rechargerLstProduitsCommande() {
        this.oLstProduitsCommande.clear();
        this.oLstProduitsCommande.addAll(this.commande.getProduits().entrySet());
    }

    private void initLblClient() {
        String s = String.format("%s %s ", this.clientSelectionne.getNom(), this.clientSelectionne.getPrenom());
        this.lblClient.setText(s);
    }

    private void initLstViewCommande() {
        if (Objects.nonNull(this.clientSelectionne)) {
            this.oLstCommandes.addAll(FACADE_COMMANDE.searchCommandeByClient(this.clientSelectionne));
        }
        this.lvCommandes.setItems(this.oLstCommandes);
        this.lvCommandes.setCellFactory(lv -> new TextFieldListCell<>(new CommandeConverter()));
        this.lvCommandes.getSelectionModel().selectedItemProperty().addListener((obervable, oldValue, newValue) -> {
            this.filteredLstProduitsCommande
                    .setPredicate((produit) -> predicatFiltreLstProduitsCommande(newValue, produit));
            this.commande = newValue;
            this.rechargerLstProduitsCommande();
        });

    }

    private Boolean predicatFiltreLstProduitsCommande(Commande newValue, Entry<Produit, Integer> produit) {
        if (newValue == null) {
            return true;
        }
        return newValue.getProduits().containsKey(produit.getKey());
    }

    private void chargerTvProduitCommande() {
        this.tcNomProduitCommande.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getKey().getNom()));
        this.tcTailleProduitCommande
                .setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getKey().getTailleProduit()));
        this.tcQteProduitCommande.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue()));

        this.tvProduitsCommande.setItems(this.filteredLstProduitsCommande);
    }

    @FXML
    public void accueil() {
        this.chargerScene(this.barreMenu.getScene(), Screens.SCREEN_ACCUEIL, null);
    }

}
