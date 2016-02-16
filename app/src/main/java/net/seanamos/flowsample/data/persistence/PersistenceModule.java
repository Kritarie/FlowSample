package net.seanamos.flowsample.data.persistence;

import android.support.annotation.NonNull;

import net.seanamos.flowsample.core.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PersistenceModule {

    @Provides @NonNull @ApplicationScope
    public DataStore provideDataStore() {
        return new DataStore();
    }
}
