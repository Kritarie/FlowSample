package net.seanamos.flowsample.ui.screen.planet.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.dagger.DaggerService;
import net.seanamos.flowsample.data.model.Planet;

import java.util.List;

/**
 * Created by Joseph on 2016-03-08.
 */
public class PlanetListView extends FrameLayout {

    private final PlanetListPresenter presenter;

    private RecyclerView recycler;
    private PlanetAdapter adapter;

    public PlanetListView(Context context, AttributeSet attrs){
        super(context, attrs);
        presenter = DaggerService.<PlanetListComponent>getComponentForFlow(this).presenter();
    }

    @Override
    protected void onAttachedToWindow(){
        super.onAttachedToWindow();
        presenter.takeView(this);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        recycler = (RecyclerView) findViewById(R.id.recycler_planet_list);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter = new PlanetAdapter());
    }

    public void showPlanets(@NonNull List<Planet> planets) {
        adapter.setPlanets(planets);
    }

    public void showError(Throwable e) {
        Snackbar.make(this, "Sean please add detail", Snackbar.LENGTH_LONG);
    }
}
