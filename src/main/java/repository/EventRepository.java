package repository;

import models.EventModel;

import java.util.*;
public interface EventRepository {
    List<EventModel> getAllEvent();

    EventModel getEventByName(String name);

    EventModel getEventByName(String name , boolean isContained);
}
