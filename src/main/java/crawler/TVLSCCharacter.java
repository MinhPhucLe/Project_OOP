package crawler;

import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import crawler.BaseCrawler;
import models.CharacterModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.Writer;

public class TVLSCCharacter implements BaseCrawler {
    /*String dataurl = "jdbc:mysql://localhost:3306/java";
    String username = "root";
    String password = "123";
    String baseUrl = "https://thuvienlichsu.com/category/nhan-vat/";*/

    public void crawlData() {
        try (Writer writer = new FileWriter("src/main/java/json/TVLSC.json")) {
            writer.write('[');
            for (int i = 1; i <= 42; ++i) {
                try {
                    Document doc;
                    if (i == 1) {
                        doc = Jsoup.connect("https://thuvienlichsu.com/category/nhan-vat/").get();
                    } else {
                        String x = Integer.toString(i);
                        doc = Jsoup.connect("https://thuvienlichsu.com/category/nhan-vat/page/" + x + "/").get();
                        ///System.out.println("https://thuvienlichsu.com/category/nhan-vat/page/" + x + "/");
                    }
                    Elements sames = doc.select("#content > div > div > div.col-lg-8 > div");
                    for (Element same : sames) {
                        Elements links = same.getElementsByClass("col-md-12 fadeInDown wow");
                        for (Element link : links) {
                            String url = link.getElementsByTag("a").attr("href");
                            Document docs = Jsoup.connect(url).get();
                            Elements frsts = docs.select("#content > div > div > div > div.bs-blog-post.single > div > h1 > a");
                            String ten = "", namSinh = "", namMat = "", description = "";
                            for (Element frst : frsts) {
                                String firstline = frst.getElementsByTag("a").text();
                                if (firstline.substring(0, 8).equals("Chi tiết")) continue;
                                ///System.out.println("Here: " + firstline);
                                String check = firstline.substring(0, 11);
                                int pos = -1;
                                int pos_line = -1;
                                for (int j = 0; j < firstline.length(); ++j) {
                                    if (firstline.charAt(j) == '(') {
                                        pos = j;
                                        break;
                                    }
                                }
                                for (int k = 0; k < firstline.length(); ++k) {
                                    if (firstline.charAt(k) == '–') {
                                        pos_line = k;
                                    }
                                }
                                ///System.out.println("Length: " + firstline.length() + " - Pos: " + pos + " - Pos_line: " + pos_line);
                                if (check.equals("Tiểu sử của")) {
                                    if (pos != -1 && pos_line != -1)
                                        ten = firstline.substring(12, pos - 1);
                                    else if (pos == -1 && pos_line == -1)
                                        ten = firstline.substring(12, firstline.length());
                                } else {
                                    if (pos != -1 && pos_line != -1)
                                        ten = firstline.substring(0, pos - 1);
                                    else if (pos == -1 && pos_line == -1)
                                        ten = firstline.substring(0, firstline.length());
                                }
                                if ((firstline.charAt(pos + 1) == '?' && firstline.charAt(firstline.length() - 2) == '?') || (pos == -1)) {
                                    namSinh = "?";
                                    namMat = "?";
                                } else {
                                    namSinh = firstline.substring(pos + 1, pos_line - 1);
                                    namMat = firstline.substring(pos_line + 1, firstline.length() - 1);
                                }
                                namMat = namMat.replaceAll(" ", "");
                                namSinh = namSinh.replaceAll(" ", "");
                                if (namMat.equals("?")) namMat = "không rõ";
                                if (namSinh.equals("?")) namSinh = "không rõ";
                                ///System.out.println("Ten: " + ten + " - namSinh: " + namSinh + " - namMat: " + namMat);
                            }
                            Elements h2Tags = docs.select("#content > div > div > div > div.bs-blog-post.single > article > h2");
                            ///System.out.println("Heheheheheh: " + h2Tags.size());
                            Element startHeading = null;
                            Element endHeading = null;
                            for (Element h2Tag : h2Tags) {
                                String ktText = h2Tag.getElementsByTag("h2").text();
                                String toCheck = "Thân thế và sự nghiệp của";
                                ///System.out.println("HEREEEEEEEEEEEEEEEEE: " + ktText);
                                if (ktText.contains(toCheck)) {
                                    startHeading = h2Tag;
                                    ///System.out.println("Yesssssssssssssssssss");
                                    for (Element tmp : h2Tags) {
                                        if (tmp.text().equals("Tài liệu tham khảo")) {
                                            endHeading = tmp;
                                            break;
                                        }
                                    }
                                    if (startHeading != null && endHeading != null) {
                                        Element nextElement = startHeading.nextElementSibling();
                                        while (nextElement != null && nextElement != endHeading) {
                                            if (nextElement.tagName().equals("p")) {
                                                String tagP = nextElement.text();
                                                description = description.concat(tagP);
                                            }
                                            nextElement = nextElement.nextElementSibling();
                                        }
                                    }
                                }
                            }
                            CharacterModel tempChar = new CharacterModel();
                            tempChar.setNamSinh(namSinh);
                            tempChar.setNamMat(namMat);
                            tempChar.setName(ten);
                            tempChar.setDesc(description);
                        /*try {
                            Connection conn = DriverManager.getConnection(dataurl, username, password);
                            String insertQuery = "INSERT INTO person (name, year, last_year, description) VALUES (?, ?, ?, ?)";
                            PreparedStatement statement = conn.prepareStatement(insertQuery);
                            statement.setString(1, ten);
                            statement.setString(2, namSinh);
                            statement.setString(3, namMat);
                            statement.setString(4, description);
                            if (!ten.equals("") && !namMat.equals("") && !namSinh.equals("") && !description.equals(""))
                                statement.executeUpdate();
                            conn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }*/

                            ObjectMapper mapper = new ObjectMapper();
                            System.out.println(mapper.writeValueAsString(tempChar));
                            writer.write(mapper.writeValueAsString(tempChar));
                            writer.write(",\n");

                        }
                    }
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