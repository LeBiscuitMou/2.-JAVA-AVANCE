package net.ent.etrs.projet.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Lanceur extends Application {
    private static Scene scene;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Lanceur.scene = new Scene(FXMLLoader.load(Objects.requireNonNull(Lanceur.class.getResource("/views/menuPrincipal.fxml"))), 800, 600);
        stage.setScene(Lanceur.scene);
        stage.show();
    }

    public static void loadFXML(String fxml) throws IOException {
        Lanceur.scene.setRoot(FXMLLoader.load(Objects.requireNonNull(Lanceur.class.getResource("/views/" + fxml + ".fxml"))));
    }

    public static void loadFXML(String fxml, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Lanceur.class.getResource("/views/" + fxml + ".fxml")));
        loader.setController(controller);
        Lanceur.scene.setRoot(loader.load());
    }
}
