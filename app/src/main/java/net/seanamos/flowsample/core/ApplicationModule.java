package net.seanamos.flowsample.core;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.seanamos.flowsample.ui.screen.InitialHistory;
import net.seanamos.flowsample.ui.screen.home.HomeScreen;

import javax.inject.Named;

import auto.parcelgson.gson.AutoParcelGsonTypeAdapterFactory;
import dagger.Module;
import dagger.Provides;
import flow.History;
import flow.KeyParceler;
import mortar.MortarScope;

@Module
public class ApplicationModule {

    public static final String CONTEXT_NAME = "ApplicationContext";

    @NonNull
    private final Application application;
    @NonNull
    private final MortarScope scope;

    public ApplicationModule(@NonNull Application application, @NonNull MortarScope scope) {
        this.application = application;
        this.scope = scope;
    }

    @Provides @NonNull @ApplicationScope
    public MortarScope provideRootScope() {
        return this.scope;
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
        return new InitialHistory(History.single(new HomeScreen()));
    }

    @Provides @NonNull @ApplicationScope
    public FlowServices provideFlowServices(MortarScope rootScope) {
        return new FlowServices(rootScope);
    }
}