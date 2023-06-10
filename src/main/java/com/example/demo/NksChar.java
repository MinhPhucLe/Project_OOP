package com.example.demo;

public class NksChar {
    private String name;
    private String desc;
    private String namSinh;
    private String namMat;

    public NksChar(String name, String desc, String namSinh, String namMat) {
        this.name = name;
        this.desc = desc;
        this.namSinh = namSinh;
        this.namMat = namMat;
    }

    public NksChar() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getNamMat() {
        return namMat;
    }

    public void setNamMat(String namMat) {
        this.namMat = namMat;
    }
}
