package net.seanamos.flowsample.core;

import net.seanamos.flowsample.data.DataModule;
import net.seanamos.flowsample.ui.screen.InitialHistory;
import net.seanamos.flowsample.ui.screen.home.HomeComponent;
import net.seanamos.flowsample.ui.screen.home.HomeModule;
import net.seanamos.flowsample.ui.screen.person.detail.PersonComponent;
import net.seanamos.flowsample.ui.screen.person.detail.PersonModule;
import net.seanamos.flowsample.ui.screen.person.list.PersonListComponent;
import net.seanamos.flowsample.ui.screen.person.list.PersonListModule;
import net.seanamos.flowsample.ui.screen.planet.detail.PlanetDetailComponent;
import net.seanamos.flowsample.ui.screen.planet.detail.PlanetDetailModule;
import net.seanamos.flowsample.ui.screen.planet.list.PlanetListComponent;
import net.seanamos.flowsample.ui.screen.planet.list.PlanetListModule;
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
    PersonListComponent plus(PersonListModule personListModule);
    PlanetDetailComponent plus(PlanetDetailModule planetDetailModule);
    PlanetListComponent plus(PlanetListModule planetListModule);
    VehicleComponent plus(VehicleModule vehicleModule);
}

