package net.seanamos.flowsample.ui.screen.planet.detail;

import android.support.annotation.NonNull;

import net.seanamos.flowsample.data.DataManager;
import net.seanamos.flowsample.data.model.Planet;
import net.seanamos.flowsample.ui.screen.ScreenScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PlanetDetailModule {

    @NonNull
    private final Planet planet;

    public PlanetDetailModule(@NonNull Planet planet) {
        this.planet = planet;
    }

    @Provides @NonNull @ScreenScope
    public Planet providePlanet() {
        return this.planet;
    }

    @Provides @NonNull @ScreenScope
    public PlanetDetailPresenter providePresenter(@NonNull DataManager dataManager, @NonNull Planet planet) {
        return new PlanetDetailPresenter(dataManager, planet);
    }
}