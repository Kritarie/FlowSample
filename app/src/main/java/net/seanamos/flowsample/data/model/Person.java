package net.seanamos.flowsample.data.model;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

@AutoParcelGson
public abstract class Person extends Model implements Parcelable {

    public abstract String name();
    @SerializedName("birth_year")
    public abstract String birthYear();
    @SerializedName("eye_color")
    public abstract String eyeColor();
    public abstract String gender();
    @SerializedName("hair_color")
    public abstract String hairColor();
    public abstract String height();
    public abstract String mass();
    @SerializedName("skin_color")
    public abstract String skinColor();
    @SerializedName("home_world")
    public abstract String homeWorld();
    public abstract List<String> films();
    public abstract List<String> species();
    public abstract List<String> starships();
    public abstract List<String> vehicles();

    @AutoParcelGson.Builder
    public interface Builder {
        Builder name(String name);
        Builder birthYear(String birthYear);
        Builder eyeColor(String eyeColor);
        Builder gender(String gender);
        Builder hairColor(String hairColor);
        Builder height(String height);
        Builder mass(String mass);
        Builder skinColor(String skinColor);
        Builder homeWorld(String homeWorld);
        Builder films(List<String> films);
        Builder species(List<String> species);
        Builder starships(List<String> starships);
        Builder vehicles(List<String> vehicles);
        Person build();
    }

    @NonNull
    public static Builder builder() {
        return new AutoParcelGson_Person.Builder();
    }

    @NonNull
    public static Builder builder(@NonNull Person source) {
        return new AutoParcelGson_Person.Builder(source);
    }
}
