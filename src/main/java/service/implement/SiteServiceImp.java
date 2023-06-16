package service.implement;

import models.SiteModel;
import repository.SiteRepository;
import repository.implement.SiteRepositoryImp;
import service.SiteService;

import java.util.*;

public class SiteServiceImp implements SiteService {
    private static SiteServiceImp instance;
    private SiteRepository siteRepository = SiteRepositoryImp.getInstance();

    public static SiteServiceImp getInstance(){
        if(instance == null) instance = new SiteServiceImp();
        return instance;
    }
    public SiteServiceImp(){

    }

    @Override
    public List<SiteModel> getAllSite() {
        return siteRepository.getAllSite();
    }
    @Override
    public Map<String, Object> getSiteByName(String name) {
        SiteModel model = siteRepository.getSiteByName(name);
        return model == null ? new HashMap<>() : model.getMapDescription();
    }
}
