package service;

import models.CharacterModel;
import java.util.*;
public interface CharacterService {
    List<CharacterModel>getAllCharacter();
    Map<String , Object> getCharacterByName(String name);
    Map<String , Object> getCharacterByName(String name , boolean isContained);
}
