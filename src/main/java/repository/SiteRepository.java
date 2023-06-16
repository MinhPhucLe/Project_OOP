package repository;

import models.SiteModel;

import java.util.List;

public interface SiteRepository {
    List<SiteModel>getAllSite();
    SiteModel getSiteByName(String name);
    SiteModel getSiteByName(String name , boolean isContained);
}
