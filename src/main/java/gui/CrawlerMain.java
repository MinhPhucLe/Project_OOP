package gui;
import controller.CrawlerProgressBarController;
import crawler.*;

import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CrawlerMain extends Application{

    private AnchorPane pane;
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/progressbar.fxml"));
        CrawlerProgressBarController controller = new CrawlerProgressBarController();
        loader.setController(controller);
        try {
            pane = loader.load();
            Scene scene = new Scene(pane);
            primaryStage.setTitle("Crawler");
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}
