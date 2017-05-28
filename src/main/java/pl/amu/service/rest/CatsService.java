package pl.amu.service.rest;

import pl.amu.service.rest.model.Cat;

import java.util.Map;

/**
 * Created by win10 on 14.05.2017.
 */
public interface CatsService {
    Map<String,Cat> getCats();
    Cat findById(String id);
    Cat remove(String id);
    Cat save(Cat cat);
    void nextversion();
    int showversion();
}
