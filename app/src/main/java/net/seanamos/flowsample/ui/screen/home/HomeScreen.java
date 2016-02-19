package net.seanamos.flowsample.ui.screen.home;

import android.os.Parcelable;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.dagger.ScreenComponentFactory;
import net.seanamos.flowsample.ui.ActivityComponent;
import net.seanamos.flowsample.ui.screen.Screen;

import auto.parcelgson.AutoParcelGson;
import flow.ClassKey;

@AutoParcelGson
@Screen(layout = R.layout.view_home, name = "Home")
public abstract class HomeScreen extends ClassKey implements ScreenComponentFactory<ActivityComponent>, Parcelable{

    @Override
    public Object buildComponent(ActivityComponent parent) {
        return parent.plus(new HomeModule());
    }

    public static HomeScreen create() {
        return new AutoParcelGson_HomeScreen();
    }
}
