package net.ent.etrs.zoo.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.*;
import net.ent.etrs.zoo.models.entities.Animal;
import net.ent.etrs.zoo.models.references.Type;
import net.ent.etrs.zoo.start.Lanceur;
import net.ent.etrs.zoo.views.exceptions.FicheAnimalControllerException;

import java.io.IOException;
import java.util.*;

@NoArgsConstructor
public class FicheAnimalController {
    @FXML
    public TextField txtNom;

    @FXML
    public TextField txtPoids;

    @FXML
    public TextField txtTaille;

    private ToggleGroup toggleGroupRdbDangereux = new ToggleGroup();

    @FXML
    public RadioButton rdbDangereuxNon;

    @FXML
    public RadioButton rdbDangereuxOui;

    @FXML
    public DatePicker dtpDateNaissance;

    private Animal animal = new Animal();

    private ObservableList<Type> observableListTypes = FXCollections.observableArrayList(Type.values());

    @FXML
    public ChoiceBox<Type> chbType = new ChoiceBox<>();

    public FicheAnimalController(Animal animal) {
        this.animal = animal;
    }

    @FXML
    public void initialize() {
        txtNom.setText(this.animal.getNom());

        if (Objects.isNull(this.animal.getPoids())) {
            txtPoids.setText("0");
        } else {
            txtPoids.setText(this.animal.getPoids().toString());
        }

        if (Objects.isNull(this.animal.getTaille())) {
            txtTaille.setText("0.0");
        } else {
            txtTaille.setText(this.animal.getTaille().toString());
        }

        rdbDangereuxOui.setToggleGroup(toggleGroupRdbDangereux);
        rdbDangereuxNon.setToggleGroup(toggleGroupRdbDangereux);

        if (Objects.isNull(this.animal.getDangerous()) || this.animal.getDangerous()) {
            rdbDangereuxOui.setSelected(true);
            rdbDangereuxNon.setSelected(false);
        } else {
            rdbDangereuxOui.setSelected(false);
            rdbDangereuxNon.setSelected(true);
        }

        dtpDateNaissance.setValue(this.animal.getDateNaissance());
        chbType.setItems(observableListTypes);
        chbType.setValue(this.animal.getType());
    }

    @FXML
    public void valider() throws FicheAnimalControllerException {
        try {
            if (Objects.isNull(txtNom) || Objects.isNull(txtPoids) || Objects.isNull(txtTaille) || Objects.isNull(dtpDateNaissance) || Objects.isNull(chbType) ||
                    Objects.isNull(txtNom.getText()) || Objects.isNull(txtPoids.getText()) || Objects.isNull(txtTaille.getText()) || Objects.isNull(dtpDateNaissance.getValue()) || Objects.isNull(chbType.getValue()) ||
                    txtNom.getText().isBlank() || txtPoids.getText().isBlank() || txtTaille.getText().isBlank() || (!rdbDangereuxOui.isSelected() && !rdbDangereuxNon.isSelected())) {
                afficherAlerte();
            } else {
                animal.setNom(txtNom.getText());
                animal.setPoids(Integer.parseInt(txtPoids.getText()));
                animal.setTaille(Float.parseFloat(txtTaille.getText()));
                animal.setDangerous(rdbDangereuxOui.isSelected());
                animal.setDateNaissance(dtpDateNaissance.getValue());
                animal.setType(chbType.getValue());

                ListeAnimalController.getAnimals().remove(animal);
                ListeAnimalController.getAnimals().add(animal);

                Lanceur.loadFXML("listeAnimal");
            }
        } catch (IOException e) {
            throw new FicheAnimalControllerException(e.getMessage(), e);
        }
    }

    private void afficherAlerte() {
        System.out.println("NULL");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Il faut saisir toutes les informations !");
        alert.showAndWait().ifPresent(rs -> {
            if (rs.equals(ButtonType.OK)) {
                System.out.println("Pressed OK.");
            }
        });
    }
}