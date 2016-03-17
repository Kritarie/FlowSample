package net.seanamos.flowsample.data.model;

import android.os.Parcelable;

import java.util.List;


import com.google.gson.annotations.SerializedName;

import auto.parcel.AutoParcel;
import me.tatarka.gsonvalue.annotations.GsonBuilder;

@AutoParcel
public abstract class Planet implements Parcelable {

    public abstract String name();
    @SerializedName("rotation_period")
    public abstract String rotationPeriod();
    @SerializedName("orbital_period")
    public abstract String orbitalPeriod();
    public abstract String diameter();
    public abstract String climate();
    public abstract String gravity();
    public abstract String terrain();
    @SerializedName("surface_water")
    public abstract String surfaceWater();
    public abstract String population();
    public abstract List<String> residents();
    public abstract List<String> films();
    public abstract String created();
    public abstract String edited();
    public abstract String url();


    @AutoParcel.Builder
    interface Builder {
        Builder name(String name);
        Builder rotationPeriod(String rotationPeriod);
        Builder orbitalPeriod(String orbitalPeriod);
        Builder diameter(String diameter);
        Builder climate(String climate);
        Builder gravity(String gravity);
        Builder terrain(String terrain);
        Builder surfaceWater(String surfaceWater);
        Builder population(String population);
        Builder residents(List<String> residents);
        Builder films(List<String> films);
        Builder created(String created);
        Builder edited(String edited);
        Builder url(String url);
        Planet build();
    }
}