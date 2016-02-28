package net.seanamos.flowsample.ui.screen.person;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.ApplicationComponent;
import net.seanamos.flowsample.core.dagger.ScreenComponentFactory;
import net.seanamos.flowsample.data.model.Person;
import net.seanamos.flowsample.ui.screen.Screen;

import auto.parcel.AutoParcel;
import flow.ClassKey;
import flow.TreeKey;
import me.tatarka.gsonvalue.annotations.GsonBuilder;

@AutoParcel
@Screen(layout = R.layout.detail_person, name = "Person")
public abstract class PersonScreen extends ClassKey implements TreeKey, ScreenComponentFactory<ApplicationComponent>, Parcelable {

    public abstract Object parent();
    public abstract Person person();

    @AutoParcel.Builder
    public interface Builder {
        Builder parent(Object parent);
        Builder person(Person person);
        PersonScreen build();
    }

    @Override
    public Object buildComponent(ApplicationComponent parent) {
        return parent.plus(new PersonModule(person()));
    }

    @Override
    public Object getParentKey() {
        return parent();
    }

    @NonNull
    @GsonBuilder
    public static Builder builder() {
        return new AutoParcel_PersonScreen.Builder();
    }

    @NonNull
    public static Builder builder(@NonNull PersonScreen source) {
        return new AutoParcel_PersonScreen.Builder(source);
    }
}