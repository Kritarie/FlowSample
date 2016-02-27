package net.seanamos.flowsample.data.model;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

@AutoParcelGson
public abstract class Vehicle extends Model implements Parcelable {

    public abstract String name();
    public abstract String model();
    @SerializedName("vehicle_class")
    public abstract String vehicleClass();
    public abstract String manufacturer();
    public abstract String length();
    @SerializedName("cost_in_credits")
    public abstract String cost();
    public abstract String crew();
    public abstract String passengers();
    @SerializedName("max_atmosphering_speed")
    public abstract String speed();
    public abstract String consumables();
    public abstract List<String> films();
    public abstract List<String> pilots();

    @AutoParcelGson.Builder
    public interface Builder {
        Builder name(String name);
        Builder model(String model);
        Builder vehicleClass(String vehicleClass);
        Builder manufacturer(String manufacturer);
        Builder length(String length);
        Builder cost(String cost);
        Builder crew(String crew);
        Builder passengers(String passengers);
        Builder speed(String speed);
        Builder consumables(String consumables);
        Builder films(List<String> films);
        Builder pilots(List<String> pilots);
        Vehicle build();
    }

    @NonNull
    public static Builder builder() {
        return new AutoParcelGson_Vehicle.Builder();
    }

    @NonNull
    public static Builder builder(@NonNull Vehicle source) {
        return new AutoParcelGson_Vehicle.Builder(source);
    }

    public String getTitle(){
        return name();
    }
}
