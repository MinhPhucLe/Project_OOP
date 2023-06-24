package controller;

import javafx.fxml.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.text.TextAlignment;
import org.example.MainScreen;

import java.util.ArrayList;
import java.util.List;


public class InfoController {
    @FXML
    private Label title;
    @FXML
    private VBox info;
    public InfoController(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/info.fxml"));
        loader.setController(this);
        try {
            info = loader.load();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void add(VBox box){
        box.getChildren().add(info);
    }
    public void setTitle(String s){
        title.setText(s);
    }

    public void setValue(Object o, String urlType){

        if(o instanceof String){
            Button button = new Button((String) o);
            button.setStyle("-fx-background-color: transparent;" +
                    "-fx-border-color: transparent;" +
                    "-fx-text-fill: rgb(160,82,45);" +
                    "-fx-font-size: 16px;" +
                    "-fx-padding: 6 20 6 20;");
            button.setWrapText(true);
            button.setOnMouseEntered(event -> {
                button.setStyle("-fx-background-color: rgba(160,82,45,0.1);" +
                        "-fx-border-color: transparent;" +
                        "-fx-text-fill: rgb(160,82,45);" +
                        "-fx-font-size: 16px;" +
                        "-fx-padding: 6 20 6 20;");
            });
            button.setOnMouseExited(event -> {
                button.setStyle("-fx-background-color: transparent;" +
                        "-fx-border-color: transparent;" +
                        "-fx-text-fill: rgb(160,82,45);" +
                        "-fx-font-size: 16px;" +
                        "-fx-padding: 6 20 6 20;");
            });
            button.setOnMouseClicked(event -> {
                MainScreen.callUrl(urlType +"/"+ button.getText());
            });
            info.getChildren().add(button);
        }
        else if (o instanceof List<?>) {
            for (Object data : (ArrayList<?>) o) {
                Button button = new Button((String) data);
                button.setStyle("-fx-background-color: transparent;" +
                        "-fx-border-color: transparent;" +
                        "-fx-text-fill: rgb(160,82,45);" +
                        "-fx-font-size: 16px;" +
                        "-fx-padding: 6 20 6 20;");
                button.setWrapText(true);
                button.setOnMouseEntered(event -> {
                    button.setStyle("-fx-background-color: rgba(160,82,45,0.1);" +
                            "-fx-border-color: transparent;" +
                            "-fx-text-fill: rgb(160,82,45);" +
                            "-fx-font-size: 16px;" +
                            "-fx-padding: 6 20 6 20;");
                });
                button.setOnMouseExited(event -> {
                    button.setStyle("-fx-background-color: transparent;" +
                            "-fx-border-color: transparent;" +
                            "-fx-text-fill: rgb(160,82,45);" +
                            "-fx-font-size: 16px;" +
                            "-fx-padding: 6 20 6 20;");
                });
                button.setOnMouseClicked(event -> {
                    MainScreen.callUrl(urlType +"/"+ button.getText());
                });
                info.getChildren().add(button);
            }
        }
    }
}
