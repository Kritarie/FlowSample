package net.seanamos.flowsample.ui.screen.person;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.dagger.ScreenComponentFactory;
import net.seanamos.flowsample.data.model.Person;
import net.seanamos.flowsample.ui.ActivityComponent;
import net.seanamos.flowsample.ui.screen.Screen;

import auto.parcelgson.AutoParcelGson;
import flow.ClassKey;

@AutoParcelGson
@Screen(layout = R.layout.detail_person, name = "Person")
public abstract class PersonScreen extends ClassKey implements ScreenComponentFactory<ActivityComponent>, Parcelable {

    public abstract Person person();

    @AutoParcelGson.Builder
    interface Builder {
        Builder person(Person person);
        PersonScreen build();
    }

    @Override
    public Object buildComponent(ActivityComponent parent) {
        return parent.plus(new PersonModule(person()));
    }

    public static PersonScreen create(@NonNull Person person) {
        return new AutoParcelGson_PersonScreen.Builder().person(person).build();
    }
}
