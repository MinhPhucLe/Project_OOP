package controller;

import javafx.fxml.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import org.example.MainScreen;

public class ObjectController  {
    private HBox root;
    @FXML
    private Label name;
    public ObjectController(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/object.fxml"));
        loader.setController(this);
        try {
            root = loader.load();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void add(VBox box){
        box.getChildren().add(root);
    }
    public void setName(String s){ name.setText(s); }

    @FXML
    public void showDetail(MouseEvent e ){
        MainScreen.callUrl(MainScreen.getCurrentUrl() + '/' + name);
    }

}
