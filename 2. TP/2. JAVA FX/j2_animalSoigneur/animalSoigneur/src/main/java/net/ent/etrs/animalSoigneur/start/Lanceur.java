package net.ent.etrs.animalSoigneur.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;
import java.util.Objects;

public class Lanceur extends Application {
    @Getter
    private static Scene scene;

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
        Lanceur.scene = new Scene(FXMLLoader.load(Objects.requireNonNull(Lanceur.class.getResource("/views/fichePrincipale.fxml"))));

        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(Lanceur.class.getResourceAsStream("/Rigoussen.jpg"))));
        stage.setTitle("Bonjour les zamis !");
        stage.show();
    }

    public static void loadFXML(String fxml) throws IOException {
        Lanceur.scene.setRoot(FXMLLoader.load(Objects.requireNonNull(Lanceur.class.getResource("/views/" + fxml + ".fxml"))));
    }

    public static void loadFXML(String fxml, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(Lanceur.class.getResource("/views/" + fxml + ".fxml"));
        loader.setController(controller);
        Lanceur.scene.setRoot(loader.load());
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}