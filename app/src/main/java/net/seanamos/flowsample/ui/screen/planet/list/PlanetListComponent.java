package net.seanamos.flowsample.ui.screen.planet.list;

import net.seanamos.flowsample.ui.screen.ScreenScope;
import dagger.Subcomponent;
/**
 * Created by Joseph on 2016-03-15.
 */
@ScreenScope
@Subcomponent(modules = PlanetListModule.class)
public interface PlanetListComponent {
    PlanetListPresenter presenter();
}
