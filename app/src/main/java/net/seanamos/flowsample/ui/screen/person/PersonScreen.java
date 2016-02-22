package net.seanamos.flowsample.ui.screen.person;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.ApplicationComponent;
import net.seanamos.flowsample.core.dagger.ScreenComponentFactory;
import net.seanamos.flowsample.data.model.Person;
import net.seanamos.flowsample.ui.screen.Screen;

import auto.parcelgson.AutoParcelGson;
import flow.ClassKey;
import flow.TreeKey;

@AutoParcelGson
@Screen(layout = R.layout.detail_person, name = "Person")
public abstract class PersonScreen extends ClassKey implements TreeKey, ScreenComponentFactory<ApplicationComponent>, Parcelable {

    public abstract Object parent();
    public abstract Person person();

    @AutoParcelGson.Builder
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
    public static Builder builder() {
        return new AutoParcelGson_PersonScreen.Builder();
    }

    @NonNull
    public static Builder builder(@NonNull PersonScreen source) {
        return new AutoParcelGson_PersonScreen.Builder(source);
    }
}
