package net.seanamos.flowsample.data.network;

import android.support.annotation.NonNull;

import java.util.concurrent.atomic.AtomicReference;

import okhttp3.HttpUrl;
import retrofit2.BaseUrl;

public class SWUrl implements BaseUrl {

    public static final String DEFAULT = "https://swapi.co";

    @NonNull
    private final AtomicReference<HttpUrl> url;

    public SWUrl() {
        this(DEFAULT);
    }

    public SWUrl(@NonNull String url) {
        this(HttpUrl.parse(url));
    }

    public SWUrl(@NonNull HttpUrl url) {
        this.url = new AtomicReference<>(url);
    }

    public void set(@NonNull String url) {
        this.url.set(HttpUrl.parse(url));
    }

    @Override
    public HttpUrl url() {
        return url.get();
    }
}
