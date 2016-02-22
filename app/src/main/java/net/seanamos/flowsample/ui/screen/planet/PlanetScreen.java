package net.seanamos.flowsample.ui.screen.planet;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.dagger.ScreenComponentFactory;
import net.seanamos.flowsample.data.model.Planet;
import net.seanamos.flowsample.ui.ActivityComponent;
import net.seanamos.flowsample.ui.screen.Screen;

import auto.parcelgson.AutoParcelGson;
import flow.ClassKey;

@AutoParcelGson
@Screen(layout = R.layout.detail_planet, name = "Planet")
public abstract class PlanetScreen extends ClassKey implements ScreenComponentFactory<ActivityComponent>, Parcelable {

    public abstract Planet planet();

    @AutoParcelGson.Builder
    interface Builder {
        Builder planet(Planet planet);
        PlanetScreen build();
    }

    @Override
    public Object buildComponent(ActivityComponent parent) {
        return parent.plus(new PlanetModule(planet()));
    }

    public static PlanetScreen create(@NonNull Planet planet) {
        return new AutoParcelGson_PlanetScreen.Builder().planet(planet).build();
    }
}