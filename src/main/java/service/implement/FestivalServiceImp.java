package service.implement;

import models.FestivalModel;
import models.SiteModel;
import repository.FestivalRepository;
import repository.implement.FestivalRepositoryImp;
import service.FestivalService;
import java.util.*;
public class FestivalServiceImp implements FestivalService {
    private static FestivalServiceImp instance;
    private FestivalRepository festivalRepository = FestivalRepositoryImp.getInstance();

    public static FestivalServiceImp getInstance(){
        if(instance == null) instance = new FestivalServiceImp();
        return instance;
    }
    public FestivalServiceImp(){

    }
    @Override
    public List<FestivalModel>getAllFestival(){
        return festivalRepository.getAllFestival();
    }

    @Override
    public Map<String, Object> getFestivalByName(String name) {
        FestivalModel model = festivalRepository.getFestivalByName(name);
        return model == null ? new HashMap<>() : model.MapDescription();
    }
    @Override
    public FestivalModel getFestivalByName(String name, boolean isContained) {
        return festivalRepository.getFestivalByName(name,isContained);
    }
}
