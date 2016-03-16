package net.seanamos.flowsample.ui.screen.planet.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;

import net.seanamos.flowsample.data.DataManager;
import net.seanamos.flowsample.data.model.Planet;

import mortar.Presenter;
import mortar.bundler.BundleService;

public class PlanetDetailPresenter extends Presenter<PlanetDetailView> {

    @NonNull
    private final DataManager dataManager;
    @NonNull
    private final Planet planet;

    public PlanetDetailPresenter(@NonNull DataManager dataManager, @NonNull Planet planet) {
        this.dataManager = dataManager;
        this.planet = planet;
    }

    @Override
    protected BundleService extractBundleService(PlanetDetailView view) {
        return BundleService.getBundleService(view.getContext());
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
        super.onLoad(savedInstanceState);
        getView().showPlanet(planet);
    }
}