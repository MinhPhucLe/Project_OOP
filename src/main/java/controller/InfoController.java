package controller;

import javafx.fxml.*;
import javafx.scene.Cursor;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import models.*;
import org.example.MainScreen;
import repository.*;
import repository.implement.*;

import util.UrlContainer;

import java.util.ArrayList;
import java.util.List;


public class InfoController {
    @FXML
    private Label title;
    @FXML
    private VBox info;
    private CharacterRepository characterRepository = CharacterRepositoryImp.getInstance();
    private DynastyRepository dynastyRepository = DynastyRepositoryImp.getInstance();
    private EventRepository eventRepository = EventRepositoryImp.getInstance();
    private SiteRepository siteRepository = SiteRepositoryImp.getInstance();
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
    public Button anchor(String name ,String urlType){
        Button button = new Button(name.trim());
        String url = "", new_name = "";
        button.setCursor(Cursor.HAND);
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
        if(urlType.contains(UrlContainer.NHAN_VAT_URL)){
            CharacterModel model = characterRepository.getCharacterByName(name,true);
            if(model != null){
                url = model.Url();
                new_name = model.getName();
            }
        }else if(urlType.contains(UrlContainer.SU_KIEN_URL)){
            EventModel model = eventRepository.getEventByName(name,true);
            if(model != null){
                url = model.Url();
                new_name = model.getName();
            }
        }else if(urlType.contains(UrlContainer.DIA_DANH_URL)){
            SiteModel model = siteRepository.getSiteByName(name,true);
            if(model != null){
                url = model.Url();
                new_name = model.getName();
            }
        }else if(urlType.contains(UrlContainer.THOI_KY_URL)){
            DynastyModel model = dynastyRepository.getDynastyByName(name,true);
            if(model != null){
                url = model.Url();
                new_name = model.getName();
            }
        } else  if(urlType.contains(UrlContainer.LE_HOI_URL)){
            DynastyModel model = dynastyRepository.getDynastyByName(name,true);
            if(model != null){
                url = model.Url();
                new_name = model.getName();
            }
        }
        if(!new_name.isEmpty()) button.setText(new_name);
        if(!url.isEmpty()) {
            String finalUrl = url;
            button.setOnMouseClicked(event -> {
                MainScreen.callUrl(finalUrl);
            });
        }
        return button;
    }
    public void setValue(Object o, String urlType){
        if(o instanceof String)  info.getChildren().add(anchor((String)o,urlType));
        else if (o instanceof List<?>) {
            for (Object data : (ArrayList<?>) o) info.getChildren().add(anchor((String)data,urlType));
        }
    }
}
