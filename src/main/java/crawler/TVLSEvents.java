package crawler;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.EventModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class TVLSEvents implements BaseCrawler {

    String baseUrl = "https://thuvienlichsu.vn/su-kien?page=";

    public List<String> getUrls() {
        List<String> tempUrl = new ArrayList<String>();
        for (int i = 1; i <= 19; ++ i) {
            String x = Integer.toString(i);
            String tmp = baseUrl + x;
            try {
                Document doc = Jsoup.connect(tmp).get();
                Elements aTags =  doc.getElementsByClass("divide-content");
                for(Element aTag : aTags){
                    String eventUrl =  aTag.getElementsByTag("a").attr("abs:href");
                    tempUrl.add(eventUrl);
                }
            } catch (IOException err)
            {
                err.printStackTrace();
            }
        }
        return tempUrl;
    }
    @Override
    public void crawlData() {
        List<String> urls = getUrls();
        try (Writer writer = new FileWriter("src/main/java/json/TVLSEvents.json")) {
            writer.write('[');
            for (String url : urls) {
                try {
                    Document doc = Jsoup.connect(url).get();
                    String name;
                    String description = "";
                    String time;
                    List<String> tmpChar = new ArrayList<>();
                    List<String> tmpSite = new ArrayList<>();
                    String tmp = doc.getElementsByTag("h3").first().text();
                    int location = -1;
                    for (int j = 0; j < tmp.length(); ++j) {
                        if (tmp.charAt(j) == '(') {
                            location = j;
                            break;
                        }
                    }
                    if (location == -1) {
                        name = tmp;
                        time = "không rõ";
                    } else {
                        name = tmp.substring(0, location - 1);
                        time = tmp.substring(location + 1, tmp.length() - 1);
                    }
                    Elements h3Tags = doc.getElementsByClass("divide-tag");
                    for (Element h3Tag : h3Tags) {
                        String checkType = h3Tag.getElementsByTag("h3").text();
                        if (checkType.contains("Diễn biễn lịch sử")) {
                            description = h3Tag.getElementsByClass("card-body").text();
                        } else if (checkType.contains("Nhân vật liên quan")) {
                            Elements chars = h3Tag.getElementsByClass("card-body");
                            for (int i = 0; i < chars.size(); ++i) {
                                Element charac = chars.get(i);
                                ///if (i == 0) continue;
                                String tmpchars = "";
                                String tempChar = charac.getElementsByClass("card-title").text();
                                int loca = -1;
                                for (int j = 0; j < tempChar.length(); ++j) {
                                    if (tempChar.charAt(j) == '(') {
                                        loca = j;
                                        break;
                                    }
                                }
                                if (loca != -1) {
                                    tmpchars = tempChar.substring(0, loca - 1);
                                }
                                else tmpchars = tempChar;
                                tmpChar.add(tmpchars);
                            }
                        } else if (checkType.contains("Địa điểm liên quan")) {
                            Elements sites = h3Tag.getElementsByClass("card-body");
                            for (int i = 0; i < sites.size(); ++i) {
                                Element site = sites.get(i);
                                ///if (i == 0) continue;
                                String tmpSites;
                                String tempSite = site.getElementsByClass("card-title").text();
                                int loca = -1;
                                for (int j = 0; j < tempSite.length(); ++j) {
                                    if (tempSite.charAt(j) == '(') {
                                        loca = j;
                                        break;
                                    }
                                }
                                if (loca == -1) {
                                    tmpSites = tempSite;
                                } else {
                                    tmpSites = tempSite.substring(0, loca - 1);
                                }
                                tmpSite.add(tmpSites);
                            }
                        }
                    }
                    EventModel tempEvent = new EventModel();
                    tempEvent.setName(name);
                    tempEvent.setTime(time);
                    tempEvent.setDesc(description);
                    tempEvent.setRelativeChar(tmpChar);
                    tempEvent.setRelativeSite(tmpSite);
                    ObjectMapper mapper = new ObjectMapper();
                    System.out.println(mapper.writeValueAsString(tempEvent));
                    writer.write(mapper.writeValueAsString(tempEvent));
                    writer.write(",\n");
                    ///System.out.println("here: " + name);
                } catch (IOException err) {
                    err.printStackTrace();
                }
            }
            writer.write(']');
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
