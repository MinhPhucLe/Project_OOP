package repository.implement;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.SiteModel;
import repository.SiteRepository;
import repository.Repository;
import util.JsonURL;

import java.io.File;
import java.util.*;

public class SiteRepositoryImp implements SiteRepository , Repository {
    public static SiteRepositoryImp instance;
    private List<SiteModel>models = new ArrayList<>();

    private SiteRepositoryImp(){

    }
    public static SiteRepositoryImp getInstance(){
        if(instance == null) instance = new SiteRepositoryImp();
        return instance;
    }
    @Override
    public void loadData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SiteModel[] sites = mapper.readValue(new File(JsonURL.DIA_DANH_FILENAME),SiteModel[].class);
            for(SiteModel site : sites) models.add(site);
        }catch(Exception e){
            e.printStackTrace();;
        }
    }
    @Override
    public List<SiteModel> getAllSite() { return models; }
    @Override
    public SiteModel getSiteByName(String name) {
        for(SiteModel model : models){
            if(model.getName().equals(name))
                return model;
        }
        return null;
    }

    @Override
    public SiteModel getSiteByName(String name, boolean isContained) {
        if(isContained){
            for(SiteModel model : models){
                if(name.contains(model.getName()) && !model.getName().isEmpty())
                    return model;
            }
        }
        return getSiteByName(name);
    }
}
