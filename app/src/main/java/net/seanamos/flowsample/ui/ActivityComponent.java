package net.seanamos.flowsample.ui;

import net.seanamos.flowsample.ui.screen.home.HomeComponent;
import net.seanamos.flowsample.ui.screen.home.HomeModule;
import net.seanamos.flowsample.ui.screen.InitialHistory;

import dagger.Subcomponent;
import flow.KeyParceler;

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    KeyParceler parceler();
    InitialHistory initialHistory();
    HomeComponent plus(HomeModule homeModule);
}
