package net.seanamos.flowsample.ui.screen.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.seanamos.flowsample.data.DataManager;

import java.util.ArrayList;

import mortar.Presenter;
import mortar.bundler.BundleService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Joseph on 2016-02-25.
 */
public class GenericListPresenter<T> extends Presenter<GenericListView> {
    private static final String STATE_THINGS = "state_things";
    private final Class<T> clz;
    @Nullable
    private ArrayList things;

    private final DataManager dataManager;

    GenericListPresenter(@NonNull DataManager dataManager, Class<T> clz){
        this.dataManager = dataManager;
        this.clz = clz;
    }

    @Override
    protected BundleService extractBundleService(GenericListView view) {
        return BundleService.getBundleService(view.getContext());
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
        super.onLoad(savedInstanceState);

        if (things == null && savedInstanceState != null) {
            System.out.println("One");
            things = savedInstanceState.getParcelableArrayList(STATE_THINGS);
            System.out.println("One");
        }

        if (things != null) {
            System.out.println("Two");
            getView().displayList(things);
            System.out.println("Two");
        } else {
            System.out.println("Three");
            dataManager.getThings(clz)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onLoadComplete, this::onError);
        }

    }

    private void onLoadComplete(ArrayList<T> things){
        this.things = things;
        GenericListView view = getView();
        if (view != null) view.displayList(things);
    }

    private void onError(Throwable e) {
        this.things = null;
        GenericListView view = getView();
        if (view != null) view.showError(e);
    }
}
