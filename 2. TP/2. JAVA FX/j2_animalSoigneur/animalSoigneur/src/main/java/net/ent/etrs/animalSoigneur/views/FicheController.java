package net.ent.etrs.animalSoigneur.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lombok.NoArgsConstructor;
import net.ent.etrs.animalSoigneur.start.Lanceur;
import net.ent.etrs.animalSoigneur.views.exceptions.FicheControllerException;

import java.io.IOException;

@NoArgsConstructor
public class FicheController {
    public void cliquerBoutonSoigneur() {

    }

    @FXML
    public void cliquerBoutonAnimal() throws FicheControllerException {
        try {
            Lanceur.loadFXML("ficheListeAnimal");
        } catch (IOException e) {
            throw new FicheControllerException(e);
        }
    }
}