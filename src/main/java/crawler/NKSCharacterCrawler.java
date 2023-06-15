package crawler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import models.Character;
import com.fasterxml.jackson.databind.ObjectMapper;
public class NKSCharacterCrawler implements BaseCrawler{
    private final int NUM_OF_PAGES = 291;
    private final String BASE_URL = "https://nguoikesu.com/";
    public List<String> getAllCharacterURL(){
        List<String> listURLs = new ArrayList<String>();
        for(int page = 0; page < NUM_OF_PAGES; ++page) {
            try {
               Document doc = Jsoup.connect(BASE_URL + "nhan-vat?start=" + Integer.toString(page * 5)).userAgent("Jsoup client").timeout(20000).get();

                Elements elmsA =  doc.select("h2[itemprop=name] a");
                for(Element ref : elmsA){
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

    public boolean containsNumber(String x){
        char[] arr = x.toCharArray();
        for(char c : arr){
            if('0' <= c && c <= '9')
                return true;
        }
       return false;
    }
    public void crawlData(){
        List<String> listURLs = getAllCharacterURL();
        int entitiesCrawled = 0;
        try(Writer writer = new FileWriter("src/main/java/json/nkschar.json")){
            writer.write('[');
            for(String url : listURLs){
                try
                {
                    Document doc = Jsoup.connect(url).userAgent("Jsoup client").timeout(20000).get();
                    Character tempChar = new Character();
                    String name = doc.select("div.page-header h2").text();
                    //System.out.println(name);
                    Elements articleBody = doc.select("div[itemprop=articleBody]");
                    Elements infoBox = articleBody.select("div.infobox");
                    String namSinh = "Không rõ";
                    String namMat = "Không rõ";
                    if(infoBox.size() > 0){
                        Elements infoTable = infoBox.get(0).select("table").get(0).select("tbody tr");
                        for(Element rows : infoTable){
                            Elements col1 = rows.select("th");
                            Elements col2 = rows.select("td");
                            if(col2.size() > 0 && col1.size() > 0){
                                String col1Data = col1.get(0).text();
                                if(col1Data.equals("Sinh"))
                                    namSinh = col2.get(0).text();
                                if(col1Data.equals("Mất"))
                                    namMat = col2.get(0).text();
                            }
                        }
                    }
                    Elements pTags = articleBody.select("p");
                    String data = "";
                    if(pTags.size() > 0)
                        data = pTags.get(0).text();
                    pTags = articleBody.select("p,h2,h3");
                    //System.out.println(data);
                    if(namSinh.equals("Không rõ") || !containsNumber(namSinh)){
                        String reg = "\\(.*sinh.*\\)";
                        String reg1 = "\\(.*-.*\\)";
                        Pattern pattern = Pattern.compile(reg);
                        Matcher match = pattern.matcher(data);
                        //System.out.println(match.group());
                        if(match.find()){
                            String time = match.group();
                            //System.out.println(time);
                            String firstMatch = time.substring(1, 5);
                            //System.out.println(firstMatch);
                            int id = 1;
                            while(!firstMatch.equals("sinh") && id + 4 <= time.length() - 1){
                                id++;
                                firstMatch = time.substring(id, id + 4);
                            }
                            int id1 = 0;
                            //System.out.println(time.length());
                            for(int i = id + 4; i < time.length(); ++i){
                                if(time.charAt(i) == ')' || time.charAt(i) == ','){
                                    id1 = i;
                                    break;
                                }
                            }
                            String birth = time.substring(id + 4, id1);
                            if(time.charAt(id1) == ',')
                            {
                                String ded = time.substring(id1 + 3, time.length() - 1);
                                namMat = ded;
                            }

                            namSinh = birth;

                        }
                        else{
                            Pattern pattern1 = Pattern.compile(reg1);
                            Matcher match1 = pattern1.matcher(data);
                            if(match1.find())
                            {
                                String time = match1.group();
                                //System.out.println(data);
                                String birth = "";
                                String ded = "";
                                int id = 1;
                                while(time.charAt(id) != '-'){
                                    birth += time.charAt(id);
                                    id++;
                                }
                                id++;
                                while(time.charAt(id) != ')'){
                                    ded += time.charAt(id);
                                    id++;
                                }

                                namSinh = birth;
                                namMat = ded;
                            }

                        }
                    }
                    //System.out.println("Sinh: " + namSinh);
                    //System.out.println("Mat: " + namMat);
                    String desc = "";
                    for(Element elems : pTags){
                        desc = desc + "\n" + elems.text();
                    }
                    //System.out.println(desc);
                    tempChar.setName(name);
                    tempChar.setDesc(desc);
                    tempChar.setNamMat((namMat==null?"Không rõ":namMat.equals("?")?"Không rõ":namMat));
                    tempChar.setNamSinh((namSinh==null?"Không rõ":namSinh.equals("?")?"Không rõ":namSinh));
                    //System.out.println(tempChar.toString());
                    entitiesCrawled++;
                    ObjectMapper mapper = new ObjectMapper();
                    System.out.println(mapper.writeValueAsString(tempChar));
                    writer.write(mapper.writeValueAsString(tempChar));
                    writer.write(",\n");
                } catch (IOException err)
                {
                    err.printStackTrace();
                }
            }
            writer.write(']');
        }
        catch (IOException err){
            err.printStackTrace();
        }
    }
}
