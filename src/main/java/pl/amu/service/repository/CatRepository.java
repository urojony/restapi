package pl.amu.service.repository;

//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Component;
import pl.amu.service.rest.CatsService;
import pl.amu.service.rest.model.Cat;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by win10 on 14.05.2017.
 */
@Component
public class CatRepository implements CatsService {
    public CatRepository() {
        save(new Cat("Pus", "Pusz","2011-11-11"));
        save(new Cat("Puskot", "Puskot","2011-01-02"));
        save(new Cat("Puska", "Puszka","1995-09-11"));
    }
    public int version=1;
    private static final Map<String,Cat> cats=new LinkedHashMap<>();
    public Map<String,Cat> getCats(){
        return cats;
    }
    public Cat findById(String Id){
        return cats.get(Id);
    }
    public Cat remove(String Id){
        Cat cat=cats.get(Id);
        if(Id!=null)
            cats.remove(Id);
        return cat;
    }
    public Cat save(Cat cat){
        Cat dbCat = findById(cat.getId().toLowerCase());
        if(dbCat == null) {
            dbCat = cat;
            cats.put(cat.getId().toLowerCase(),cat);
        }
        else {
            dbCat.updateCat(cat);
        }

        return cat;
    }
    public void nextversion(){
        version+=1;
    }
    public int showversion(){
        return version;
    }
}
