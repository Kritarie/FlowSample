package net.seanamos.flowsample.core.flow;

import android.os.Parcelable;

import flow.KeyParceler;

/**
 * Assumes states are {@link Parcelable} due to AutoParcel
 */
public class AutoKeyParceler implements KeyParceler {

    @Override
    public Parcelable toParcelable(Object key) {
        return (Parcelable) key;
    }

    @Override
    public Object toKey(Parcelable parcelable) {
        return parcelable;
    }

}
