package models;

import java.util.List;

public class Dynasty extends BaseModel{
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

    public Dynasty(String name, String desc, String time, List<String> kings) {
        super.name = name;
        super.desc = desc;
        this.time = time;
        this.kings = kings;
    }

    public Dynasty() {
        super.name = null;
        super.desc = null;
        this.time = null;
        this.kings = null;
    }
}
