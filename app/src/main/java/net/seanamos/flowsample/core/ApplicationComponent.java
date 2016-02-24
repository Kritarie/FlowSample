package net.seanamos.flowsample.core;

import net.seanamos.flowsample.data.DataModule;
import net.seanamos.flowsample.ui.screen.InitialHistory;
import net.seanamos.flowsample.ui.screen.home.HomeComponent;
import net.seanamos.flowsample.ui.screen.home.HomeModule;
import net.seanamos.flowsample.ui.screen.person.PersonComponent;
import net.seanamos.flowsample.ui.screen.person.PersonModule;
import net.seanamos.flowsample.ui.screen.planet.PlanetComponent;
import net.seanamos.flowsample.ui.screen.planet.PlanetModule;
import net.seanamos.flowsample.ui.screen.vehicle.VehicleComponent;
import net.seanamos.flowsample.ui.screen.vehicle.VehicleModule;
import net.seanamos.flowsample.ui.toolbar.ToolbarController;

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
    ToolbarController toolbar();
    HomeComponent plus(HomeModule homeModule);
    PersonComponent plus(PersonModule personModule);
    PlanetComponent plus(PlanetModule planetModule);
    VehicleComponent plus(VehicleModule vehicleModule);
}
