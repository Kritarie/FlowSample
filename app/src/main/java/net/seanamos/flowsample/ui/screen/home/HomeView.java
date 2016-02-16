package net.seanamos.flowsample.ui.screen.home;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.kritarie.glossator.Glossator;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.dagger.DaggerService;
import net.seanamos.flowsample.data.model.Person;
import net.seanamos.flowsample.ui.common.TransitionOut;

import java.util.List;

import flow.Flow;
import flow.path.Path;
import mortar.MortarScope;
import mortar.bundler.BundleService;
import mortar.bundler.Bundler;

public class HomeView extends FrameLayout implements TransitionOut {

    private final HomePresenter presenter;

    private RecyclerView recycler;
    private PersonAdapter adapter;

    public HomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.presenter = DaggerService.<HomeComponent>getComponentForContext(context).presenter();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.dropView(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter = new PersonAdapter());
    }

    public void showList(@NonNull List<Person> people) {
        adapter.setPeople(people);
    }

    public void showError(Throwable e) {
        // TODO: 2/13/2016 todotodo
    }

    @Override
    public Animator transitionOut(Path to, Flow.Direction direction) {
        return null;
    }
}
