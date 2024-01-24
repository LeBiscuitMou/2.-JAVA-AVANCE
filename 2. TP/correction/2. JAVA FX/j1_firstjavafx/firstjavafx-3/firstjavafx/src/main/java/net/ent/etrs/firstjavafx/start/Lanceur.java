package net.ent.etrs.firstjavafx.start;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Lanceur extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
//        stage.setTitle("Hello World !!!!");
//
//        BorderPane pane = new BorderPane();
//        Button button = new Button("Hello World !!!");
//        pane.setCenter(button);
//
//        TextField tf1 = new TextField(), tf2 = new TextField();
//        tf1.textProperty().bindBidirectional(tf2.textProperty());
//
//        pane.setLeft(tf1);
//        pane.setRight(tf2);
//
//        button.setOnMouseClicked((e) -> button.setText("Tu m'as cliqué dessus :)"));
//        button.setOnMouseEntered((e) -> button.setText("Tu attends quoi pour cliquer ?"));
//
//        Scene scene = new Scene(pane, 640, 480);
//
//        stage.setScene(scene);
//        stage.show();

       Lanceur.scene  = new Scene(FXMLLoader.load(Lanceur.class.getResource("/views/liste.fxml")), 800, 600);
       stage.setScene(Lanceur.scene);
       stage.show();
    }

    public static void loadFxml(String fxml) throws IOException {
        Lanceur.scene.setRoot(FXMLLoader.load(Lanceur.class.getResource("/views/" + fxml + ".fxml")));
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
