package repository;
import models.FestivalModel;

import java.util.*;
public interface FestivalRepository  {
    List<FestivalModel>getAllFestival();
    FestivalModel getFestivalByName(String name);
    FestivalModel getFestivalByName(String name , boolean isContained);
}
