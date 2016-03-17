package net.seanamos.flowsample.ui.screen.home;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.dagger.DaggerService;
import net.seanamos.flowsample.data.model.Person;
import net.seanamos.flowsample.data.model.Planet;

import java.util.List;

public class HomeView extends FrameLayout {

    private final HomePresenter presenter;

    private ViewPager pager;
    private HomePagerAdapter pAdapter;
    private RecyclerView recycler;
    private PersonAdapter adapter;

    public HomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        presenter = DaggerService.<HomeComponent>getComponentForFlow(this).presenter();
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
        //recycler = (RecyclerView) findViewById(R.id.recycler);
        //recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        //recycler.setAdapter(adapter = new PersonAdapter());
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(pAdapter = new HomePagerAdapter());
    }

    public void showPeople(@NonNull List<Person> people) {
        pAdapter.setPeople(people);
    }

    public void showPlanets(@NonNull List<Planet> planets){
        pAdapter.setPlanets(planets);
    }

    public void showError(Throwable e) {
        Snackbar.make(this, "Error loading datas", Snackbar.LENGTH_LONG);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        Bundle bundle = new Bundle();
        presenter.onSave(bundle);

        return new SavedState(superState, bundle);
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if(!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        presenter.onLoad(ss.bundle);
    }

    static class SavedState extends BaseSavedState {
        @NonNull
        private final Bundle bundle;

        SavedState(@NonNull Parcelable superState, @NonNull Bundle bundle) {
            super(superState);
            this.bundle = bundle;
        }

        private SavedState(@NonNull Parcel in) {
            super(in);
            this.bundle = in.readBundle();
        }

        @Override
        public void writeToParcel(@NonNull Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeBundle(this.bundle);
        }

        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }
                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }
}
