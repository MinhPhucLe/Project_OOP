package repository;

import models.CharacterModel;
import java.util.*;
public interface CharacterRepository {
    List<CharacterModel>getAllCharacter();
    CharacterModel getCharacterByName(String name);
    CharacterModel getCharacterByName(String name , boolean isContained);
}
