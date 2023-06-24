package org.example;

import repository.Repository;
import repository.implement.*;

public class Program {
    private Repository[] repositories = {
            CharacterRepositoryImp.getInstance(),
            DynastyRepositoryImp.getInstance(),
            EventRepositoryImp.getInstance(),
            SiteRepositoryImp.getInstance(),
            FestivalRepositoryImp.getInstance()
    };
    public Program(){
        for(Repository repo : repositories){
            repo.loadData();
        }
    }
}
