package crawler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.FestivalModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLDecoder;

public class WikiEvent implements BaseCrawler{
    public void crawlData() {
        try (Writer writer = new FileWriter("src/main/java/json/WKFestival.json")) {
            writer.write('[');
            int dem = 0;
            try {
                String name = "";
                String time = "";
                String location = "";
                String description = "";
                String baseUrl = "https://vi.wikipedia.org/wiki/";
                String url = baseUrl + "Lễ_hội_Việt_Nam";
                Document doc = Jsoup.connect(url).get();
                Elements elements = doc.select("#mw-content-text > div.mw-parser-output > table.prettytable.wikitable > tbody > tr");
                for (Element element : elements) {
                    dem++;
                    Elements cells = element.select("td");
                    if (cells.size() >= 3) {
                        name = cells.get(2).text();
                        time = cells.get(0).text();
                        location = cells.get(1).text();
                        String title = cells.get(2).getElementsByTag("a").attr("title");
                        if (title.contains("(trang không tồn tại)")) {
                            description = "";
                        } else {
                            String desc = "";
                            String link = cells.get(2).getElementsByTag("a").attr("abs:href");
                            link = URLDecoder.decode(link, "UTF-8");
                            System.out.println("Here: " + link);
                            Document dsc = Jsoup.connect(link).get();
                            Elements firstH2s = dsc.select("body > div.mw-page-container > div > div.mw-content-container");
                            ///System.out.println("Sad: " + firstH2.text());
                            Element pTag = firstH2s.first().selectFirst("p");
                            desc = pTag.text();
                            description = desc;
                        }
                    }
                    FestivalModel tempFes = new FestivalModel();
                    if (name != "") {
                        tempFes.setName(name);
                        tempFes.setTime(time);
                        tempFes.setLocation(location);
                        tempFes.setDesc(description);
                    } else continue;
                ObjectMapper mapper = new ObjectMapper();
                System.out.println(mapper.writeValueAsString(tempFes));
                writer.write(mapper.writeValueAsString(tempFes));
                if (dem < elements.size()) writer.write(",");
                writer.write("\n");
                    System.out.println("Name: " + name + ", Time: " + time + ", location: " + location + ", description: " + description);
                }
            } catch (IOException err) {
                err.printStackTrace();
            }
            writer.write(']');
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
