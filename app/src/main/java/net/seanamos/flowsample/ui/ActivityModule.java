package net.seanamos.flowsample.ui;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import net.seanamos.flowsample.core.FlowServices;
import net.seanamos.flowsample.ui.screen.InitialHistory;
import net.seanamos.flowsample.ui.screen.home.HomeScreen;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import flow.History;

@Module
public class ActivityModule {

    public static final String CONTEXT_NAME = "ActivityContext";

    @NonNull
    private final Activity activity;

    public ActivityModule(@NonNull Activity activity) {
        this.activity = activity;
    }

    @Provides @NonNull @ActivityScope @Named(CONTEXT_NAME)
    public Context provideContext() {
        return activity;
    }

    @Provides @NonNull @ActivityScope
    public InitialHistory provideInitialHistory() {
        return new InitialHistory(History.single(new HomeScreen()));
    }
}
