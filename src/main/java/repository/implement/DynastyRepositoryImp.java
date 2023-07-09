package repository.implement;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.DynastyModel;
import repository.DynastyRepository;
import repository.Repository;
import data.util.JsonURL;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DynastyRepositoryImp implements DynastyRepository , Repository {
    public static DynastyRepositoryImp instance;
    private List<DynastyModel>models = new ArrayList<>();

    private DynastyRepositoryImp(){

    }
    public static DynastyRepositoryImp getInstance(){
        if(instance == null) instance = new DynastyRepositoryImp();
        return instance;
    }
    @Override
    public void loadData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            DynastyModel[] dynasties = mapper.readValue(new File(JsonURL.THOI_KY_FILENAME),DynastyModel[].class);
            for(DynastyModel dynasty : dynasties) models.add(dynasty);
        }catch(Exception e){
            e.printStackTrace();;
        }
    }
    @Override
    public List<DynastyModel> getAllDynasty() { return models; }
    @Override
    public DynastyModel getDynastyByName(String name) {
        for(DynastyModel model : models){
            if(model.getName().equals(name))
                return model;
        }
        return null;
    }

    @Override
    public DynastyModel getDynastyByName(String name, boolean isContained) {
        if(isContained){
            for(DynastyModel model : models){
                if(name.contains(model.getName()) && !model.getName().isEmpty())
                    return model;
            }
        }
        return getDynastyByName(name);
    }
}
