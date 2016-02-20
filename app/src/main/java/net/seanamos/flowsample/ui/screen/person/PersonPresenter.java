package net.seanamos.flowsample.ui.screen.person;

import android.os.Bundle;
import android.support.annotation.NonNull;

import net.seanamos.flowsample.data.DataManager;
import net.seanamos.flowsample.data.model.Person;

import mortar.Presenter;
import mortar.bundler.BundleService;

public class PersonPresenter extends Presenter<PersonView> {

    @NonNull
    private final DataManager dataManager;
    @NonNull
    private final Person person;

    public PersonPresenter(@NonNull DataManager dataManager, @NonNull Person person) {
        this.dataManager = dataManager;
        this.person = person;
    }

    @Override
    protected BundleService extractBundleService(PersonView view) {
        return BundleService.getBundleService(view.getContext());
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
        super.onLoad(savedInstanceState);
        getView().showPerson(person);
    }
}
