package net.seanamos.flowsample.data;

import android.support.annotation.NonNull;

import net.seanamos.flowsample.core.ApplicationScope;
import net.seanamos.flowsample.data.network.NetworkModule;
import net.seanamos.flowsample.data.network.SWService;
import net.seanamos.flowsample.data.persistence.DataStore;
import net.seanamos.flowsample.data.persistence.PersistenceModule;

import dagger.Module;
import dagger.Provides;

@Module(includes = { NetworkModule.class, PersistenceModule.class })
public class DataModule {

    @Provides @NonNull @ApplicationScope
    public DataManager provideDataManager(@NonNull DataStore store, @NonNull SWService swapi) {
        return new ContentDataManager(store, swapi);
    }
}
