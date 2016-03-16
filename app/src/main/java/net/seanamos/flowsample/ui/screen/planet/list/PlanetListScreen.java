package net.seanamos.flowsample.ui.screen.planet.list;

import android.os.Parcelable;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.ApplicationComponent;
import net.seanamos.flowsample.core.dagger.ScreenComponentFactory;
import net.seanamos.flowsample.ui.screen.Screen;

import auto.parcelgson.AutoParcelGson;
import flow.ClassKey;

/**
 * Created by Joseph on 2016-03-15.
 */
@AutoParcelGson
@Screen(layout = R.layout.list_planet, name = "Planet List")
public abstract class PlanetListScreen extends ClassKey implements ScreenComponentFactory<ApplicationComponent>, Parcelable {
    @Override
    public Object buildComponent(ApplicationComponent parent) {
        return parent.plus(new PlanetListModule());
    }
}