package net.seanamos.flowsample.ui.screen.person;

import android.os.Bundle;
import android.support.annotation.NonNull;

import net.seanamos.flowsample.data.DataManager;
import net.seanamos.flowsample.data.model.Person;

import mortar.ViewPresenter;

public class PersonPresenter extends ViewPresenter<PersonView> {

    @NonNull
    private final DataManager dataManager;
    @NonNull
    private final Person person;

    public PersonPresenter(@NonNull DataManager dataManager, @NonNull Person person) {
        this.dataManager = dataManager;
        this.person = person;
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
        getView().showPerson(person);
    }
}
