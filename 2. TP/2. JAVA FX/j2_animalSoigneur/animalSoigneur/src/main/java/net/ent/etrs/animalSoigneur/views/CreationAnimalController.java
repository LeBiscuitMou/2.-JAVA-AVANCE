package net.ent.etrs.animalSoigneur.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lombok.NoArgsConstructor;
import net.ent.etrs.animalSoigneur.model.entities.Animal;
import net.ent.etrs.animalSoigneur.model.entities.EntitiesFactory;
import net.ent.etrs.animalSoigneur.model.entities.exceptions.EntitiesFactoryException;
import net.ent.etrs.animalSoigneur.model.entities.references.Type;
import net.ent.etrs.animalSoigneur.start.Lanceur;
import net.ent.etrs.animalSoigneur.views.exceptions.CreationAnimalControllerException;

import java.io.IOException;
import java.time.LocalDate;

@NoArgsConstructor
public class CreationAnimalController {
    @FXML
    public DatePicker dtpNaissance;

    @FXML
    public ChoiceBox chbDangereux;

    @FXML
    public TextField txttaille;

    @FXML
    public TextField txtNom;

    @FXML
    public TextField txtPoids;

    private Animal animal = new Animal();

    public CreationAnimalController(Animal animal) throws EntitiesFactoryException {
        this.animal = animal;
    }

    @FXML
    public void initialize() {
        txtNom.setText(animal.getNom());
        txtPoids.setText(animal.getPoids().toString());
        txttaille.setText(animal.getTaille().toString());
        chbDangereux.getItems().add("Oui");
        chbDangereux.getItems().add("Non");
        if (animal.getDangerous().equals(true)) {
            chbDangereux.setValue("Oui");
        } else if (animal.getDangerous().equals(false)) {
            chbDangereux.setValue("Non");
        } else {
            chbDangereux.setValue(null);
        }
        dtpNaissance.setValue(animal.getDateNaissance());
    }

    public void validerCreationOuModification() throws IOException {
        animal.setNom(txtNom.getText());
        animal.setPoids(Integer.parseInt(txtPoids.getText()));
        animal.setTaille(Float.parseFloat(txttaille.getText()));
        animal.setDangerous(chbDangereux.getValue().equals("Oui"));
        animal.setDateNaissance(dtpNaissance.getValue());

        ListeAnimalController.getAnimals().remove(animal);
        ListeAnimalController.getAnimals().add(animal);
        Lanceur.loadFXML("ficheListeAnimal");
    }
}