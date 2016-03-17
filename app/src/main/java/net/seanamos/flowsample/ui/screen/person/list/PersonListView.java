package net.seanamos.flowsample.ui.screen.person.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.dagger.DaggerService;
import net.seanamos.flowsample.data.model.Person;

import java.util.List;

/**
 * Created by Joseph on 2016-03-17.
 */
public class PersonListView extends FrameLayout {

    private final PersonListPresenter presenter;

    private RecyclerView recycler;
    private PersonAdapter adapter;

    public PersonListView(Context context, AttributeSet attrs){
        super(context, attrs);
        presenter = DaggerService.<PersonListComponent>getComponentForFlow(this).presenter();
    }

    @Override
    protected void onAttachedToWindow(){
        super.onAttachedToWindow();
        presenter.takeView(this);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        recycler = (RecyclerView) findViewById(R.id.recycler_list_person);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter = new PersonAdapter());
    }

    public void showPeople(@NonNull List<Person> people){
        adapter.setPeople(people);
    }

    public void showError(Throwable e) {
        Snackbar.make(this, "Error: it's whatever", Snackbar.LENGTH_LONG);
    }
}
