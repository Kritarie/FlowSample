package net.seanamos.flowsample.core.parcel;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import flow.StateParceler;

public class GsonParceler implements StateParceler {

    private static final String TAG = GsonParceler.class.getSimpleName();

    @NonNull
    private final Gson gson;

    public GsonParceler(@NonNull Gson gson) {
        this.gson = gson;
    }

    @Override
    public Parcelable wrap(Object instance) {
        try {
            String json = encode(instance);
            return new GsonParcelWrapper(json);
        } catch (IOException e) {
            Log.e(TAG, "Could not serialize state for " + instance, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object unwrap(Parcelable parcelable) {
        GsonParcelWrapper wrapper = (GsonParcelWrapper) parcelable;
        try {
            return decode(wrapper.getJson());
        } catch (IOException e) {
            Log.e(TAG, "Could not deserialize state for " + wrapper.getJson(), e);
            throw new RuntimeException(e);
        }
    }

    private String encode(Object instance) throws IOException {
        StringWriter stringWriter = new StringWriter();
        JsonWriter writer = new JsonWriter(stringWriter);

        try {
            Class<?> type = instance.getClass();

            writer.beginObject();
            writer.name(type.getName());
            gson.toJson(instance, type, writer);
            writer.endObject();

            return stringWriter.toString();
        } finally {
            writer.close();
        }
    }

    private Object decode(String json) throws IOException {
        JsonReader reader = new JsonReader(new StringReader(json));

        try {
            reader.beginObject();

            Class<?> type = Class.forName(reader.nextName());
            return gson.fromJson(reader, type);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            reader.close();
        }
    }
}
