package net.seanamos.flowsample.ui.screen.planet.detail;

import net.seanamos.flowsample.ui.screen.ScreenScope;

import dagger.Subcomponent;

@ScreenScope
@Subcomponent(modules = PlanetDetailModule.class)
public interface PlanetDetailComponent {
    PlanetDetailPresenter presenter();
}