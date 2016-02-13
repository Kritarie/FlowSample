package net.seanamos.flowsample.core.parcel;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public final class GsonParcelWrapper implements Parcelable {

    @NonNull
    private final String json;

    public GsonParcelWrapper(@NonNull String json) {
        this.json = json;
    }

    @NonNull
    public String getJson() {
        return this.json;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.json);
    }

    protected GsonParcelWrapper(Parcel in) {
        this.json = in.readString();
    }

    public static final Parcelable.Creator<GsonParcelWrapper> CREATOR = new Parcelable.Creator<GsonParcelWrapper>() {
        public GsonParcelWrapper createFromParcel(Parcel source) {
            return new GsonParcelWrapper(source);
        }

        public GsonParcelWrapper[] newArray(int size) {
            return new GsonParcelWrapper[size];
        }
    };
}
