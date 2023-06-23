package service.implement;

import models.CharacterModel;
import repository.CharacterRepository;
import repository.implement.CharacterRepositoryImp;
import service.CharacterService;

import java.util.*;

public class CharacterServiceImp implements CharacterService {
    private static CharacterServiceImp instance;
    private CharacterRepository characterRepository = CharacterRepositoryImp.getInstance();

    public static CharacterServiceImp getInstance(){
        if(instance == null) instance = new CharacterServiceImp();
        return instance;
    }
    public CharacterServiceImp(){

    }
    @Override
    public List<CharacterModel> getAllCharacter(){
        return characterRepository.getAllCharacter();
    }

    @Override
    public Map<String,Object> getCharacterByName(String name){
        CharacterModel model = characterRepository.getCharacterByName(name);
        return model == null ? new HashMap<>() : model.MapDescription();
    }

}
