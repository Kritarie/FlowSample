package net.seanamos.flowsample.data.model;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import auto.parcel.AutoParcel;
import me.tatarka.gsonvalue.annotations.GsonBuilder;

@AutoParcel
public abstract class Person implements Parcelable {

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
    public abstract String created();
    public abstract String edited();
    public abstract String url();

    @AutoParcel.Builder
    public interface Builder {
        Builder name(String name);
        Builder birthYear(String birth_year);
        Builder eyeColor(String eye_color);
        Builder gender(String gender);
        Builder hairColor(String hair_color);
        Builder height(String height);
        Builder mass(String mass);
        Builder skinColor(String skin_color);
        Builder homeWorld(String homeworld);
        Builder films(List<String> films);
        Builder species(List<String> species);
        Builder starships(List<String> starships);
        Builder vehicles(List<String> vehicles);
        Builder created(String created);
        Builder edited(String edited);
        Builder url(String url);
        Person build();
    }

    @NonNull
    @GsonBuilder
    public static Builder builder() {
        return new AutoParcel_Person.Builder();
    }

    @NonNull
    public static Builder builder(@NonNull Person source) {
        return new AutoParcel_Person.Builder(source);
    }
}
