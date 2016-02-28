package net.seanamos.flowsample.data.network.response;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import auto.parcel.AutoParcel;
import me.tatarka.gsonvalue.annotations.GsonConstructor;

@AutoParcel
public abstract class PagingResponse<T> implements Parcelable {

    public abstract int count();
    @Nullable
    public abstract String next();
    @Nullable
    public abstract String previous();
    public abstract ArrayList<T> results();

    @GsonConstructor
    public static <T> PagingResponse<T> create(int count, String next, String previous, ArrayList<T> results) {
        return new AutoParcel_PagingResponse<>(count, next, previous, results);
    }

}
