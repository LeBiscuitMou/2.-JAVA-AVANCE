package net.ent.etrs.zoo.views;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import lombok.*;
import net.ent.etrs.zoo.models.Animal;
import net.ent.etrs.zoo.models.Type;

import javax.persistence.*;
import javax.validation.constraints.*;

@NoArgsConstructor
public class FicheAnimalController {
    @FXML
    public TextField txtNom;

    @FXML
    public TextField txtPoids;

    @FXML
    public TextField txtTaille;

    @FXML
    public RadioButton rdbDangereuxNon;

    @FXML
    public RadioButton rdbDangereuxOui;

    @FXML
    public DatePicker dtpDateNaissance;

    @FXML
    public ChoiceBox<Type> chbType;

    private Animal animal = new Animal();

    public FicheAnimalController(Animal animal) {
        this.animal = animal;
    }
}