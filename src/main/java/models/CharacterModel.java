package models;

import util.UrlContainer;

import java.util.*;

public class CharacterModel extends BaseModel implements CustomInfo{
    private String namSinh;
    private String namMat;
    public CharacterModel(String name, String desc, String namSinh, String namMat){
        super.name = name;
        super.desc = desc;
        this.namSinh = namSinh;
        this.namMat = namMat;
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

    public CharacterModel() {
        super.name = null;
        super.desc = null;
        this.namMat = null;
        this.namSinh = null;
    }

    @Override
    public String toString() {
        return "Character{" +
                "namSinh='" + namSinh + '\'' +
                ", namMat='" + namMat + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    @Override
    public String getUrl(){
        return UrlContainer.NHAN_VAT_URL + '/' +  name;
    }

    @Override
    public Map<String ,Object> getMapDescription(){
        Map<String,Object> res = new HashMap<>();
        res.put("name",name );
        res.put("desc",desc);
        res.put("namSinh" ,namSinh);
        res.put("namMat" , namMat);
        return res;
    }
}
