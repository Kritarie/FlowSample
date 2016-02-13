package net.seanamos.flowsample.ui.screen.home;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.dagger.ScreenComponentFactory;
import net.seanamos.flowsample.ui.ActivityComponent;
import net.seanamos.flowsample.ui.screen.Screen;

import flow.path.Path;

@Screen(layout = R.layout.view_home, name = "Home")
public final class HomeScreen extends Path implements ScreenComponentFactory<ActivityComponent> {

    @Override
    public Object buildComponent(ActivityComponent parent) {
        return parent.plus(new HomeModule());
    }
}
