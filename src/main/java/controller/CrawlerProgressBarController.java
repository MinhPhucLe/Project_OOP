package controller;



import java.io.IOException;
import java.math.BigDecimal;

import java.net.URL;

import java.util.ResourceBundle;

import crawler.*;
import javafx.beans.value.ChangeListener;

import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.control.ProgressBar;

import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import org.json.simple.parser.ParseException;


public class CrawlerProgressBarController implements Initializable{
    @FXML
    private ProgressBar myProgressBar;
    @FXML
    private Label myLabel;
    @FXML
    private Button myButton;
    //The BigDecimal class gives its user complete control over rounding behavior
    BigDecimal progress = new BigDecimal(String.format("%.2f", 0.0));
    int count = 0;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        myProgressBar.setStyle("-fx-accent: #00FF00;");

    }



    public void increaseProgress() throws IOException, ParseException {
        //zmyButton.setDisable(true);
        switch(count){
            case 0:{
                TVLSCharacterCrawler crawl1 = new TVLSCharacterCrawler();
                myLabel.setText("Crawling Characters from thuvienlichsu.vn");
                crawl1.crawlData();
                progress = new BigDecimal(String.format("%.2f", progress.doubleValue() + 1.0 / 7));
                myProgressBar.setProgress(progress.doubleValue());
                count++;
                increaseProgress();
                break;
            }
            case 1:{
                TVLSCCharacter crawl2 = new TVLSCCharacter();
                myLabel.setText("Crawling Characters from thuvienlichsu.com");
                crawl2.crawlData();
                progress = new BigDecimal(String.format("%.2f", progress.doubleValue() + 1.0 / 7));
                myProgressBar.setProgress(progress.doubleValue());
                count++;
                increaseProgress();
                break;
            }
            case 2:{
                NKSCharacterCrawler crawl6 = new NKSCharacterCrawler();
                myLabel.setText("Crawling Characters from nguoikesu.com");
                crawl6.crawlData();
                progress = new BigDecimal(String.format("%.2f", progress.doubleValue() + 1.0 / 7));
                myProgressBar.setProgress(progress.doubleValue());
                count++;
                increaseProgress();
                break;
            }
            case 3:{
                SitesCrawler crawl = new SitesCrawler();
                myLabel.setText("Crawling Sites from thuvienlichsu.vn");
                crawl.crawlData();
                progress = new BigDecimal(String.format("%.2f", progress.doubleValue() + 1.0 / 7));
                myProgressBar.setProgress(progress.doubleValue());
                count++;
                increaseProgress();
                break;
            }
            case 4:{
                VSDynasty crawl3 = new VSDynasty();
                myLabel.setText("Crawling Dynasty from Vansu.vn");
                crawl3.crawlData();
                progress = new BigDecimal(String.format("%.2f", progress.doubleValue() + 1.0 / 7));
                myProgressBar.setProgress(progress.doubleValue());
                count++;
                increaseProgress();
                break;
            }
            case 5:{
                TVLSEvents crawl4 = new TVLSEvents();
                myLabel.setText("Crawling Events from thuvienlichsu.vn");
                crawl4.crawlData();
                progress = new BigDecimal(String.format("%.2f", progress.doubleValue() + 1.0 / 7));
                myProgressBar.setProgress(progress.doubleValue());
                count++;
                increaseProgress();
                break;
            }
            case 6:{
                WikiEvent s1 = new WikiEvent();
                myLabel.setText("Crawling Festivals from wikipedia.com");
                s1.crawlData();
                progress = new BigDecimal(String.format("%.2f", progress.doubleValue() + 1.0 / 7));
                myProgressBar.setProgress(progress.doubleValue());
                count++;
                increaseProgress();
                break;
            }
            case 7:{
                CombineCrawler crawler = new CombineCrawler();
                String ob1 = crawler.getStringFile("src/main/java/data/TVLSC.json");
                String ob2 = crawler.getStringFile("src/main/java/data/nkschar.json");
                String ob3 = crawler.getStringFile("src/main/java/data/tvlschar.json");
                ///StringBuilder res =
                crawler.JsonMerge(ob1, ob2, ob3);
                count++;
                increaseProgress();
                break;
            }
            case 8:{
                myLabel.setText("DONE!");
                break;
            }
        }
    }

}