package service;
import models.EventModel;

import java.util.*;
public interface EventService {
    List<EventModel>getALlEvent();
    Map<String,Object> getEventByName(String name);
    Map<String,Object> getEventByName(String name,boolean isContained);
}
