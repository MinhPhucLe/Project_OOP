package models;

import java.util.List;

public class Sites extends BaseModel{
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

    public Sites(String name, String desc, List<String> relatedEvents, List<String> relatedCharacters) {
        super.name = name;
        super.desc = desc;
        this.relatedEvents = relatedEvents;
        this.relatedCharacters = relatedCharacters;
    }

    public Sites() {
        super.name = null;
        super.desc = null;
        this.relatedEvents = null;
        this.relatedCharacters = null;
    }
}
