package net.seanamos.flowsample.ui.screen.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.dagger.DaggerService;
import net.seanamos.flowsample.data.model.Person;

import java.util.List;

public class HomeView extends FrameLayout {

    private final HomePresenter presenter;

    private RecyclerView recycler;
    private PersonAdapter adapter;

    public HomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.presenter = DaggerService.<HomeComponent>getComponent(context).presenter();
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
}
