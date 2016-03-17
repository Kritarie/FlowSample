package net.seanamos.flowsample.ui.screen.person.list;

import android.support.annotation.NonNull;

import net.seanamos.flowsample.data.DataManager;
import net.seanamos.flowsample.ui.screen.ScreenScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Joseph on 2016-03-17.
 */
@Module
public class PersonListModule {

    @Provides @NonNull
    @ScreenScope
    public PersonListPresenter providePresenter(@NonNull DataManager manager){
        return new PersonListPresenter(manager);
    }
}
