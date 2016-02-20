package net.seanamos.flowsample.ui.screen.person;

import android.support.annotation.NonNull;

import net.seanamos.flowsample.data.DataManager;
import net.seanamos.flowsample.data.model.Person;
import net.seanamos.flowsample.ui.screen.ScreenScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PersonModule {

    @NonNull
    private final Person person;

    public PersonModule(@NonNull Person person) {
        this.person = person;
    }

    @Provides @NonNull @ScreenScope
    public Person providePerson() {
        return this.person;
    }

    @Provides @NonNull @ScreenScope
    public PersonPresenter providePresenter(@NonNull DataManager dataManager, @NonNull Person person) {
        return new PersonPresenter(dataManager, person);
    }
}
