package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import model.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
		Scene scene = new Scene(root, 1185, 718);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
			
		primaryStage.setTitle("ьет-йсуюп");
		primaryStage.getIcons().add(new Image("/images/chef.png"));
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
