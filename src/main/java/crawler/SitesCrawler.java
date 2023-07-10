package crawler;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import models.SiteModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.fasterxml.jackson.databind.ObjectMapper;
public class SitesCrawler implements BaseCrawler{
    private final int NUM_OF_PAGES = 10;
    private final String BASE_URL = "https://thuvienlichsu.vn";
    private final String SKLQ = "Sự kiện liên quan";
    private final String NVLQ = "Nhân vật liên quan";
    public List<String> getAllSitesURL(){
        List<String> listURLs = new ArrayList<String>();
        for(int page = 1; page <= NUM_OF_PAGES; page++){
            try{
                Document doc = Jsoup.connect(BASE_URL + "/dia-diem?page=" + Integer.toString(page)).userAgent("Jsoup client").timeout(20000).get();
                Elements elmA = doc.select("a.click");
                for(Element ref : elmA){
                    String sitesURL = BASE_URL + ref.attr("href");
                    System.out.println(ref.attr("href"));
                    listURLs.add(sitesURL);
                }
            }
            catch (IOException err){
                err.printStackTrace();
            }
        }
        return listURLs;
    }
    public void crawlData(){
        List<String> listURLs = getAllSitesURL();
        try(Writer writer = new FileWriter("src/main/java/data/sites.json")){
            writer.write('[');
            int dem = 0;
            for(String url : listURLs){
                try {
                    dem++;
                    Document doc = Jsoup.connect(url).userAgent("Jsoup client").timeout(20000).get();
                    String name = doc.select("h3").get(0).text();
                    String desc = doc.select("div.card-body").get(1).text();
                    List<String> relatedEvent = new ArrayList<String>();
                    List<String> relatedCharacter = new ArrayList<String>();
                   // System.out.println(name);
                    //System.out.println(desc);
                    Elements divideTags = doc.select("div.divide-tag");
                    for(int bodyID = 0; bodyID < divideTags.size(); ++bodyID){
                        Elements headerEdge = divideTags.get(bodyID).select("h3.header-edge");
                        switch (headerEdge.text()){
                            case SKLQ:
                            {
                                Elements h4Elms = divideTags.get(bodyID).select("h4.card-title");
                                //System.out.println(divideTags.get(bodyID));
                               // System.out.println(SKLQ);
                                for(int id = 0 ; id < h4Elms.size(); ++id){
                                    relatedEvent.add(h4Elms.get(id).text());
                                    //System.out.println(h4Elms.get(id).text());
                                }
                                break;
                            }
                            case NVLQ:
                            {
                                Elements h4Elms = divideTags.get(bodyID).select("h4.card-title");
                                //System.out.println(divideTags.get(bodyID));
                                //System.out.println(NVLQ);
                                for(Element relatedC : h4Elms){
                                    relatedCharacter.add(relatedC.text());
                                    //System.out.println(relatedC.text());
                                }
                                break;
                            }
                        }

                    }
                    SiteModel tempSites = new SiteModel(name, desc, relatedEvent, relatedCharacter);
                    ObjectMapper mapper = new ObjectMapper();
                    System.out.println(mapper.writeValueAsString(tempSites));
                    writer.write(mapper.writeValueAsString(tempSites));
                    if (dem < listURLs.size()) writer.write(",");
                    writer.write("\n");

                }catch(IOException err){
                    err.printStackTrace();
                }

            }
            writer.write(']');
        }catch (IOException err){
            err.printStackTrace();
        }
    }
}
