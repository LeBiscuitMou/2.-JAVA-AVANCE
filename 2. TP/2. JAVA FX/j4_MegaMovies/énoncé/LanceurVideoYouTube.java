package start;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class LanceurVideoYouTube extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Teaser"); 
	    
	    WebView webview = new WebView();
	    webview.getEngine().load("https://www.youtube.com/watch?v=1eO9ChE52WA");
	    webview.setPrefSize(640, 390);

	    primaryStage.setScene(new Scene(webview));
	    primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
