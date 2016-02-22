package net.seanamos.flowsample.ui.screen.vehicle;

import net.seanamos.flowsample.ui.screen.ScreenScope;

import dagger.Subcomponent;

@ScreenScope
@Subcomponent(modules = VehicleModule.class)
public interface VehicleComponent {
    VehiclePresenter presenter();
}
