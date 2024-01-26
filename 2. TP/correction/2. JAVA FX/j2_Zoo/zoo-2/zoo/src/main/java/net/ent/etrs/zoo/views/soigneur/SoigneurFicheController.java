package net.ent.etrs.zoo.views.soigneur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.NoArgsConstructor;
import net.ent.etrs.zoo.models.entities.Animal;
import net.ent.etrs.zoo.models.entities.Soigneur;
import net.ent.etrs.zoo.models.entities.references.Type;
import net.ent.etrs.zoo.models.facades.FacadeAnimal;
import net.ent.etrs.zoo.models.facades.FacadeSoigneur;
import net.ent.etrs.zoo.start.Lanceur;
import net.ent.etrs.zoo.views.utils.AlerteUtils;
import net.ent.etrs.zoo.views.utils.Item;

@NoArgsConstructor
public class SoigneurFicheController {
    @FXML
    public TextField tfNom;
    @FXML
    public TextField tfPrenom;
    @FXML
    public DatePicker dpDdn;
    @FXML
    public DatePicker dpDa;

    @FXML
    public ListView<Type> lvTypes;

    @FXML
    public ListView<Item<Animal>> lvAnimaux;

    private FacadeSoigneur facadeSoigneur = new FacadeSoigneur();
    private FacadeAnimal facadeAnimal = new FacadeAnimal();

    private Soigneur soigneur = new Soigneur();

    public SoigneurFicheController(Soigneur soigneurToEdit) {
        this.soigneur = soigneurToEdit;
    }


    @FXML
    public void initialize() {
        try {
            tfNom.setText(soigneur.getNom());
            tfPrenom.setText(soigneur.getPrenom());
            dpDdn.setValue(soigneur.getDateDeNaissance());
            dpDa.setValue(soigneur.getDateArrivee());

            ObservableList<Type> typeObservableList = FXCollections.observableArrayList();
            typeObservableList.addAll(Type.values());
            lvTypes.setItems(typeObservableList);
            lvTypes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            soigneur.getTypesSoignable().forEach(t -> lvTypes.getSelectionModel().select(t));

            ObservableList<Item<Animal>> animalObservableList = FXCollections.observableArrayList();
            animalObservableList.addAll(soigneur.getAnimaux().stream().map(a -> new Item<>(a, a.getNom())).toList());
            animalObservableList.addAll(this.facadeAnimal.findAllWithoutSoigneur().stream().map(a -> new Item<>(a, a.getNom())).toList());

            lvAnimaux.setItems(animalObservableList);
            lvAnimaux.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            soigneur.getAnimaux().forEach(a -> lvAnimaux.getSelectionModel().select(new Item<>(a, "")));
        } catch (Exception e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void valider() {
        try {
            soigneur.setNom(tfNom.getText());
            soigneur.setPrenom(tfPrenom.getText());
            soigneur.setDateDeNaissance(dpDdn.getValue());
            soigneur.setDateArrivee(dpDa.getValue());

            soigneur.getTypesSoignable().clear();
            lvTypes.getSelectionModel().getSelectedItems().forEach(t -> soigneur.getTypesSoignable().add(t));

            soigneur.getAnimaux().clear();
            lvAnimaux.getSelectionModel().getSelectedItems().forEach(ia -> soigneur.getAnimaux().add(ia.getObject()));

            this.facadeSoigneur.save(soigneur);

            Lanceur.loadFxml("soigneur/liste");
        } catch (Exception e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}

