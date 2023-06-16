package crawler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import models.CharacterModel;
import com.fasterxml.jackson.databind.ObjectMapper;
public class TVLSCharacterCrawler implements BaseCrawler {
    private final int NUM_OF_PAGES = 41;
    private final String BASE_URL = "https://thuvienlichsu.vn";

    public List<String> getAllCharacterURL(){
        List<String> listURLs = new ArrayList<String>();
        for(int page = 1; page <= NUM_OF_PAGES; ++page) {
            try {
                Document doc = Jsoup.connect(BASE_URL + "/nhan-vat?page=" + Integer.toString(page)).userAgent("Jsoup client").timeout(20000).get();

                Elements elmsA =  doc.select("div.card-body a.click");
                for(Element ref : elmsA){
                    System.out.println(ref.attr("href"));
                    String characterURL = BASE_URL + ref.attr("href");
                    listURLs.add(characterURL);
                }
            } catch (IOException err)
            {
                err.printStackTrace();
            }
        }
        return listURLs;
    }
    public void crawlData() {
        List<String> listURLs = getAllCharacterURL();
        int entitiesCrawled = 0;
        CharacterModel tempChar = new CharacterModel();
        try (Writer writer = new FileWriter("src/main/java/json/tvlschar.json")) {
            writer.write('[');
            for (String url : listURLs) {
                Document doc = Jsoup.connect(url).userAgent("Jsoup client").timeout(20000).get();
                String name = "";
                String namSinh = "";
                String namMat = "";
                String desc = "";
                String nameAndTime = doc.select("h3").get(0).text();
                int id = 0;
                while (id + 1 <= nameAndTime.length() - 1 && nameAndTime.charAt(id + 1) != '(') {
                    name += nameAndTime.charAt(id);
                    id++;
                }
                //System.out.println(name);
                if(id + 2 <= nameAndTime.length() - 1) {
                    id = id + 2;
                    while (id + 1 <= nameAndTime.length() - 1 && nameAndTime.charAt(id + 1) != '-') {
                        namSinh += nameAndTime.charAt(id);
                        id++;
                    }
                    //System.out.println(namSinh);
                    id = id + 3;
                    while (id <= nameAndTime.length() - 1 && nameAndTime.charAt(id) != ')') {
                        namMat += nameAndTime.charAt(id);
                        id++;
                    }
                    //System.out.println(namMat);
                }
                else {
                    name += nameAndTime.charAt(id);
                    //System.out.println(name);
                }
                Elements pTags = doc.select("p.card-text");
                for(Element pText : pTags){
                    desc += pText.text();
                }
                //System.out.println(desc);
                tempChar.setName(name);
                tempChar.setDesc(desc);
                tempChar.setNamMat((namMat==""?"Không rõ":namMat.equals("?")?"Không rõ":namMat));
                tempChar.setNamSinh((namSinh==""?"Không rõ":namSinh.equals("?")?"Không rõ":namSinh));
                entitiesCrawled++;
                ObjectMapper mapper = new ObjectMapper();
                System.out.println(mapper.writeValueAsString(tempChar));
                writer.write(mapper.writeValueAsString(tempChar));
                writer.write(",\n");
            }
            writer.write(']');
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
