package controller;

import javafx.fxml.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import org.example.MainScreen;

public class ObjectController  {
    @FXML
    private Button root;

    private String name;
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
    public void setTitle(String s){ root.setText(s); }
    public void setName(String name){
        this.name = name;
    }

    @FXML
    public void showDetail(ActionEvent e ){
        MainScreen.callUrl(MainScreen.getCurrentUrl() + '/' + name);
    }

}
