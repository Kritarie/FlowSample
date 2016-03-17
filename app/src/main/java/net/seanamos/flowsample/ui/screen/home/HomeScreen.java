package net.seanamos.flowsample.ui.screen.home;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.ApplicationComponent;
import net.seanamos.flowsample.core.dagger.ScreenComponentFactory;
import net.seanamos.flowsample.ui.screen.Screen;

import java.util.ArrayList;
import java.util.List;

import auto.parcel.AutoParcel;
import flow.ClassKey;
import flow.MultiKey;
import me.tatarka.gsonvalue.annotations.GsonConstructor;

@AutoParcel
@Screen(layout = R.layout.view_home, name = "Home")
public abstract class HomeScreen extends ClassKey implements MultiKey, ScreenComponentFactory<ApplicationComponent>, Parcelable{

    public abstract List<Object> children();
    @NonNull
    @Override
    public Object buildComponent(@NonNull ApplicationComponent parent) {
        return parent.plus(new HomeModule());
    }

    @GsonConstructor
    public static HomeScreen from(List<Object> children) {
        return new AutoParcel_HomeScreen(children);
    }

    @Override
    public List<Object> getKeys() {
        return children();
    }
}
