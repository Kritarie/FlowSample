package net.seanamos.flowsample.ui.screen.home;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import net.seanamos.flowsample.core.dagger.DaggerService;

import java.util.List;

/**
 * Created by Joseph on 2016-02-25.
 */
public class GenericListView<T> extends RecyclerView {

    GenericListPresenter presenter;

    public GenericListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        presenter = DaggerService.<HomeComponent>getComponentForFlow(this).genericPresenter();
    }

    public void displayList(List<T> things){

    }

    public void showError(Throwable e) {
        Snackbar.make(this, "Error loading datas", Snackbar.LENGTH_LONG);
    }

}
