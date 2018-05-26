package home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

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