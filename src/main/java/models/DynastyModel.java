package models;

import util.UrlContainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynastyModel extends BaseModel implements CustomInfo{
    private String time;
    private List<String> kings;
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getKings() {
        return kings;
    }

    public void setKings(List<String> kings) {
        this.kings = kings;
    }

    public DynastyModel(String name, String desc, String time, List<String> kings) {
        super.name = name;
        super.desc = desc;
        this.time = time;
        this.kings = kings;
    }

    public DynastyModel() {
        super.name = null;
        super.desc = null;
        this.time = null;
        this.kings = null;
    }

    @Override
    public String Url() {
        return UrlContainer.THOI_KY_URL + '/' + name;
    }

    @Override
    public Map<String, Object> MapDescription() {
        Map<String,Object> res = new HashMap<>();
        res.put("name",name );
        res.put("desc",desc);
        res.put("time",time);
        res.put("kings",kings);
        return res;
    }
}
