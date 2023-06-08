package models;

public abstract class BaseModel {
    protected String name;
    protected String desc;
    public String getName(){
        return name;
    }
    public String getDesc(){
        return desc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
