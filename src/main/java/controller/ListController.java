package controller;

import javafx.fxml.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import models.*;
import util.UrlContainer;


public class ListController {
    @FXML
    private VBox list;

    public ListController(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/list.fxml"));
        loader.setController(this);
        try{
            list = loader.load();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void add(ScrollPane box){
        box.setContent(list);
    }
    public void addList(String name ){
        ObjectController controller = new ObjectController();
        controller.setTitle(name);
        controller.setName(name);
        controller.add(list);
    }
    public void addList(String name , Object o , String url){
        ObjectController controller = new ObjectController();
        String res = "";
        if(url.contains(UrlContainer.NHAN_VAT_URL)){
            CharacterModel model = (CharacterModel)o;
            res += name + " ( " + model.getNamSinh() + " - " + model.getNamMat() + " ) ";
        }
        else if(url.contains(UrlContainer.SU_KIEN_URL)){
            EventModel model = (EventModel)o;
            res += name +  " ( " + model.getTime() + " ) ";
        }
        else if(url.contains(UrlContainer.THOI_KY_URL)){
            DynastyModel model = (DynastyModel)o;
            res += name +  " ( " + model.getTime() + " ) ";
        }
        else if(url.contains(UrlContainer.LE_HOI_URL)){
            FestivalModel model = (FestivalModel)o;
            res += name +  " ( " + model.getTime() + " ) ";
        }
        controller.setTitle(res);
        controller.setName(name);
        controller.add(list);
    }
    public void resetList(){
        list.getChildren().clear();
    }
}
