package net.seanamos.flowsample.core;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.seanamos.flowsample.core.flow.AutoKeyParceler;
import net.seanamos.flowsample.data.PagingResponseTypeAdapter;
import net.seanamos.flowsample.ui.screen.InitialHistory;
import net.seanamos.flowsample.ui.screen.home.HomeScreen;
import net.seanamos.flowsample.ui.screen.person.list.PersonListScreen;
import net.seanamos.flowsample.ui.screen.planet.list.PlanetListScreen;
import net.seanamos.flowsample.ui.toolbar.ToolbarController;

import java.util.Arrays;
import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import flow.History;
import flow.KeyParceler;
import me.tatarka.gsonvalue.ValueTypeAdapterFactory;

@Module
public class ApplicationModule {

    public static final String CONTEXT_NAME = "ApplicationContext";

    @NonNull
    private final Application application;

    public ApplicationModule(@NonNull Application application) {
        this.application = application;
    }

    @Provides @NonNull @ApplicationScope @Named(CONTEXT_NAME)
    public Context provideContext() {
        return this.application;
    }

    @Provides @NonNull @ApplicationScope
    public Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(new ValueTypeAdapterFactory())
                .registerTypeAdapterFactory(new PagingResponseTypeAdapter())
                .create();
    }

    @Provides @NonNull @ApplicationScope
    public KeyParceler provideKeyParceler() {
        return new AutoKeyParceler();
    }

    @Provides @NonNull @ApplicationScope
    public InitialHistory provideInitialHistory() {
        return new InitialHistory(History.single(HomeScreen.from(Arrays.asList(PersonListScreen.create(), PlanetListScreen.create()))));
    }

    @Provides @NonNull @ApplicationScope
    public ToolbarController provideToolbarController() {
        return new ToolbarController();
    }
}