package service;

import models.DynastyModel;

import java.util.*;
public interface DynastyService {
    List<DynastyModel> getAllDynasty();
    Map<String,Object> getDynastyByName(String name);

    Map<String,Object> getDynastyByName(String name, boolean isContained);
}
