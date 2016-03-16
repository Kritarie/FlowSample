package net.seanamos.flowsample.ui.screen.planet.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.seanamos.flowsample.data.DataManager;
import net.seanamos.flowsample.data.model.Planet;

import java.util.ArrayList;

import mortar.Presenter;
import mortar.bundler.BundleService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Joseph on 2016-03-15.
 */


public class PlanetListPresenter extends Presenter<PlanetListView> {

    private static final String STATE_PLANETS = "state_planets";

    private final DataManager dataManager;
    @Nullable
    private ArrayList<Planet> planets;

    public PlanetListPresenter(@NonNull DataManager dataManager) { this.dataManager = dataManager;}

    @Override
    protected BundleService extractBundleService(PlanetListView view){
        return BundleService.getBundleService(view.getContext());
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
        super.onLoad(savedInstanceState);

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
    protected void onSave(Bundle outState){
        super.onSave(outState);
        outState.putParcelableArrayList(STATE_PLANETS, planets);
    }

    private void onLoadPlanetsComplete(ArrayList<Planet> planets){
        this.planets = planets;
        PlanetListView view = getView();
        if(view != null) view.showPlanets(planets);
    }

    private void onError(Throwable e) {
        this.planets = null;
        PlanetListView view = getView();
        if(view != null) view.showError(e);
    }
}
