package net.seanamos.flowsample.ui.screen.person.list;

import net.seanamos.flowsample.ui.screen.ScreenScope;

import dagger.Subcomponent;

/**
 * Created by Joseph on 2016-03-17.
 */
@ScreenScope
@Subcomponent(modules = PersonListModule.class)
public interface PersonListComponent {
    PersonListPresenter presenter();
}
