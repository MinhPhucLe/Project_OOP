package models;

public class FestivalModel extends BaseModel {
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
}
