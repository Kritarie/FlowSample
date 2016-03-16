package net.seanamos.flowsample.ui.screen.planet.list;

import android.support.annotation.NonNull;

import net.seanamos.flowsample.data.DataManager;
import net.seanamos.flowsample.ui.screen.ScreenScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Joseph on 2016-03-15.
 */
@Module
public class PlanetListModule {
    @Provides
    @NonNull @ScreenScope
    public PlanetListPresenter providePresenter(@NonNull DataManager manager){
        return new PlanetListPresenter(manager);
    }
}
