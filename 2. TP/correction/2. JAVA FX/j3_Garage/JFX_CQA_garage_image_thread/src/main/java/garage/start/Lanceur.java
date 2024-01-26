package garage.start;


import garage.view.utils.JfxUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Lanceur extends Application {    
	
	//This method is called immediately after the Application class is loaded and constructed
	
	public void init(){
		System.out.println("Init de l'application");
	}
	
	//This method is called when the application should stop,
	//and provides a convenient place to prepare for application exit and destroy resources. 
	public void stop(){
		System.out.println("Arret de l'application... par la méthode stop");
	}
  
    @Override
    //Le point d'entrée de toutes les applications JavaFX 
    public void start(Stage primaryStage) {    
        primaryStage.setTitle("Mon garage");
        
        //Pas besoin de rootLayout
        //BorderPane rootLayout = (BorderPane) JfxUtils.loadFxml("../view/RootLayout.fxml");
        
        VBox vueAccueil = (VBox) JfxUtils.loadFxml("/garage/view/Accueil.fxml");       
        //Ou alors instanciation du controleur de Accueil.fxml
        
        
        // Set person overview into the center of root layout.
        //rootLayout.setCenter(personOverview);        
        
        //primaryStage.setScene(new Scene(rootLayout));
        primaryStage.setScene(new Scene(vueAccueil));
        primaryStage.show();
        
        
    }    
   

   //Depuis JAVA 8, plus besoin de main
	
    public static void main(String[] args) {
        //launch(args);
        Application.launch(args);
    }
}