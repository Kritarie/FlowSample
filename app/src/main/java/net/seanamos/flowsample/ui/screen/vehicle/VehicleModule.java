package net.seanamos.flowsample.ui.screen.vehicle;

import android.support.annotation.NonNull;

import net.seanamos.flowsample.data.DataManager;
import net.seanamos.flowsample.data.model.Vehicle;
import net.seanamos.flowsample.ui.screen.ScreenScope;

import dagger.Module;
import dagger.Provides;

@Module
public class VehicleModule {

    @NonNull
    private final Vehicle vehicle;

    public VehicleModule(@NonNull Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Provides @NonNull @ScreenScope
    public Vehicle provideVehicle() {
        return this.vehicle;
    }

    @Provides @NonNull @ScreenScope
    public VehiclePresenter providePresenter(@NonNull DataManager dataManager, @NonNull Vehicle vehicle) {
        return new VehiclePresenter(dataManager, vehicle);
    }

}
