package net.ent.etrs.firstjavafx.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import net.ent.etrs.firstjavafx.start.Lanceur;

import java.io.IOException;

public class TestController {

    @FXML
    public TextField tfNom;

    @FXML
    public TextField tfPrenom;

    @FXML
    public void click() throws IOException {
        System.out.println(tfNom.getText() + " " + tfPrenom.getText());
        Lanceur.loadFxml("liste");
    }
}
