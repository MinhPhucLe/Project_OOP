package repository.implement;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.FestivalModel;
import repository.FestivalRepository;
import repository.Repository;
import data.util.JsonURL;

import java.io.File;
import java.util.*;

public class FestivalRepositoryImp implements FestivalRepository , Repository {
    public static FestivalRepositoryImp instance;
    private List<FestivalModel>models = new ArrayList<>();
    private FestivalRepositoryImp(){

    }
    public static FestivalRepositoryImp getInstance(){
        if(instance == null) instance = new FestivalRepositoryImp();
        return instance;
    }
    @Override
    public void loadData(){
        try{
            ObjectMapper mapper = new ObjectMapper();
            FestivalModel[] festivals = mapper.readValue(new File(JsonURL.LE_HOI_FILENAME),FestivalModel[].class);
            for(FestivalModel festival : festivals) models.add(festival);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public List<FestivalModel> getAllFestival() {
        return models;
    }

    @Override
    public FestivalModel getFestivalByName(String name) {
        for(FestivalModel model : models){
            if(model.getName().equals(name))
                return model;
        }
        return null;
    }

    @Override
    public FestivalModel getFestivalByName(String name, boolean isContained) {
        if(isContained){
            for(FestivalModel model : models){
                if(name.contains(model.getName()) && !model.getName().isEmpty())
                    return model;
            }
        }
        return getFestivalByName(name);
    }
}
