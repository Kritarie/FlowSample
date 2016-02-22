package net.seanamos.flowsample.ui.screen.planet;

import net.seanamos.flowsample.ui.screen.ScreenScope;

import dagger.Subcomponent;

@ScreenScope
@Subcomponent(modules = PlanetModule.class)
public interface PlanetComponent {
    PlanetPresenter presenter();
}