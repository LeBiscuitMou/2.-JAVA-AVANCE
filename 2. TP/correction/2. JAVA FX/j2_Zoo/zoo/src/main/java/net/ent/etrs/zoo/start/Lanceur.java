package net.ent.etrs.zoo.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Lanceur extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
       Lanceur.scene  = new Scene(FXMLLoader.load(Lanceur.class.getResource("/views/accueil.fxml")), 800, 600);
       stage.setScene(Lanceur.scene);
       stage.show();
    }

    public static void loadFxml(String fxml) throws IOException {
        Lanceur.scene.setRoot(FXMLLoader.load(Lanceur.class.getResource("/views/" + fxml + ".fxml")));
    }

    public static void loadFxml(String fxml, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(Lanceur.class.getResource("/views/" + fxml + ".fxml"));
        loader.setController(controller);
        Lanceur.scene.setRoot(loader.load());
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
