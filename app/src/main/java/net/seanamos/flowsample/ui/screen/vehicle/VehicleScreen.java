package net.seanamos.flowsample.ui.screen.vehicle;

import android.support.annotation.NonNull;

import net.seanamos.flowsample.core.ApplicationComponent;
import net.seanamos.flowsample.core.dagger.ScreenComponentFactory;
import net.seanamos.flowsample.data.model.Vehicle;

import auto.parcel.AutoParcel;
import flow.ClassKey;
import flow.TreeKey;
import me.tatarka.gsonvalue.annotations.GsonBuilder;

@AutoParcel
public abstract class VehicleScreen extends ClassKey implements TreeKey, ScreenComponentFactory<ApplicationComponent> {

    public abstract Object parent();
    public abstract Vehicle vehicle();

    @AutoParcel.Builder
    public interface Builder {
        Builder parent(Object parent);
        Builder vehicle(Vehicle vehicle);
        VehicleScreen build();
    }

    @Override
    public Object buildComponent(ApplicationComponent parent) {
        return parent.plus(new VehicleModule(vehicle()));
    }

    @Override
    public Object getParentKey() {
        return parent();
    }

    @NonNull
    @GsonBuilder
    public static Builder builder() {
        return new AutoParcel_VehicleScreen.Builder();
    }

    @NonNull
    public static Builder builder(@NonNull VehicleScreen source) {
        return new AutoParcel_VehicleScreen.Builder(source);
    }
}
