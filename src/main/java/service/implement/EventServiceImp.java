package service.implement;

import models.EventModel;
import repository.EventRepository;
import repository.implement.EventRepositoryImp;
import service.EventService;

import java.util.*;

public class EventServiceImp implements EventService {
    private static EventServiceImp instance;
    private EventRepository eventRepository = EventRepositoryImp.getInstance();

    public static EventServiceImp getInstance(){
        if(instance == null) instance = new EventServiceImp();
        return instance;
    }
    public EventServiceImp(){

    }

    @Override
    public List<EventModel> getALlEvent() {
        return eventRepository.getAllEvent();
    }

    @Override
    public Map<String, Object> getEventByName(String name) {
        EventModel model = eventRepository.getEventByName(name);
        return model == null ? new HashMap<>() : model.MapDescription();
    }
}
