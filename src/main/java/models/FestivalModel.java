package models;

import util.UrlContainer;

import java.util.HashMap;
import java.util.Map;

public class FestivalModel extends BaseModel implements CustomInfo{
    private String location;
    private String time;
    public FestivalModel(String name, String time, String location, String description) {
        super.name = name;
        this.time = time;
        this.location = location;
        super.desc = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public FestivalModel() {
        super.name = null;
        super.desc = null;
        this.time = null;
        this.location = null;
    }
    @Override
    public String Url() {
        return UrlContainer.LE_HOI_URL + '/' + name;
    }

    @Override
    public Map<String ,Object> MapDescription(){
        Map<String,Object> res = new HashMap<>();
        res.put("name",name );
        res.put("desc",desc);
        res.put("time",time);
        res.put("location",location);
        return res;
    }
}
