package net.seanamos.flowsample.ui.screen.planet;

import android.support.annotation.NonNull;

import net.seanamos.flowsample.data.DataManager;
import net.seanamos.flowsample.data.model.Planet;
import net.seanamos.flowsample.ui.screen.ScreenScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PlanetModule {

    @NonNull
    private final Planet planet;

    public PlanetModule(@NonNull Planet planet) {
        this.planet = planet;
    }

    @Provides @NonNull @ScreenScope
    public Planet providePlanet() {
        return this.planet;
    }

    @Provides @NonNull @ScreenScope
    public PlanetPresenter providePresenter(@NonNull DataManager dataManager, @NonNull Planet planet) {
        return new PlanetPresenter(dataManager, planet);
    }
}