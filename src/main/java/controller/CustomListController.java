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
import java.util.*;
import java.text.Normalizer;

public class CustomListController {
    @FXML
    private Label title;
    @FXML
    private Label amount;
    @FXML
    private TextField searchTf;
    @FXML
    private BorderPane pane;
    @FXML
    private ScrollPane box;
    @FXML
    private ChoiceBox<String> option;
    private String url;
    private boolean show = false;
    private String[] characterOption = { "By name" };
    private String[] dynastyOption = { "By name"};
    private String[] eventOption = { "By name"};
    private String[] siteOption = { "By name"};
    private String[] festivalOption = { "By name"};
    private CharacterService characterService = CharacterServiceImp.getInstance();
    private DynastyService dynastyService = DynastyServiceImp.getInstance();
    private EventService eventService = EventServiceImp.getInstance();
    private SiteService siteService = SiteServiceImp.getInstance();
    private FestivalService festivalService = FestivalServiceImp.getInstance();

    List<CharacterModel>characterModels ;
    List<DynastyModel>dynastyModels;
    List<SiteModel>siteModels;
    List<EventModel>eventModels;
    List<FestivalModel>festivalModels;

    public void add(VBox box){
        box.getChildren().add(pane);
    }

    public String Normalization(String s){
        return Normalizer.normalize(s,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase().replaceAll("\\s{2,}", " ").trim();
    }
    public void generateList(){
        if(url.contains(UrlContainer.NHAN_VAT_URL)) characterModels = characterService.getAllCharacter();
        else if(url.contains(UrlContainer.THOI_KY_URL)) dynastyModels = dynastyService.getAllDynasty();
        else if(url.contains(UrlContainer.SU_KIEN_URL)) eventModels = eventService.getALlEvent();
        else if(url.contains(UrlContainer.DIA_DANH_URL)) siteModels = siteService.getAllSite();
        else if(url.contains(UrlContainer.LE_HOI_URL)) festivalModels = festivalService.getAllFestival();
    }
    public void addList(){
        ListController controller = new ListController();
        controller.add(box);
        int num_model = 0;
        if(url.equals(UrlContainer.NHAN_VAT_URL)) {
            sortListCharacter(characterModels, option.getValue());
            for (CharacterModel model : characterModels){
                if(Normalization(model.getName()).contains(Normalization(searchTf.getText())) && !model.getName().isBlank()){
                    if(show) controller.addList(model.getName(),model,url);
                    else controller.addList(model.getName());
                    num_model++;
                }
            }
        } else if(url.equals(UrlContainer.THOI_KY_URL)){
            sortListDynasty(dynastyModels,option.getValue());
            for (DynastyModel model : dynastyModels){
                if(Normalization(model.getName()).contains(Normalization(searchTf.getText())) && !model.getName().isBlank()){
                    if(show) controller.addList(model.getName(),model,url);
                    else controller.addList(model.getName());
                    num_model++;
                }
            }
        }
        else if(url.equals(UrlContainer.SU_KIEN_URL)){
            sortListEvent(eventModels , option.getValue());
            for (EventModel model : eventModels){
                if(Normalization(model.getName()).contains(Normalization(searchTf.getText())) && !model.getName().isBlank()){
                    if(show) controller.addList(model.getName() , model,url);
                    else controller.addList(model.getName());
                    num_model++;
                }
            }
        }else if(url.equals(UrlContainer.DIA_DANH_URL)){
            sortListSite(siteModels,option.getValue());
            for (SiteModel model : siteModels){
                if(Normalization(model.getName()).contains(Normalization(searchTf.getText())) && !model.getName().isBlank()){
                    controller.addList(model.getName());
                    num_model++;
                }
            }
        }else if(url.equals(UrlContainer.LE_HOI_URL)){
            sortListFestival(festivalModels,option.getValue());
            for (FestivalModel model : festivalModels){
                if(Normalization(model.getName()).contains(Normalization(searchTf.getText())) && !model.getName().isBlank()){
                    if(show) controller.addList(model.getName() , model,url);
                    else controller.addList(model.getName());
                    num_model++;
                }
            }
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
    public void sortListFestival(List<FestivalModel>model , String sortOption){
        if(sortOption.equals("By name")){
            Collections.sort(model, new Comparator<FestivalModel>() {
                @Override
                public int compare(FestivalModel o1, FestivalModel o2) { return o1.getName().compareTo(o2.getName()); }
            });
        }
    }
    public CustomListController(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/customList.fxml"));
        loader.setController(this);
        try {
            pane = loader.load();
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
    @FXML
    public void showTime(ActionEvent event) {
        show = !show;
        addList();
    }
    public void setTitle(String s){
        title.setText(s);
    }
    public void setAmount(int num){
        if(num > 1) amount.setText("( " + num + " results )");
        else amount.setText("( " + num + " result )");
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
        else if(url.equals(UrlContainer.LE_HOI_URL)) option.getItems().addAll(festivalOption);
        option.setOnAction(this::getSortedList);
    }
}
