package net.seanamos.flowsample.core.dagger;

import android.support.annotation.NonNull;

public interface ScreenComponentFactory<T> {
    @NonNull
    Object buildComponent(@NonNull T parent);
}
