package net.seanamos.flowsample.core.dagger;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import flow.Flow;
import mortar.MortarScope;

public final class DaggerService {

    public static final String SERVICE_NAME = DaggerService.class.getSimpleName();

    @SuppressWarnings("unchecked")
    public static <T> T getComponentForContext(@NonNull Context context) {
        //noinspection ResourceType
        return (T) context.getSystemService(SERVICE_NAME);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getComponentForFlow(@NonNull View view) {
        return getComponentForFlow(view.getContext());
    }

    @SuppressWarnings("unchecked")
    public static <T> T getComponentForFlow(@NonNull Context context) {
        return (T) Flow.getService(SERVICE_NAME, context);
    }

}
