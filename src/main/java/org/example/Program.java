package org.example;

import repository.CharacterRepository;
import repository.Repository;
import repository.implement.CharacterRepositoryImp;
import repository.implement.DynastyRepositoryImp;
import repository.implement.EventRepositoryImp;
import repository.implement.SiteRepositoryImp;

public class Program {
    private Repository[] repositories = {
            CharacterRepositoryImp.getInstance(),
            DynastyRepositoryImp.getInstance(),
            EventRepositoryImp.getInstance(),
            SiteRepositoryImp.getInstance()
    };
    public Program(){
        for(Repository repo : repositories){
            repo.loadData();
        }
    }
}
