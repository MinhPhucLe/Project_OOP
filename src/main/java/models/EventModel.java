package models;

import util.UrlContainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventModel extends BaseModel implements CustomInfo{
    private List<String> relativeSite;
    private List<String> relativeChar;

    private String time;

    public List<String> getRelativeSite() {
        return relativeSite;
    }

    public void setRelativeSite(List<String> relativeSite) {
        this.relativeSite = relativeSite;
    }

    public List<String> getRelativeChar() {
        return relativeChar;
    }

    public void setRelativeChar(List<String> relativeChar) {
        this.relativeChar = relativeChar;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void Events() {};

    public void Events(String name, String time, String description, List<String> relativeChar, List<String> relativeSite) {
        super.name = name;
        super.desc = description;
        this.time = time;
        this.relativeChar = relativeChar;
        this.relativeSite = relativeSite;
    }

    @Override
    public String getUrl() {
        return UrlContainer.SU_KIEN_URL + '/' + name;
    }

    @Override
    public Map<String ,Object> getMapDescription(){
        Map<String,Object> res = new HashMap<>();
        res.put("name",name );
        res.put("desc",desc);
        res.put("time",time);
        res.put("relativeChar",relativeChar);
        res.put("relativeSite",relativeSite);
        return res;
    }
}
