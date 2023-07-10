package crawler;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CombineCrawler {

    private static List<JsonNode> mergeJsonArrays(JsonNode array1, JsonNode array2, JsonNode array3) {
        Set<String> encounteredKeys = new HashSet<>();
        List<JsonNode> mergedArray = new ArrayList<>();

        // Add elements from array1
        for (JsonNode node : array1) {
            String key = node.get("name").asText();
            if (!encounteredKeys.contains(key)) {
                mergedArray.add(node);
                encounteredKeys.add(key);
            }
        }

        // Add elements from array2
        for (JsonNode node : array2) {
            String key = node.get("name").asText();
            if (!encounteredKeys.contains(key)) {
                mergedArray.add(node);
                encounteredKeys.add(key);
            }
        }

        for (JsonNode node : array3) {
            String key = node.get("name").asText();
            if (!encounteredKeys.contains(key)) {
                mergedArray.add(node);
                encounteredKeys.add(key);
            }
        }

        return mergedArray;
    }

    public String getStringFile(String path) throws IOException, ParseException {
        String jsonFile1 = new String(Files.readAllBytes(Paths.get(path)));
        System.out.println("jsonFile 1: " + jsonFile1);
        return jsonFile1;
    }

    public void JsonMerge(String json1, String json2, String json3) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Parse the JSON arrays into JsonNode objects
            JsonNode array1 = objectMapper.readTree(json1);
            JsonNode array2 = objectMapper.readTree(json2);
            JsonNode array3 = objectMapper.readTree(json3);

            // Merge the two arrays while handling duplicates
            List<JsonNode> mergedArray = mergeJsonArrays(array1, array2, array3);

            // Convert the merged array back to JSON
            String mergedJsonArray = objectMapper.writeValueAsString(mergedArray);
            String filePath = "src/main/java/json/characters.json";
            try {
                // Write the new string to the JSON file, overriding its contents
                FileWriter fileWriter = new FileWriter(filePath);
                fileWriter.write(mergedJsonArray);
                fileWriter.close();

                System.out.println("JSON file overridden with the new string successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Merged JSON Array: " + mergedJsonArray);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        CombineCrawler crawler = new CombineCrawler();
        String ob1 = crawler.getStringFile("src/main/java/data/TVLSC.json");
        String ob2 = crawler.getStringFile("src/main/java/data/nkschar.json");
        String ob3 = crawler.getStringFile("src/main/java/data/tvlschar.json");
        ///StringBuilder res =
        crawler.JsonMerge(ob1, ob2, ob3);
        ///System.out.println("here: " + res);
    }
}