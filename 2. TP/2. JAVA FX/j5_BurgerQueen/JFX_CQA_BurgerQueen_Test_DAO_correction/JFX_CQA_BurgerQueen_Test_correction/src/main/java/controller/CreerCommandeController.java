package controller;

import common.utils.C;
import common.validator.ValidException;
import common.validator.ValidatorUtils;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.entities.Client;
import model.entities.Commande;
import model.entities.Produit;
import model.exceptions.BusinessException;
import model.exceptions.ProduitException;
import model.references.TailleProduit;
import model.references.TypeProduit;
import view.converter.ClientConverter;
import view.utils.AlerteUtils;
import view.utils.Screens;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreerCommandeController extends AbstractController {

    @FXML
    VBox barreMenu;

    @FXML
    TextField tfFiltreClient;

    @FXML
    ComboBox<Client> cbxClient;

    @FXML
    TextField tfFiltreProduit;

    @FXML
    ComboBox<TypeProduit> cbxTypeProduit;

    @FXML
    TableView<Produit> tvProduits;

    @FXML
    TableColumn<Produit, String> tcNomProduit;

    @FXML
    TableColumn<Produit, TailleProduit> tcTailleProduit;

    @FXML
    TableColumn<Produit, BigDecimal> tcPrixProduit;

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
    Button btnAnnuler;

    @FXML
    Button btnAjouterProduit;

    @FXML
    Button btnRetirerProduit;

    @FXML
    Label lblPrixCommande;

    /**
     * Liste observable des clients de l'application.
     */
    ObservableList<Client> oLstClients = FXCollections.observableArrayList();
    /**
     * Liste filtrée des clients de l'application.
     */
    FilteredList<Client> filteredLstClients = new FilteredList<>(oLstClients);

    /**
     * Liste obsrvable des types de produits de l'application.
     */
    ObservableList<TypeProduit> oLstTypeProduits = FXCollections.observableArrayList();

    /**
     * Liste observable de produits.
     */
    ObservableList<Produit> oLstProduits = FXCollections.observableArrayList();
    /**
     * Liste filtrée des produits.
     */
    FilteredList<Produit> filteredLstProduits = new FilteredList<>(oLstProduits);
    /**
     * Liste triée des produits.
     */
    SortedList<Produit> sortedLstProduits;

    /**
     * Liste observable de produits.
     */
    ObservableList<Entry<Produit, Integer>> oLstProduitsCommande = FXCollections.observableArrayList();

    Commande commandeAModifier;

    Client clientSelectionne;

    public CreerCommandeController(Client client) {
        this.clientSelectionne = client;

    }

    @FXML
    public void initialize() {
        rechargerListeClients();
        initFilteredLstClients();
        initTfFiltreClient();
        initCbxClient();

        initTfFiltreProduit();

        initCbxTypeProduit();

        rechargerListeProduits();
        initFilteredLstProduits();
        chargerTvProduit();

        initCommande();
        chargerTvProduitCommande();

        bindLblPrixCommande();

    }

    private void initTfFiltreProduit() {
        tfFiltreProduit.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredLstProduits.setPredicate(produit -> {
                boolean filtre = predicatFiltreLstProduits(newValue, produit);
                return filtre;
            });
        });
    }

    private boolean predicatFiltreLstProduits(String newValue, Produit produit) {
        if ((newValue == null || newValue.isEmpty()) && produit.getTypeProduit().equals(this.cbxTypeProduit.getSelectionModel().getSelectedItem())) {
            return true;
        }

        String lowerCaseFilter = newValue.toLowerCase();

        if (produit.getNom().toLowerCase().contains(lowerCaseFilter) && produit.getTypeProduit().equals(this.cbxTypeProduit.getSelectionModel().getSelectedItem())) {
            return true;
        }
        return false;
    }

    private void bindLblPrixCommande() {
        this.oLstProduitsCommande.addListener(new ListChangeListener<Entry<Produit, Integer>>() {

            @Override
            public void onChanged(Change<? extends Entry<Produit, Integer>> c) {
                majLblPrixCommande();
            }
        });
    }

    private void majLblPrixCommande() {
        BigDecimal prixTot = calculerTotalCommande();
        this.lblPrixCommande.setText(prixTot.toString());
    }

    private BigDecimal calculerTotalCommande() {
        BigDecimal prixTot = BigDecimal.ZERO;
        prixTot = new BigDecimal(this.oLstProduitsCommande.stream()
                .mapToDouble(entry -> entry.getKey().getPrix().multiply(new BigDecimal(entry.getValue())).doubleValue())
                .sum());
        return prixTot;
    }

    private void initCommande() {
//        try {
//            if (Objects.nonNull(this.clientSelectionne)) {
//                this.commandeAModifier = EntitiesFactory.fabriquerCommande(this.clientSelectionne, new ArrayList<>());
//            } else {
//                this.commandeAModifier = EntitiesFactory.fabriquerCommande(EntitiesFactory.fabriquerClient("temp", "temp", "temp@temp"), new ArrayList<>());
//            }
//        } catch (ProduitException | ValidException e) {
//            e.printStackTrace();
//            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), AlertType.WARNING);
//        }
        this.commandeAModifier = new Commande();
    }

    private void chargerTvProduitCommande() {
        this.tcNomProduitCommande.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getKey().getNom()));
        this.tcTailleProduitCommande
                .setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getKey().getTailleProduit()));
        this.tcQteProduitCommande.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue()));

        this.tvProduitsCommande.setItems(this.oLstProduitsCommande);
    }

    private void initFilteredLstProduits() {
        this.filteredLstProduits = new FilteredList<>(oLstProduits, s -> false);
    }

    private void rechargerListeProduits() {

        try {
            this.oLstProduits.clear();
            this.oLstProduits.addAll(FACADE_PRODUIT.findAll().stream().sorted((p1, p2) -> comparerProduits(p1, p2))
                    .collect(Collectors.toList()));
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), AlertType.WARNING);
        }
    }

    private int comparerProduits(Produit p1, Produit p2) {
        int retour;
        retour = p1.getNom().toLowerCase().compareTo(p2.getNom().toLowerCase());
        if (retour == 0) {
            retour = p1.getTailleProduit().compareTo(p2.getTailleProduit());
        }
        return retour;
    }

    private void initCbxTypeProduit() {
        this.oLstTypeProduits.addAll(TypeProduit.values());
        this.cbxTypeProduit.setItems(this.oLstTypeProduits);

        this.cbxTypeProduit.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.filteredLstProduits.setPredicate((produit) -> predicatFiltreLstProduits(newValue, produit));
        });

    }

    private Boolean predicatFiltreLstProduits(TypeProduit newValue, Produit produit) {
        if (newValue == null) {
            return true;
        }
        if (produit.getTypeProduit().equals(newValue)) {
            return true;
        }
        return false;
    }

    private void chargerTvProduit() {
        tcNomProduit.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tcTailleProduit.setCellValueFactory(new PropertyValueFactory<>("tailleProduit"));
        tcPrixProduit.setCellValueFactory(new PropertyValueFactory<>("prix"));

        this.sortedLstProduits = new SortedList<>(filteredLstProduits);
        this.sortedLstProduits.comparatorProperty().bind(this.tvProduits.comparatorProperty());
        this.tvProduits.setItems(this.sortedLstProduits);
    }

    private void initCbxClient() {
        this.cbxClient.setConverter(new ClientConverter());
        this.cbxClient.setItems(filteredLstClients);
        if (Objects.nonNull(this.clientSelectionne)) {
            this.cbxClient.getSelectionModel().select(this.clientSelectionne);
        }
        this.cbxClient.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.clientSelectionne = newValue;
            if (Objects.nonNull(this.clientSelectionne)) {
                this.commandeAModifier.setClient(this.clientSelectionne);

            }
        });

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
    }

    private void initTfFiltreClient() {
        tfFiltreClient.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredLstClients.setPredicate(client -> {
                boolean filtre = predicatFiltreLstClients(newValue, client);
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
        if (client.getNom().toLowerCase().contains(lowerCaseFilter)
                || client.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
            return true;
        }
        return false; // Does not match.
    }

    private int comparerClients(Client c1, Client c2) {
        int retour;
        retour = c1.getNom().toLowerCase().compareTo(c2.getNom().toLowerCase());
        if (retour == 0) {
            retour = c1.getPrenom().toLowerCase().compareTo(c2.getPrenom().toLowerCase());
        }
        return retour;
    }

    @FXML
    public void validerCommande() {

        try {


            if (Objects.isNull(this.clientSelectionne)) {
                AlerteUtils.afficherMessageDansAlerte("Veuillez sélectionner un client.", AlertType.ERROR);
            } else {
                this.commandeAModifier.setClient(this.clientSelectionne);
                ValidatorUtils.validate(this.commandeAModifier);
                FACADE_COMMANDE.save(this.commandeAModifier);
                this.resumerCommande();
                this.annuler();
            }

        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(String.format("Ajout impossible %n%s", e.getMessage()),
                    AlertType.ERROR);
        } catch (ValidException e) {
            AlerteUtils.afficherMessageDansAlerte(String.format("Ajout impossible %n%s", e.getMessage()),
                    AlertType.ERROR);
        }

    }

    private void resumerCommande() {
        StringBuilder sb = new StringBuilder(
                String.format("Commande du %s%n", this.commandeAModifier.getDateCommande()));
        sb.append("Liste des produits : ");
        sb.append(System.lineSeparator());
        this.commandeAModifier.getProduits()
                .forEach((k, v) -> sb.append(String.format("%s X %s (%s)%n", v, k.getNom(), k.getTailleProduit())));
        sb.append("-------------------");
        sb.append(System.lineSeparator());
        sb.append(String.format("Total de la commande : %s", calculerTotalCommande()));
        AlerteUtils.afficherMessageDansAlerte(sb.toString(), AlertType.INFORMATION);
    }

    @FXML
    public void ajouterProduit() {
        Produit p = this.tvProduits.getSelectionModel().getSelectedItem();
        if (Objects.nonNull(p)) {
            try {
                this.commandeAModifier.ajouterProduits(p);
                rechargerLstProduitsCommande();
            } catch (ProduitException e) {
                AlerteUtils.afficherMessageDansAlerte(e.getMessage(), AlertType.WARNING);
            }
        } else {
            AlerteUtils.afficherMessageDansAlerte(C.MSG_AUCUN_PRODUIT_SELECTIONNE, AlertType.WARNING);
        }

    }

    private void rechargerLstProduitsCommande() {
        this.oLstProduitsCommande.clear();
        this.oLstProduitsCommande.addAll(this.commandeAModifier.getProduits().entrySet());
    }

    @FXML
    public void retirerProduit() {
        Entry<Produit, Integer> entryProduit = this.tvProduitsCommande.getSelectionModel().getSelectedItem();
        if (Objects.nonNull(entryProduit)) {
            Produit p = entryProduit.getKey();
            try {
                this.commandeAModifier.retirerProduit(p);
                rechargerLstProduitsCommande();
            } catch (BusinessException e) {
                AlerteUtils.afficherMessageDansAlerte(e.getMessage(), AlertType.WARNING);
            }
        } else {
            AlerteUtils.afficherMessageDansAlerte(C.MSG_AUCUN_PRODUIT_SELECTIONNE, AlertType.WARNING);
        }
    }

    @FXML
    public void annuler() {
        this.chargerScene(this.barreMenu.getScene(), Screens.SCREEN_ACCUEIL, null);
    }


}
