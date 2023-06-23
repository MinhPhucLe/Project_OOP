package service.implement;

import models.DynastyModel;
import repository.DynastyRepository;
import repository.implement.DynastyRepositoryImp;
import service.DynastyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynastyServiceImp implements DynastyService {

    private static DynastyServiceImp instance;

    private DynastyRepository dynastyRepository = DynastyRepositoryImp.getInstance();

    public static DynastyServiceImp getInstance(){
        if(instance == null) instance = new DynastyServiceImp();
        return instance;
    }

    public DynastyServiceImp(){

    }
    @Override
    public List<DynastyModel> getAllDynasty() {
        return dynastyRepository.getAllDynasty();
    }

    @Override
    public Map<String, Object> getDynastyByName(String name) {
        DynastyModel model = dynastyRepository.getDynastyByName(name);
        return model == null ? new HashMap<>() : model.MapDescription();
    }
}
