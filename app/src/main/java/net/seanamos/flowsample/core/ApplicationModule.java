package net.seanamos.flowsample.core;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.seanamos.flowsample.core.flow.AutoKeyParceler;
import net.seanamos.flowsample.ui.screen.InitialHistory;
import net.seanamos.flowsample.ui.screen.home.HomeScreen;
import net.seanamos.flowsample.ui.toolbar.ToolbarController;

import javax.inject.Named;

import auto.parcelgson.gson.AutoParcelGsonTypeAdapterFactory;
import dagger.Module;
import dagger.Provides;
import flow.History;
import flow.KeyParceler;

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
                .registerTypeAdapterFactory(new AutoParcelGsonTypeAdapterFactory())
                .create();
    }

    @Provides @NonNull @ApplicationScope
    public KeyParceler provideKeyParceler() {
        return new AutoKeyParceler();
    }

    @Provides @NonNull @ApplicationScope
    public InitialHistory provideInitialHistory() {
        return new InitialHistory(History.single(HomeScreen.create()));
    }

    @Provides @NonNull @ApplicationScope
    public ToolbarController provideToolbarController() {
        return new ToolbarController();
    }
}