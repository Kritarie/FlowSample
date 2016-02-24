package net.seanamos.flowsample.data.network.response;

import android.os.Parcelable;

import net.seanamos.flowsample.data.model.Planet;

import java.util.ArrayList;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class PagingResponsePlanets implements Parcelable {

    public abstract int count();
    public abstract String next();
    public abstract String previous();
    public abstract ArrayList<Planet> results();

}