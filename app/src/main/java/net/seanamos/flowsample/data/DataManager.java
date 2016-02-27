package net.seanamos.flowsample.data;

import net.seanamos.flowsample.data.model.Model;
import net.seanamos.flowsample.data.model.Person;
import net.seanamos.flowsample.data.model.Planet;

import java.util.ArrayList;

import rx.Observable;

public interface DataManager {
    Observable<ArrayList<Person>> getPeople();
    Observable<ArrayList<Planet>> getPlanets();
    Observable<ArrayList<? extends Model>> getThings(Class<?> clz);
}