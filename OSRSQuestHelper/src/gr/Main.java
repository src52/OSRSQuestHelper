package gr;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	private static HostServices hostServices ;
	
	private static Stage primaryStage;
	private BorderPane page;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		hostServices = getHostServices();
		
		FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource("MainMenuLayout.fxml"));
        Parent mainMenuPane = mainMenuLoader.load();
        Scene mainMenuScene = new Scene(mainMenuPane, 325, 370);
        mainMenuScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		
        FXMLLoader questGuideLoader = new FXMLLoader(getClass().getResource("TabPaneLayout.fxml"));
        Parent questGuidePane = questGuideLoader.load();
        Scene questGuideScene = new Scene(questGuidePane, 700, 400);
        
        // injecting second scene into the controller of the first scene
        MainMenuController mainMenuController = (MainMenuController) mainMenuLoader.getController();
        mainMenuController.setQuestGuideScene(questGuideScene);

        // injecting first scene into the controller of the second scene
        TabPaneController questGuideController = (TabPaneController) questGuideLoader.getController();
        questGuideController.setMainMenuScene(mainMenuScene);
        
        primaryStage.setTitle("Quest Helper OSRS");	
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static HostServices getHost() {
        return hostServices ;
    }
	public static Stage getPrimaryStage() {
        return primaryStage;
    }
	private void setPrimaryStage(Stage pStage) {
        Main.primaryStage = pStage;
    }
	
	public Pane getRootPane() {
        return page;
    }
}
