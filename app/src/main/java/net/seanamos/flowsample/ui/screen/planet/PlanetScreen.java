package net.seanamos.flowsample.ui.screen.planet;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.ApplicationComponent;
import net.seanamos.flowsample.core.dagger.ScreenComponentFactory;
import net.seanamos.flowsample.data.model.Planet;
import net.seanamos.flowsample.ui.screen.Screen;

import auto.parcelgson.AutoParcelGson;
import flow.ClassKey;
import flow.TreeKey;

@AutoParcelGson
@Screen(layout = R.layout.detail_planet, name = "Planet")
public abstract class PlanetScreen extends ClassKey implements TreeKey, ScreenComponentFactory<ApplicationComponent>, Parcelable {

    public abstract Object parent();
    public abstract Planet planet();

    @AutoParcelGson.Builder
    public interface Builder {
        Builder parent(Object parent);
        Builder planet(Planet planet);
        PlanetScreen build();
    }

    @Override
    public Object buildComponent(ApplicationComponent parent) {
        return parent.plus(new PlanetModule(planet()));
    }

    @Override
    public Object getParentKey() { return parent();}

    @NonNull
    public static Builder builder() {
        return new AutoParcelGson_PlanetScreen.Builder();
    }

    @NonNull
    public static Builder builder(@NonNull PlanetScreen source) {
        return new AutoParcelGson_PlanetScreen.Builder(source);
    }
}