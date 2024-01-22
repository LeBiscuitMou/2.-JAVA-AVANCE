package net.ent.etrs.fistJavaFX.start;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Lanceur extends Application {
    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param stage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hello World!");

        BorderPane pane = new BorderPane();
        Button button = new Button("Cliquez ici pour découvrir la vérité");

        TextField textField = new TextField();
        TextField textField2 = new TextField();

        pane.setCenter(button);
        pane.setLeft(textField);
        pane.setRight(textField2);

        textField.textProperty().bindBidirectional(textField2.textProperty());

        Scene scene = new Scene(pane, 300, 150);

        stage.setScene(scene);
        stage.show();

        Label label = new Label("Vous êtes raciste");
        button.setOnMouseClicked(event -> button.setText(label.getText()));


    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}