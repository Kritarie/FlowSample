package net.seanamos.flowsample.data;

import android.support.annotation.NonNull;

import net.seanamos.flowsample.data.model.Person;
import net.seanamos.flowsample.data.model.Planet;
import net.seanamos.flowsample.data.network.SWService;
import net.seanamos.flowsample.data.network.response.PagingResponse;
import net.seanamos.flowsample.data.persistence.DataStore;

import java.util.ArrayList;

import rx.Observable;

public class ContentDataManager implements DataManager {

    private final DataStore store;
    private final SWService swapi;

    public ContentDataManager(@NonNull DataStore store, @NonNull SWService swapi) {
        this.store = store;
        this.swapi = swapi;
    }

    @Override
    public Observable<ArrayList<Person>> getPeople() {
        return swapi.fetchPeople().map(PagingResponse::results);
    }

    @Override
    public Observable<ArrayList<Planet>> getPlanets() {
        return swapi.fetchPlanets().map(PagingResponse::results);
    }
}
