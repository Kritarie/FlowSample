package net.seanamos.flowsample.core;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import net.seanamos.flowsample.core.parcel.GsonParceler;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import flow.StateParceler;

@Module
public class ApplicationModule {

    public static final String CONTEXT_NAME = "ContextName";

    @NonNull
    private final Application application;

    public ApplicationModule(@NonNull Application application) {
        this.application = application;
    }

    @Provides @NonNull @ApplicationScope @Named(CONTEXT_NAME)
    public Context provideContext() {
        return application;
    }

    @Provides @NonNull @ApplicationScope
    public Gson provideGson() {
        return new Gson();
    }

    @Provides @NonNull @ApplicationScope
    public StateParceler provideParceler(@NonNull Gson gson) {
        return new GsonParceler(gson);
    }
}