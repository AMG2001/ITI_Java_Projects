package tech.amg.chatapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tech.amg.chatapp.Chat.DashBoard;
import tech.amg.chatapp.config.AppPages;

public class Main extends Application {

    FXMLLoader fxmlLoader;
    Scene scene;

    @Override
    public void init() {
        try {
            super.init();
            fxmlLoader = new FXMLLoader(getClass().getResource(AppPages.getNewusersPage()));
            System.out.println("Login page loaded #");
            scene = new Scene(fxmlLoader.load());
            System.out.println("Choose Room fxml file Loaded.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chat App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        DashBoard dashBoard = new DashBoard();
    }
}
