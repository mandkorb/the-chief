package application;

import java.io.IOException;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class infoWindowController {

    @FXML private Button infoButton;
    
    void initialize() {
    	infoButton.setOnAction(event ->{    		
    		
			try {				
				AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("info.fxml"));
				Scene newScene = new Scene(root, 400, 400);				
				Stage infoWindow = new Stage();
				infoWindow.setScene(newScene);
				infoWindow.showAndWait();
	    		infoWindow.initModality(Modality.APPLICATION_MODAL);
	    		infoWindow.setTitle("б╡ймн дндюрйнбн╞ ╡мтнплюж╡╞");				
				infoWindow.setResizable(false);
				infoWindow.getIcons().add(new Image("file:/chef.png"));
			} catch (IOException e) {
				System.out.println("blyad");
			}            
            
    	});
    }
}
