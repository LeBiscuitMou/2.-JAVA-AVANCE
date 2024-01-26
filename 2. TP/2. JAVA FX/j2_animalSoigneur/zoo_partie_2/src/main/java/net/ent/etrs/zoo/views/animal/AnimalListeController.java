package net.ent.etrs.zoo.views.animal;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.ent.etrs.zoo.models.entities.Animal;
import net.ent.etrs.zoo.models.facades.FacadeAnimal;
import net.ent.etrs.zoo.start.Lanceur;

import java.time.LocalDate;
import java.util.Objects;

public class AnimalListeController {

    @FXML
    public TableView<Animal> tvAnimal;

    @FXML
    public TableColumn<Animal, String> tcNom;

    @FXML
    public TableColumn<Animal, Float> tcpoids;

    @FXML
    public TableColumn<Animal, Integer> tcTaille;

    @FXML
    public TableColumn<Animal, String> tcDangerous;

    @FXML
    public TableColumn<Animal, LocalDate> tcDdn;

    private FacadeAnimal facadeAnimal;

    private ObservableList<Animal> animalObservableList = FXCollections.observableArrayList();

    public AnimalListeController() {
        this.facadeAnimal = new FacadeAnimal();
    }

    @FXML
    public void initialize() {
        try {
            animalObservableList.clear();
            animalObservableList.addAll(this.facadeAnimal.findAll());
            tvAnimal.setItems(animalObservableList);

            tcNom.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getNom()));
            tcpoids.setCellValueFactory(a -> new SimpleObjectProperty<>(a.getValue().getPoids()));
            tcTaille.setCellValueFactory(a -> new SimpleObjectProperty<>(a.getValue().getTaille()));
            tcDangerous.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().isDangerous() ? "Oui" : "Non"));
            tcDdn.setCellValueFactory(a -> new SimpleObjectProperty<>(a.getValue().getDateDeNaissance()));

            this.ajouterContextMenu();

        } catch (Exception e) {
            //TODO
            e.printStackTrace();
        }
    }

    @FXML
    public void goToFiche() {
        try {
            Lanceur.loadFxml("animal/fiche", new AnimalFicheController());
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
        }
    }

    public void edit() {
        try {
            Animal animalToEdit = tvAnimal.getSelectionModel().getSelectedItem();
            if (!Objects.isNull(animalToEdit)) {
                Lanceur.loadFxml("animal/fiche", new AnimalFicheController(animalToEdit));
            }
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            Animal animalToDelete = tvAnimal.getSelectionModel().getSelectedItem();
            if (!Objects.isNull(animalToDelete)) {
                this.facadeAnimal.delete(animalToDelete);
                initialize();
            }
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
        }
    }

    @FXML
    public void goToAccueil() {
        try {
            Lanceur.loadFxml("accueil");
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
        }
    }

    private void ajouterContextMenu() {
        this.tvAnimal.setRowFactory( p -> {
            TableRow<Animal> row = new TableRow<>();
            row.emptyProperty().addListener((obs, wasEmpty, isEmpty) -> {
                if (isEmpty) {
                    row.setContextMenu(null);
                } else {
                    row.setContextMenu(creerContextMenu());
                }
            });
            return row;
        });
    }

    private ContextMenu creerContextMenu() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem menuItemModifier = new MenuItem("Modifier");
        MenuItem menuItemSupprimer = new MenuItem("Supprimer");

        menuItemModifier.setOnAction(e -> edit());
        menuItemSupprimer.setOnAction(e -> delete());

        contextMenu.getItems().add(menuItemModifier);
        contextMenu.getItems().add(menuItemSupprimer);

        return  contextMenu;
    }
}
