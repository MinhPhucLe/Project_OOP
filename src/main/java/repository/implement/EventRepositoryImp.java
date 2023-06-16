package repository.implement;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.EventModel;
import repository.EventRepository;
import repository.Repository;
import util.JsonURL;

import java.io.File;
import java.util.*;

public class EventRepositoryImp implements EventRepository , Repository {
    public static EventRepositoryImp instance;
    private List<EventModel>models = new ArrayList<>();

    private EventRepositoryImp(){

    }
    public static EventRepositoryImp getInstance(){
        if(instance == null) instance = new EventRepositoryImp();
        return instance;
    }
    @Override
    public void loadData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            EventModel[] events = mapper.readValue(new File(JsonURL.SU_KIEN_FILENAME),EventModel[].class);
            for(EventModel event : events) models.add(event);
        }catch(Exception e){
            e.printStackTrace();;
        }
    }
    @Override
    public List<EventModel> getAllEvent() { return models; }
    @Override
    public EventModel getEventByName(String name) {
        for(EventModel model : models){
            if(model.getName().equals(name))
                return model;
        }
        return null;
    }

    @Override
    public EventModel getEventByName(String name, boolean isContained) {
        if(isContained){
            for(EventModel model : models){
                if(model.getName().contains(name))
                    return model;
            }
        }
        return getEventByName(name);
    }
}
