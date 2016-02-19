package net.seanamos.flowsample.core;

import net.seanamos.flowsample.data.DataModule;
import net.seanamos.flowsample.ui.ActivityComponent;
import net.seanamos.flowsample.ui.ActivityModule;
import net.seanamos.flowsample.ui.screen.InitialHistory;

import dagger.Component;
import flow.KeyParceler;

@ApplicationScope
@Component(modules = {
        ApplicationModule.class,
        DataModule.class
})
public interface ApplicationComponent {
    KeyParceler parceler();
    InitialHistory initialHistory();
    ActivityComponent plus(ActivityModule activityModule);
}
