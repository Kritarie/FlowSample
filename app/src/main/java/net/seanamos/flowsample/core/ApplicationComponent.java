package net.seanamos.flowsample.core;

import net.seanamos.flowsample.data.DataModule;
import net.seanamos.flowsample.ui.ActivityComponent;
import net.seanamos.flowsample.ui.ActivityModule;

import dagger.Component;

@ApplicationScope
@Component(modules = {
        ApplicationModule.class,
        DataModule.class
})
public interface ApplicationComponent {
    ActivityComponent plus(ActivityModule activityModule);
}
