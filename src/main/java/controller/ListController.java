package controller;

import javafx.application.Platform;
import javafx.fxml.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import org.example.MainScreen;
import util.UrlContainer;

import java.io.IOException;

public class ListController {
    @FXML
    private VBox root;

    public ListController(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/list.fxml"));
        loader.setController(this);
        try{
            root = loader.load();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void add(ScrollPane box){
        box.setContent(root);
    }
    public void addList(String name ){
        ObjectController controller = new ObjectController();
        controller.setName(name);
        controller.add(root);
    }
    public void resetList(){
        root.getChildren().clear();
    }
}
