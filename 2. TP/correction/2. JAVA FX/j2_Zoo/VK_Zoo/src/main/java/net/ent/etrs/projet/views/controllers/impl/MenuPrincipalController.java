package net.ent.etrs.projet.views.controllers.impl;

import javafx.event.ActionEvent;
import net.ent.etrs.projet.start.Lanceur;

import java.io.IOException;

public class MenuPrincipalController {

    public void openListSoigneur(ActionEvent actionEvent) {
        try {
            Lanceur.loadFXML("listeSoigneur");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openListAnimal(ActionEvent actionEvent) {
        try {
            Lanceur.loadFXML("listeAnimal");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
