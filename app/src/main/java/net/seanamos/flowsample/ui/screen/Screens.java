package net.seanamos.flowsample.ui.screen;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

public final class Screens {

    public static View createView(@NonNull Context context, @NonNull Object screen) {
        return createView(context, screen.getClass());
    }

    public static View createView(@NonNull Context context, @NonNull Class<?> screenType) {
        Screen screen = screenType.getAnnotation(Screen.class);
        if (screen == null) {
            throw new IllegalArgumentException(
                    String.format("@%s annotation not found on class %s", Screen.class.getSimpleName(),
                            screenType.getName()));
        }

        int layout = screen.layout();
        return inflateLayout(context, layout);
    }

    private static View inflateLayout(@NonNull Context context, @LayoutRes int layoutId) {
        return LayoutInflater.from(context).inflate(layoutId, null);
    }

    private Screens() {
        throw new AssertionError("No instances.");
    }
}
