package repository;

import models.DynastyModel;

import java.util.*;

public interface DynastyRepository {
    List<DynastyModel>getAllDynasty();
    DynastyModel getDynastyByName(String name);

    DynastyModel getDynastyByName(String name, boolean isContained);
}
