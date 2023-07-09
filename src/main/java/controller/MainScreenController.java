package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import gui.MainScreen;
import data.util.UrlContainer;

public class MainScreenController {
    private BorderPane box;

    public MainScreenController(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main.fxml"));
        loader.setController(this);
        try {
            box = loader.load();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void add(VBox layout){
        layout.getChildren().add(box);
    }

    @FXML
    public void NhanVatLichSu(ActionEvent e){
        loadPage(UrlContainer.NHAN_VAT_URL);
    }
    @FXML
    public void ThoiKyLichSu(ActionEvent e){
        loadPage(UrlContainer.THOI_KY_URL);
    }
    @FXML
    public void SuKienLichSu(ActionEvent e){
        loadPage(UrlContainer.SU_KIEN_URL);
    }
    @FXML
    public void DiaDiemLichSu(ActionEvent e){
        loadPage(UrlContainer.DIA_DANH_URL);
    }
    @FXML
    public void LeHoiVanHoa(ActionEvent e){
        loadPage(UrlContainer.LE_HOI_URL);
    }
    public void loadPage(String url){
        MainScreen.callUrl(url);
    }
}
