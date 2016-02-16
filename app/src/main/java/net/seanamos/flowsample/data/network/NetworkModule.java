package net.seanamos.flowsample.data.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

import net.seanamos.flowsample.BuildConfig;
import net.seanamos.flowsample.core.ApplicationModule;
import net.seanamos.flowsample.core.ApplicationScope;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.BaseUrl;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final String TAG = "NetworkModule";
    private static final int GLOBAL_TIMEOUT = 30; // seconds

    public static final String NETWORK_CACHE = "network_cache";

    @Provides @ApplicationScope @NonNull @Named(NETWORK_CACHE)
    public File provideNetworkCacheDirectory(@NonNull @Named(ApplicationModule.CONTEXT_NAME) Context context) {
        return context.getDir(NETWORK_CACHE, Context.MODE_PRIVATE);
    }

    @Provides @ApplicationScope @NonNull
    public Cache provideNetworkCache(@NonNull @Named(NETWORK_CACHE) File cacheDir) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(cacheDir, cacheSize);
    }

    @Provides @ApplicationScope @NonNull
    public OkHttpClient provideHttpClient(@NonNull Cache cache) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(GLOBAL_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(GLOBAL_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(GLOBAL_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    @Provides @ApplicationScope @NonNull
    public BaseUrl provideStarWarsUrl() {
        return new SWUrl();
    }

    @Provides @ApplicationScope @NonNull
    public SWService provideStarWarsService(@NonNull OkHttpClient client, @NonNull BaseUrl url, @NonNull Gson gson) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .validateEagerly(BuildConfig.DEBUG)
                .build()
                .create(SWService.class);
    }
}
