package net.seanamos.flowsample.ui.screen.person;

import net.seanamos.flowsample.ui.screen.ScreenScope;

import dagger.Subcomponent;

@ScreenScope
@Subcomponent(modules = PersonModule.class)
public interface PersonComponent {
    PersonPresenter presenter();
}
