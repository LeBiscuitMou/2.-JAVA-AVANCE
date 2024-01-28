package keepfit;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Lanceur extends Application {    


	@Override
	public void start(Stage primaryStage) {    
		primaryStage.setTitle("Keep fit");		
		try {
			AnchorPane vuePrincipale = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/keepfit/view/ListerAbonnes.fxml"));
			primaryStage.sizeToScene();
			primaryStage.setScene(new Scene(vuePrincipale));
			primaryStage.setResizable(false);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();			
		} 
	}    




	public static void main(String[] args) {
		launch(args);
	}
}