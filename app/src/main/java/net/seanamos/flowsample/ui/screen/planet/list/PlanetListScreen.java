package net.seanamos.flowsample.ui.screen.planet.list;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.ApplicationComponent;
import net.seanamos.flowsample.core.dagger.ScreenComponentFactory;
import net.seanamos.flowsample.data.model.Planet;
import net.seanamos.flowsample.ui.screen.Screen;


import auto.parcel.AutoParcel;
import flow.ClassKey;
import me.tatarka.gsonvalue.annotations.GsonConstructor;

/**
 * Created by Joseph on 2016-03-15.
 */
@AutoParcel
@Screen(layout = R.layout.list_planet, name = "Planet List")
public abstract class PlanetListScreen extends ClassKey implements ScreenComponentFactory<ApplicationComponent>, Parcelable {

    @NonNull
    @Override
    public Object buildComponent(ApplicationComponent parent) {
        return parent.plus(new PlanetListModule());
    }

    @NonNull
    @GsonConstructor
    public static PlanetListScreen from(@NonNull Planet planet) {
        return new AutoParcel_PlanetListScreen(planet);
    }
}