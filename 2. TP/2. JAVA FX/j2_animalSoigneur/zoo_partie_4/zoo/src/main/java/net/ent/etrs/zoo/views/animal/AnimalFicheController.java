package net.ent.etrs.zoo.views.animal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import lombok.NoArgsConstructor;
import net.ent.etrs.zoo.models.entities.Animal;
import net.ent.etrs.zoo.models.entities.references.Type;
import net.ent.etrs.zoo.models.facades.FacadeAnimal;
import net.ent.etrs.zoo.start.Lanceur;
import net.ent.etrs.zoo.views.utils.AlerteUtils;

@NoArgsConstructor
public class AnimalFicheController {
    @FXML
    public TextField tfNom;
    @FXML
    public Spinner<Integer> spTaille;
    @FXML
    public Spinner<Double> spPoids;
    @FXML
    public CheckBox cbDangerous;
    @FXML
    public DatePicker dpDdn;
    @FXML
    public ComboBox<Type> cmbType;

    private FacadeAnimal facadeAnimal = new FacadeAnimal();
    private Animal animal = new Animal();

    public AnimalFicheController(Animal animalToEdit) {
        this.animal = animalToEdit;
    }

    @FXML
    public void initialize() {
        tfNom.setText(animal.getNom());
        spPoids.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.01, 5000.00,
                animal.getPoids() != null ? animal.getPoids() : 0, 0.01
                ));
        spPoids.setEditable(true);
        spTaille.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20,
                animal.getTaille() != null ? animal.getTaille() : 0, 1
        ));
        spTaille.setEditable(true);
        cbDangerous.setSelected(animal.isDangerous());
        dpDdn.setValue(animal.getDateDeNaissance());

        ObservableList<Type> typeObservableList = FXCollections.observableArrayList();
        typeObservableList.addAll(Type.values());
        cmbType.setItems(typeObservableList);
        cmbType.setValue(animal.getType());
    }


    @FXML
    public void valider() {
        try {
            animal.setNom(tfNom.getText());
            animal.setTaille(spTaille.getValue());
            animal.setPoids(spPoids.getValue().floatValue());
            animal.setDangerous(cbDangerous.isSelected());
            animal.setDateDeNaissance(dpDdn.getValue());
            animal.setType(cmbType.getValue());

            this.facadeAnimal.save(animal);

            Lanceur.loadFxml("animal/liste");
        } catch (Exception e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
