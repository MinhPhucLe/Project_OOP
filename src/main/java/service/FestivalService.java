package service;

import models.FestivalModel;
import java.util.*;
public interface FestivalService {
    List<FestivalModel>getAllFestival();
    Map<String , Object> getFestivalByName(String name);
    Map<String,Object> getFestivalByName(String name,boolean isContained);
}
