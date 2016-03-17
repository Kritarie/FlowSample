package net.seanamos.flowsample.ui.screen.person.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.seanamos.flowsample.data.DataManager;
import net.seanamos.flowsample.data.model.Person;

import java.util.ArrayList;

import mortar.Presenter;
import mortar.bundler.BundleService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Joseph on 2016-03-16.
 */
public class PersonListPresenter extends Presenter<PersonListView> {

    private static final String STATE_PEOPLE = "state_people";

    private final DataManager dataManager;
    @Nullable
    private ArrayList<Person> people;

    public PersonListPresenter(@NonNull DataManager dataManager) { this.dataManager = dataManager;}

    @Override
    protected BundleService extractBundleService(PersonListView view){
        return BundleService.getBundleService(view.getContext());
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
        super.onLoad(savedInstanceState);

        if(people == null && savedInstanceState != null) {
            people = savedInstanceState.getParcelableArrayList(STATE_PEOPLE);
        }

        if(people != null) {
            getView().showPeople(people);
        } else {
            dataManager.getPeople()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onLoadPeopleComplete, this::onError);
        }
    }

    @Override
    protected void onSave(Bundle outState){
        super.onSave(outState);
        outState.putParcelableArrayList(STATE_PEOPLE, people);
    }

    private void onLoadPeopleComplete(ArrayList<Person> people){
        this.people = people;
        PersonListView view = getView();
        if(view != null) view.showPeople(people);
    }

    private void onError(Throwable e) {
        this.people = null;
        PersonListView view = getView();
        if(view != null) view.showError(e);
    }
}
