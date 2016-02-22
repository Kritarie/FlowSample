package net.seanamos.flowsample.ui.screen.vehicle;

import android.os.Bundle;
import android.support.annotation.NonNull;

import net.seanamos.flowsample.data.DataManager;
import net.seanamos.flowsample.data.model.Vehicle;

import mortar.ViewPresenter;

public class VehiclePresenter extends ViewPresenter<VehicleView> {

    @NonNull
    private final DataManager dataManager;
    @NonNull
    private final Vehicle vehicle;

    public VehiclePresenter(@NonNull DataManager dataManager, @NonNull Vehicle vehicle) {
        this.dataManager = dataManager;
        this.vehicle = vehicle;
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
        getView().showVehicle(vehicle);
    }
}
