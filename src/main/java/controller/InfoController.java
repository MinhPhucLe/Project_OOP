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

    public void setValue(Object o){

        if(o instanceof String){
            Label value = new Label((String)o);
            value.setWrapText(true);
            value.setStyle("-fx-text-fill: rgb(160,82,45);\n" +
                    "    -fx-font-size: 16px;\n" +
                    "    -fx-padding: 10 20 10 20;");
            info.getChildren().add(value);
        }
        else if(o instanceof List<?>){
            for(Object data : (ArrayList)o){
                Label value = new Label((String)data);
                value.setStyle("-fx-text-fill: rgb(160,82,45);\n" +
                        "    -fx-font-size: 16px;\n" +
                        "-fx-padding: 6 20 6 20");
                value.setWrapText(true);
                info.getChildren().add(value);
            }
        }
    }
}
