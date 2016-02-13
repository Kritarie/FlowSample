package net.seanamos.flowsample.core;

import net.seanamos.flowsample.data.api.ApiModule;
import net.seanamos.flowsample.ui.ActivityComponent;
import net.seanamos.flowsample.ui.ActivityModule;

import dagger.Component;

@ApplicationScope
@Component(modules = {
        ApplicationModule.class,
        ApiModule.class
})
public interface ApplicationComponent {
    ActivityComponent plus(ActivityModule activityModule);
}
