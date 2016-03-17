package net.seanamos.flowsample.ui.screen.person.detail;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.ApplicationComponent;
import net.seanamos.flowsample.core.dagger.ScreenComponentFactory;
import net.seanamos.flowsample.data.model.Person;
import net.seanamos.flowsample.ui.screen.Screen;

import auto.parcel.AutoParcel;
import flow.ClassKey;
import me.tatarka.gsonvalue.annotations.GsonConstructor;

@AutoParcel
@Screen(layout = R.layout.detail_person, name = "Person")
public abstract class PersonScreen extends ClassKey implements ScreenComponentFactory<ApplicationComponent>, Parcelable {

    public abstract Person person();

    @NonNull
    @Override
    public Object buildComponent(@NonNull ApplicationComponent parent) {
        return parent.plus(new PersonModule(person()));
    }

    @NonNull
    @GsonConstructor
    public static PersonScreen from(@NonNull Person person) {
        return new AutoParcel_PersonScreen(person);
    }
}