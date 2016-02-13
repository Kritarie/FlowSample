package net.seanamos.flowsample.ui;

import android.view.View;
import android.view.ViewTreeObserver;

public final class UiUtil {

    public interface OnMeasured {
        void onMeasured(View view, int width, int height);
    }

    public static void waitForMeasure(final View view, final OnMeasured callback) {
        int width = view.getWidth();
        int height = view.getHeight();

        if (width > 0 && height > 0) {
            callback.onMeasured(view, width, height);
            return;
        }

        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override public boolean onPreDraw() {
                final ViewTreeObserver observer = view.getViewTreeObserver();
                if (observer.isAlive()) {
                    observer.removeOnPreDrawListener(this);
                }

                callback.onMeasured(view, view.getWidth(), view.getHeight());

                return true;
            }
        });
    }

    private UiUtil() {
        throw new AssertionError("No instances.");
    }
}
