package net.seanamos.flowsample.ui.screen.planet.detail;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.ApplicationComponent;
import net.seanamos.flowsample.core.dagger.ScreenComponentFactory;
import net.seanamos.flowsample.data.model.Planet;
import net.seanamos.flowsample.ui.screen.Screen;

import auto.parcel.AutoParcel;
import flow.ClassKey;
import flow.TreeKey;
import me.tatarka.gsonvalue.annotations.GsonConstructor;

@AutoParcel
@Screen(layout = R.layout.detail_planet, name = "Planet")
public abstract class PlanetDetailScreen extends ClassKey implements TreeKey, ScreenComponentFactory<ApplicationComponent>, Parcelable {

    public abstract Planet planet();

    @NonNull
    @Override
    public Object buildComponent(@NonNull ApplicationComponent parent) {
        return parent.plus(new PlanetDetailModule(planet()));
    }

    @NonNull
    @GsonConstructor
    public static PlanetDetailScreen from(@NonNull Planet planet){
        return new AutoParcel_PlanetDetailScreen(planet);
    }
}