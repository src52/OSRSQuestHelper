package home;
import java.io.IOException;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
    private static HostServices hostServices;

    private static Stage primaryStage;
    private BorderPane page;

    @Override
    public void start(Stage primaryStage) throws IOException {
        hostServices = getHostServices();
        System.out.println("FXML resource: " + getClass().getResource("/home/MainMenuLayout.fxml"));

        FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource("MainMenuLayout.fxml"));
        Parent mainMenuPane = mainMenuLoader.load();
        Scene mainMenuScene = new Scene(mainMenuPane, 325, 370);
        mainMenuScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        FXMLLoader questGuideLoader = new FXMLLoader(getClass().getResource("/home/TabPaneLayout.fxml"));
        Parent questGuidePane = questGuideLoader.load();
        Scene questGuideScene = new Scene(questGuidePane, 700, 400);

        TabPaneController tabPaneController = questGuideLoader.getController();
        tabPaneController.setMainMenuScene(questGuideScene);

        // injecting second scene into the controller of the first scene
        MainMenuController mainMenuController = mainMenuLoader.getController();
        mainMenuController.setQuestGuideScene(questGuideScene);

        // injecting first scene into the controller of the second scene
        TabPaneController questGuideController = questGuideLoader.getController();
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

}