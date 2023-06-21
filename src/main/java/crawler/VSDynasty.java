package crawler;

import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.DynastyModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class VSDynasty implements BaseCrawler {

    public void crawlData() {
        try (Writer writer = new FileWriter("src/main/java/json/VSDynasty.json")) {
            writer.write('[');
            Document doc = Jsoup.connect("https://vansu.vn/viet-nam/nien-bieu-lich-su?fbclid=IwAR2ezEDAtHRKdTgjV5PjFTgJpbiVGFKm44mUF8U0m7piCBk_ZhEm206NTNQ").get();
            Elements links = doc.select("body > div.ui.container > div");
            int dem = 0;
            for (Element link : links) {
                try {
                    dem++;
                    String name = "", reign = "", description = "";
                    String inString = link.getElementsByTag("a").text();
                    int pos = -1;
                    for (int i = 0; i < inString.length(); ++i) {
                        if (inString.charAt(i) == '(') {
                            pos = i;
                            break;
                        }
                    }

                    if (pos == -1) {
                        name = inString;
                        reign = "unknown";
                    } else {
                        name = inString.substring(0, pos);
                        reign = inString.substring(pos + 1, inString.length() - 2);
                    }

                    String url = link.getElementsByTag("a").attr("abs:href");
                    ///System.out.println("Pro: " + url);
                    Document docs = Jsoup.connect(url).get();
                    String des = docs.getElementsByTag("p").text();
                    description = des;
                    Elements h4Tags = docs.getElementsByTag("h4");
                    List<String> kings = new ArrayList<String>();
                    if (dem >= 6) {
                        int tmp = 0;
                        for (Element h4Tag : h4Tags) {
                            tmp++;
                            String king = "";
                            String tmpKing = h4Tag.text();
                            int location = -1;
                            int location_start = -1;
                            for (int i = 0; i < tmpKing.length(); ++i) {
                                if (tmpKing.charAt(i) == '(') {
                                    location = i;
                                    break;
                                }
                            }
                            for (int i = 0; i < tmpKing.length(); ++i) {
                                if (tmpKing.charAt(i) == '.') {
                                    location_start = i;
                                    break;
                                }
                            }
                            if (location_start != -1) {
                                if (location == -1) king = tmpKing.substring(location_start + 1, tmpKing.length() - 1);
                                else {
                                    king = tmpKing.substring(location_start + 1, location - 1);
                                }
                            } else continue;
                            if (dem != 23)
                                kings.add(king);
                            else {
                                tmp++;
                                if (tmp > 9) break;
                                else kings.add(king);
                            }
                        }
                    }
                    DynastyModel tempDy = new DynastyModel();
                    tempDy.setTime(reign);
                    tempDy.setName(name);
                    tempDy.setDesc(description);
                    tempDy.setKings(kings);
                    ObjectMapper mapper = new ObjectMapper();
                    System.out.println(mapper.writeValueAsString(tempDy));
                    writer.write(mapper.writeValueAsString(tempDy));
                    if (dem != 24) writer.write(",");
                    writer.write("\n");
                    System.out.println("Thoi dai thu " + dem + ":");
                    for (int i = 0; i < kings.size(); ++i) {
                        System.out.println("Vua la: " + kings.get(i));
                    }
                    ///System.out.println("Name: "  + name + " - Reign: " + reign + " - Description: " + description);
                /*try {
                    Connection conn = DriverManager.getConnection(dataurl, username, password);
                    String insertQuery = "INSERT INTO dynasty (name, reign, description) VALUES (?, ?, ?)";
                    PreparedStatement statement = conn.prepareStatement(insertQuery);
                    statement.setString(1, name);
                    statement.setString(2, reign);
                    statement.setString(3, description);
                    statement.executeUpdate();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
                } catch (IOException err) {
                    err.printStackTrace();
                }
                System.out.println("Here: " + dem);
            }
            /*String content = readFileContent("src/main/java/json/VSDynasty.json");
            System.out.println("Herre: " + content);
            content = content.substring(0, content.length() - 1);
            writer.flush();
            writer.write(content);*/
            writer.write(']');
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
    private static String readFileContent(String filePath) throws IOException {
        File file = new File(filePath);
        StringBuilder content = new StringBuilder();

        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
        }

        return content.toString();
    }

}


