package models;

import util.UrlContainer;

import java.util.*;

public class SiteModel extends BaseModel implements CustomInfo{
    private List<String> relatedEvents;
    private List<String> relatedCharacters;

    public List<String> getRelatedEvents() {
        return relatedEvents;
    }

    public void setRelatedEvents(List<String> relatedEvents) {
        this.relatedEvents = relatedEvents;
    }

    public List<String> getRelatedCharacters() {
        return relatedCharacters;
    }

    public void setRelatedCharacters(List<String> relatedCharacters) {
        this.relatedCharacters = relatedCharacters;
    }

    public SiteModel(String name, String desc, List<String> relatedEvents, List<String> relatedCharacters) {
        super.name = name;
        super.desc = desc;
        this.relatedEvents = relatedEvents;
        this.relatedCharacters = relatedCharacters;
    }

    public SiteModel() {
        super.name = null;
        super.desc = null;
        this.relatedEvents = null;
        this.relatedCharacters = null;
    }

    @Override
    public Map<String, Object> MapDescription() {
        Map<String , Object> res = new HashMap<>();
        res.put("name",name);
        res.put("desc",desc);
        res.put("relatedEvents", relatedEvents);
        res.put("relatedCharacters",relatedCharacters);
        return res;
    }

    @Override
    public String Url() {
        return UrlContainer.DIA_DANH_URL + '/' + name;
    }
}
