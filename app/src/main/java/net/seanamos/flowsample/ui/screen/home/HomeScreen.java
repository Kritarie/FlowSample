package net.seanamos.flowsample.ui.screen.home;

import android.os.Parcelable;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.ApplicationComponent;
import net.seanamos.flowsample.core.dagger.ScreenComponentFactory;
import net.seanamos.flowsample.ui.screen.Screen;

import auto.parcel.AutoParcel;
import flow.ClassKey;
import me.tatarka.gsonvalue.annotations.GsonConstructor;

@AutoParcel
@Screen(layout = R.layout.view_home, name = "Home")
public abstract class HomeScreen extends ClassKey implements ScreenComponentFactory<ApplicationComponent>, Parcelable{

    @Override
    public Object buildComponent(ApplicationComponent parent) {
        return parent.plus(new HomeModule());
    }

    @GsonConstructor
    public static HomeScreen create() {
        return new AutoParcel_HomeScreen();
    }
}
