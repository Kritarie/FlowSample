package net.seanamos.flowsample.ui.screen.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.seanamos.flowsample.data.DataManager;
import net.seanamos.flowsample.data.model.Person;
import net.seanamos.flowsample.data.model.Planet;

import java.util.ArrayList;

import mortar.Presenter;
import mortar.bundler.BundleService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomePresenter extends Presenter<HomeView> {

    private static final String STATE_PEOPLE = "state_people";
    private static final String STATE_PLANETS = "state_planets";

    private final DataManager dataManager;

    @Nullable
    private ArrayList<Person> people;
    @Nullable
    private ArrayList<Planet> planets;

    public HomePresenter(@NonNull DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected BundleService extractBundleService(HomeView view) {
        return BundleService.getBundleService(view.getContext());
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
        super.onLoad(savedInstanceState);

        //PEOPLE
        if (people == null && savedInstanceState != null) {
            System.out.println("One");
            people = savedInstanceState.getParcelableArrayList(STATE_PEOPLE);
            System.out.println("One");
        }

        if (people != null) {
            System.out.println("Two");
            getView().showPeople(people);
            System.out.println("Two");
        } else {
            System.out.println("Three");
            dataManager.getPeople()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onLoadPeopleComplete, this::onError);
        }

        //PLANETS
        if (planets == null && savedInstanceState != null) {
            planets = savedInstanceState.getParcelableArrayList(STATE_PLANETS);
        }

        if (planets != null) {
            getView().showPlanets(planets);
        } else {
            dataManager.getPlanets()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onLoadPlanetsComplete, this::onError);
        }


    }

    @Override
    protected void onSave(Bundle outState) {
        super.onSave(outState);
        outState.putParcelableArrayList(STATE_PEOPLE, people);
        outState.putParcelableArrayList(STATE_PLANETS, planets);
    }

    private void onLoadPeopleComplete(ArrayList<Person> people) {
        this.people = people;
        HomeView view = getView();
        if (view != null) view.showPeople(people);
    }

    private void onLoadPlanetsComplete(ArrayList<Planet> planets){
        this.planets = planets;
        HomeView view = getView();
        if (view != null) view.showPlanets(planets);
    }

    private void onError(Throwable e) {
        this.people = null;
        HomeView view = getView();
        if (view != null) view.showError(e);
    }
}
