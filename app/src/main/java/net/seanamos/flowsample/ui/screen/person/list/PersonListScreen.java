package net.seanamos.flowsample.ui.screen.person.list;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.ApplicationComponent;
import net.seanamos.flowsample.core.dagger.ScreenComponentFactory;
import net.seanamos.flowsample.ui.screen.Screen;

import auto.parcel.AutoParcel;
import flow.ClassKey;
import me.tatarka.gsonvalue.annotations.GsonConstructor;

/**
 * Created by Joseph on 2016-03-17.
 */
@AutoParcel
@Screen(layout = R.layout.list_person, name = "Person List")
public abstract class PersonListScreen extends ClassKey implements ScreenComponentFactory<ApplicationComponent>, Parcelable {

    @NonNull
    @Override
    public Object buildComponent(ApplicationComponent parent){
        return parent.plus(new PersonListModule());
    }

    @GsonConstructor
    public static PersonListScreen create() { return new AutoParcel_PersonListScreen();}
}
