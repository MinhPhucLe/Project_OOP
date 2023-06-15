package models;

import java.util.List;

public class Events extends BaseModel{
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
}
