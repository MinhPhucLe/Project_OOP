package controller;
import javafx.fxml.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import org.example.MainScreen;

import java.io.IOException;

public class DetailController {
    private ScrollPane root;
    @FXML
    private Label name;
    @FXML
    public void pressBackBtn(ActionEvent event) throws IOException {
        MainScreen.back();
    }

    public DetailController(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/detail.fxml"));
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
}
