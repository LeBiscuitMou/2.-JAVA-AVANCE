package net.ent.etrs.zoo.views;

import javafx.fxml.FXML;
import net.ent.etrs.zoo.start.Lanceur;
import net.ent.etrs.zoo.views.exceptions.AccueilControllerException;

import java.io.IOException;

public class AccueilController {

    @FXML
    public void afficherListeAnimal() throws AccueilControllerException {
        try {
            Lanceur.loadFXML("listeAnimal");
        } catch (IOException e) {
            throw new AccueilControllerException(e.getMessage(), e);
        }
    }
}