package net.ent.etrs.fistJavaFX.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TestController {

    @FXML
    public TextField nomTextField;

    @FXML
    public TextField prenomTextField;

    @FXML
    public void click() {
        System.out.println(nomTextField.getText() + " " + prenomTextField.getText());
    }
}