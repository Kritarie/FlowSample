package net.seanamos.flowsample.ui.screen.home;

import android.support.annotation.NonNull;

import net.seanamos.flowsample.data.DataManager;
import net.seanamos.flowsample.ui.screen.ScreenScope;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    @Provides @NonNull @ScreenScope
    public HomePresenter providePresenter(@NonNull DataManager manager) {
        return new HomePresenter(manager);
    }
}
