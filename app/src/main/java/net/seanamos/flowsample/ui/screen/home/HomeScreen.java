package net.seanamos.flowsample.ui.screen.home;

import android.os.Parcelable;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.ApplicationComponent;
import net.seanamos.flowsample.core.dagger.ScreenComponentFactory;
import net.seanamos.flowsample.ui.screen.Screen;

import auto.parcelgson.AutoParcelGson;
import flow.ClassKey;

@AutoParcelGson
@Screen(layout = R.layout.view_home, name = "Home")
public abstract class HomeScreen extends ClassKey implements ScreenComponentFactory<ApplicationComponent>, Parcelable{

    @Override
    public Object buildComponent(ApplicationComponent parent) {
        return parent.plus(new HomeModule());
    }

    public static HomeScreen create() {
        return new AutoParcelGson_HomeScreen();
    }
}
