package controller;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import models.*;
import org.example.MainScreen;
import service.CharacterService;

import service.*;
import service.implement.*;
import util.UrlContainer;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class CustomListController {
    @FXML
    private Label title;
    @FXML
    private Label amount;
    @FXML
    private TextField searchTf;
    @FXML
    private BorderPane root;
    @FXML
    private ScrollPane box;
    @FXML
    private ChoiceBox<String> option;
    private String url;
    private String[] characterOption = { "By name" };
    private String[] dynastyOption = { "By name"};
    private String[] eventOption = { "By name"};
    private String[] siteOption = { "By name"};
    private CharacterService characterService = CharacterServiceImp.getInstance();
    private DynastyService dynastyService = DynastyServiceImp.getInstance();
    private EventService eventService = EventServiceImp.getInstance();
    private SiteService siteService = SiteServiceImp.getInstance();
    public void add(VBox box){
        box.getChildren().add(root);
    }

    public void addList(){
        ListController controller = new ListController();
        controller.resetList();
        controller.add(box);
        int num_model = 0;
        if(url.equals(UrlContainer.NHAN_VAT_URL)) {
            List<CharacterModel>models = characterService.getAllCharacter();
            sortListCharacter(models, option.getValue());
            for (CharacterModel model : models){
                if(model.getName().contains(searchTf.getText()) && !model.getName().isBlank()){
                    controller.addList(model.getName());
                    num_model++;
                }
            }
        } else if(url.equals(UrlContainer.THOI_KY_URL)){
            List<DynastyModel>models = dynastyService.getAllDynasty();
            sortListDynasty(models,option.getValue());
            for (DynastyModel model : models){
                if(model.getName().contains(searchTf.getText()) && !model.getName().isBlank()){
                    controller.addList(model.getName());
                    num_model++;
                }
            }
        }
        else if(url.equals(UrlContainer.SU_KIEN_URL)){
            List<EventModel>models = eventService.getALlEvent();
            sortListEvent(models , option.getValue());
            for (EventModel model : models){
                if(model.getName().contains(searchTf.getText()) && !model.getName().isBlank()){
                    controller.addList(model.getName());
                    num_model++;
                }
            }
        }else if(url.equals(UrlContainer.DIA_DANH_URL)){
            List<SiteModel>models = siteService.getAllSite();
            sortListSite(models,option.getValue());
            for (SiteModel model : models){
                if(model.getName().contains(searchTf.getText()) && !model.getName().isBlank()){
                    controller.addList(model.getName());
                    num_model++;
                }
            }
        }else if(url.equals(UrlContainer.LE_HOI_URL)){

        }
        setAmount(num_model);
    }
    public void sortListCharacter(List<CharacterModel>model , String sortOption ){
        if(sortOption.equals("By name")) {
            Collections.sort(model, new Comparator<CharacterModel>() {
                @Override
                public int compare(CharacterModel o1, CharacterModel o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }
    }
    public void sortListDynasty(List<DynastyModel>model , String sortOption ){
        if(sortOption.equals("By name")) {
            Collections.sort(model, new Comparator<DynastyModel>() {
                @Override
                public int compare(DynastyModel o1, DynastyModel o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }
    }
    public void sortListEvent(List<EventModel>model , String sortOption ){
        if(sortOption.equals("By name")) {
            Collections.sort(model, new Comparator<EventModel>() {
                @Override
                public int compare(EventModel o1, EventModel o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }
    }
    public void sortListSite(List<SiteModel>model , String sortOption){
        if(sortOption.equals("By name")) {
            Collections.sort(model, new Comparator<SiteModel>() {
                @Override
                public int compare(SiteModel o1, SiteModel o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }
    }
    public CustomListController(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/customList.fxml"));
        loader.setController(this);
        try {
            root = loader.load();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void goBack(MouseEvent event) throws IOException {
        MainScreen.back();
    }
    @FXML
    public void goHome(MouseEvent event) throws IOException {
        MainScreen.callUrl(UrlContainer.HOME_URL);
    }
    @FXML
    public void search(KeyEvent event){
        addList();
    }
    @FXML
    public void getSortedList(ActionEvent event){
        addList();
    }
    public void setTitle(String s){
        title.setText(s);
    }
    public void setSearchTf(String s){
        searchTf.setText(s);
    }
    public void setAmount(int num){
        amount.setText("( " + num + " results )");
    }
    public void setUrl(String url){
        this.url = url;
    }
    public void setOption(){
        option.setValue("By name");
        if(url.equals(UrlContainer.NHAN_VAT_URL)) option.getItems().addAll(characterOption);
        else if(url.equals(UrlContainer.THOI_KY_URL)) option.getItems().addAll(dynastyOption);
        else if(url.equals(UrlContainer.SU_KIEN_URL)) option.getItems().addAll(eventOption);
        else if(url.equals(UrlContainer.DIA_DANH_URL)) option.getItems().addAll(siteOption);
        option.setOnAction(this::getSortedList);
    }
}
