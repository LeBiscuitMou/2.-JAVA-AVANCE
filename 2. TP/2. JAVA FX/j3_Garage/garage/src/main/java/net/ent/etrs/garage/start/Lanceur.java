package net.ent.etrs.garage.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Lanceur extends Application {
    private static Scene scene;

    public static Stage stage;

    public static void main(String[] args) {
        Application.launch(args);
    }

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
        Lanceur.stage = stage;
        Lanceur.scene  = new Scene(FXMLLoader.load(Objects.requireNonNull(Lanceur.class.getResource("/views/accueil.fxml"))));
        stage.setScene(Lanceur.scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(Lanceur.class.getResourceAsStream("/garage.png"))));
        stage.setTitle("Garage");
        stage.show();
    }

    public static void loadFxml(String fxml) throws IOException {
        Lanceur.scene.setRoot(FXMLLoader.load(Objects.requireNonNull(Lanceur.class.getResource("/views/" + fxml + ".fxml"))));
    }

    public static void loadFxml(String fxml, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(Lanceur.class.getResource("/views/" + fxml + ".fxml"));
        loader.setController(controller);
        Lanceur.scene.setRoot(loader.load());
    }
}