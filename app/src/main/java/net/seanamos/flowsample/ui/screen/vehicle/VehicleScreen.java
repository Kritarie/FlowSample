package net.seanamos.flowsample.ui.screen.vehicle;

import android.support.annotation.NonNull;

import net.seanamos.flowsample.core.ApplicationComponent;
import net.seanamos.flowsample.core.dagger.ScreenComponentFactory;
import net.seanamos.flowsample.data.model.Vehicle;

import auto.parcelgson.AutoParcelGson;
import flow.ClassKey;
import flow.TreeKey;

@AutoParcelGson
public abstract class VehicleScreen extends ClassKey implements TreeKey, ScreenComponentFactory<ApplicationComponent> {

    public abstract Object parent();
    public abstract Vehicle vehicle();

    @AutoParcelGson.Builder
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
    public static Builder builder() {
        return new AutoParcelGson_VehicleScreen.Builder();
    }

    @NonNull
    public static Builder builder(@NonNull VehicleScreen source) {
        return new AutoParcelGson_VehicleScreen.Builder(source);
    }
}
