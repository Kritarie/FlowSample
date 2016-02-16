package net.seanamos.flowsample.core.dagger;

import android.content.Context;
import android.support.annotation.NonNull;

public final class DaggerService {

    public static final String SERVICE_NAME = DaggerService.class.getSimpleName();

    @SuppressWarnings("unchecked")
    public static <T> T getComponentForContext(@NonNull Context context) {
        //noinspection ResourceType
        return (T) context.getSystemService(SERVICE_NAME);
    }

}
