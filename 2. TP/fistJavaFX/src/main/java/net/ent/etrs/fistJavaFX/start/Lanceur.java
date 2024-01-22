package net.ent.etrs.fistJavaFX.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

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
//        stage.setTitle("Hello World!");
//
//        BorderPane pane = new BorderPane();
//        Button button = new Button("Cliquez ici pour découvrir la vérité");
//
//        TextField textField = new TextField();
//        TextField textField2 = new TextField();
//
//        pane.setCenter(button);
//        pane.setLeft(textField);
//        pane.setRight(textField2);
//
//        textField.textProperty().bindBidirectional(textField2.textProperty());
//
//        Scene scene = new Scene(pane, 300, 150);
//
//        stage.setScene(scene);
//        stage.show();
//
//        Label label = new Label("Vous êtes raciste");
//        button.setOnMouseClicked(event -> button.setText(label.getText()));
//

        Parent parent = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/views/test.fxml")));

        Scene scene = new Scene(parent, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}