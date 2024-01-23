package net.ent.etrs.firstjavafx.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TestController {

    @FXML
    public TextField tfNom;

    @FXML
    public TextField tfPrenom;

    @FXML
    public void click() {
        System.out.println(tfNom.getText() + " " + tfPrenom.getText());
    }
}
