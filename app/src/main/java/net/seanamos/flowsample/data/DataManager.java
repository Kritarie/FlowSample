package net.seanamos.flowsample.data;

import net.seanamos.flowsample.data.model.Person;

import java.util.ArrayList;

import rx.Observable;

public interface DataManager {
    Observable<ArrayList<Person>> getPeople();
}