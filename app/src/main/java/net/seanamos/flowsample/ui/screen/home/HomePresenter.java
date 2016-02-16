package net.seanamos.flowsample.ui.screen.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.seanamos.flowsample.data.DataManager;
import net.seanamos.flowsample.data.model.Person;

import java.util.ArrayList;
import java.util.List;

import mortar.Presenter;
import mortar.bundler.BundleService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomePresenter extends Presenter<HomeView> {

    private static final String STATE_PEOPLE = "state_people";

    private final DataManager dataManager;

    @Nullable
    private ArrayList<Person> people;

    public HomePresenter(@NonNull DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected BundleService extractBundleService(HomeView view) {
        return BundleService.getBundleService(view.getContext());
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
        super.onLoad(savedInstanceState);

        if (people == null && savedInstanceState != null) {
            people = savedInstanceState.getParcelableArrayList(STATE_PEOPLE);
        }

        if (people != null) {
            getView().showList(people);
        } else {
            dataManager.getPeople()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onLoadComplete, this::onError);
        }
    }

    @Override
    protected void onSave(Bundle outState) {
        super.onSave(outState);
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
