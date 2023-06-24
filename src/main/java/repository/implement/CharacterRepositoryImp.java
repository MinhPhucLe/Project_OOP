package repository.implement;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.CharacterModel;
import repository.CharacterRepository;
import repository.Repository;
import util.JsonURL;

import java.io.File;
import java.util.*;

public class CharacterRepositoryImp implements CharacterRepository , Repository {
    public static CharacterRepositoryImp instance;
    private List<CharacterModel>models = new ArrayList<>();

    private CharacterRepositoryImp(){

    }
    public static CharacterRepositoryImp getInstance(){
        if(instance == null) instance = new CharacterRepositoryImp();
        return instance;
    }
    @Override
    public void loadData(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            CharacterModel[] characters = mapper.readValue(new File(JsonURL.NHAN_VAT_FILENAME), CharacterModel[].class);
            for(CharacterModel character : characters) models.add(character);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<CharacterModel>getAllCharacter(){
        return models;
    }

    @Override
    public CharacterModel getCharacterByName(String name){
        for(CharacterModel model : models){
            if(model.getName().equals(name))
                return model;
        }
        return null;
    }
    @Override
    public CharacterModel getCharacterByName(String name , boolean isContained){
        if(isContained){
            for(CharacterModel model : models){
                if(model.getName().contains(name))
                    return model;
            }
        }
        return getCharacterByName(name);
    }
}
