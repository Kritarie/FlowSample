package net.seanamos.flowsample.ui.screen.vehicle;

import android.support.annotation.NonNull;

import net.seanamos.flowsample.core.ApplicationComponent;
import net.seanamos.flowsample.core.dagger.ScreenComponentFactory;
import net.seanamos.flowsample.data.model.Vehicle;

import auto.parcel.AutoParcel;
import flow.ClassKey;
import me.tatarka.gsonvalue.annotations.GsonConstructor;

@AutoParcel
public abstract class VehicleScreen extends ClassKey implements ScreenComponentFactory<ApplicationComponent> {

    public abstract Vehicle vehicle();

    @NonNull
    @Override
    public Object buildComponent(@NonNull ApplicationComponent parent) {
        return parent.plus(new VehicleModule(vehicle()));
    }

    @NonNull
    @GsonConstructor
    public static VehicleScreen from(@NonNull Vehicle vehicle) {
        return new AutoParcel_VehicleScreen(vehicle);
    }
}
