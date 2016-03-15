package net.seanamos.flowsample.ui.screen.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.seanamos.flowsample.data.DataManager;
import net.seanamos.flowsample.data.model.Person;

import java.util.ArrayList;

import mortar.ViewPresenter;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomePresenter extends ViewPresenter<HomeView> {

    private static final String STATE_PEOPLE = "state_people";

    private final DataManager dataManager;

    @Nullable
    private ArrayList<Person> people;

    public HomePresenter(@NonNull DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
        if (people == null && savedInstanceState != null) {
            people = savedInstanceState.getParcelableArrayList(STATE_PEOPLE);
        }

        if (people != null) {
            if (getView() != null) {
                getView().showList(people);
            }
        } else {
            dataManager.getPeople()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onLoadComplete, this::onError);
        }
    }

    @Override
    protected void onSave(Bundle outState) {
        outState.putParcelableArrayList(STATE_PEOPLE, people);
    }

    private void onLoadComplete(ArrayList<Person> people) {
        this.people = people;
        HomeView view = getView();
        if (view != null) view.showList(people);
    }

    private void onError(Throwable e) {
        this.people = null;
        HomeView view = getView();
        if (view != null) view.showError(e);
    }
}
