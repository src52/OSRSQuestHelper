package gr;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainMenuController implements Initializable{
	private Scene questGuideScene;
	
	@FXML
    private ImageView questGuideButtonImg;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		
	}

    public void setQuestGuideScene(Scene scene) {
    	questGuideScene = scene;
    }

    public void openQuestGuideScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(questGuideScene);
    }
}
