package net.ent.etrs.projet.views.controllers.impl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import lombok.NoArgsConstructor;
import net.ent.etrs.projet.models.entities.Animal;
import net.ent.etrs.projet.models.entities.EntitiesFactory;
import net.ent.etrs.projet.models.entities.Soigneur;
import net.ent.etrs.projet.models.entities.references.Type;
import net.ent.etrs.projet.models.exceptions.BusinessException;
import net.ent.etrs.projet.models.exceptions.EntitiesFactoryException;
import net.ent.etrs.projet.models.facades.impl.FacadeMetierFactory;
import net.ent.etrs.projet.models.facades.interfaces.IFacadeAnimal;
import net.ent.etrs.projet.start.Lanceur;

import java.io.IOException;
import java.util.function.Function;

@NoArgsConstructor
public class FicheAnimalController implements Function<Animal, FicheAnimalController> {
    private final IFacadeAnimal fMetAnimal = FacadeMetierFactory.fabriquerFacadeAnimal();

    @FXML
    protected TextArea frNom;
    @FXML
    protected DatePicker frDdN;
    @FXML
    protected ChoiceBox<Type> frType;
    @FXML
    protected TextArea frTaille;
    @FXML
    protected TextArea frPoids;
    @FXML
    protected CheckBox frDangereux;

    private Animal animal;


    public FicheAnimalController(Animal animal) {
        this.animal = animal;

    }

    @FXML
    public void initialize(){
        frType.getItems().addAll(Type.values());

        if(null != animal){
            frNom.setText(animal.getNom());
            frDdN.setValue(animal.getDateDeNaissance());
            frPoids.setText(String.valueOf(animal.getPoids()));
            frTaille.setText(String.valueOf(animal.getTaille()));
            frDangereux.setSelected(animal.isDangereux());
            frType.setValue(animal.getType());
        }
    }

    public void valider() {
        double poids = Double.parseDouble(frPoids.getText());
        double taille = Double.parseDouble(frTaille.getText());
        Animal newAnimal = null;

        try {
            newAnimal = EntitiesFactory.fabriquerAnimal(frNom.getText(), frDdN.getValue(), poids, taille, frDangereux.isSelected(), frType.getValue());
        } catch (EntitiesFactoryException e) {
            throw new RuntimeException(e);
        }

        if(null != this.animal){
            newAnimal.setId(this.animal.getId());
        }

        sauvegarderAnimal(newAnimal);

    }

    private void sauvegarderAnimal(Animal newAnimal) {
        try {
            fMetAnimal.save(newAnimal);
        } catch (BusinessException e) {
            try {
                Lanceur.loadFXML("menuPrincipal");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        try {
            Lanceur.loadFXML("listeAnimal");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Applies this function to the given argument.
     *
     * @param animal the function argument
     * @return the function result
     */
    @Override
    public FicheAnimalController apply(Animal animal) {
        return new FicheAnimalController(animal);
    }
}
