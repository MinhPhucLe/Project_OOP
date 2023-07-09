package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import data.util.UrlContainer;

import java.io.IOException;

public class MainScreen extends Application {
    public void on(String[] args) {
        new Program();
        launch(args);
    }
    private static Controller controller;
    public static void callUrl(String url){
        controller.moveToURL(url,false);
    }
    public static void back(){ controller.back(); }
    public static String getCurrentUrl(){ return controller.getCurrentUrl(); }
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/layout.fxml"));
            controller = new Controller();
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Vietnam History Wiki");
            primaryStage.setScene(scene);
            primaryStage.show();
            callUrl(UrlContainer.HOME_URL);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}