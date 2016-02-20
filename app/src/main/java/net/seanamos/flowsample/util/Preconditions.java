package net.seanamos.flowsample.util;

import android.support.annotation.Nullable;

import java.util.Collection;

public final class Preconditions {

    public static boolean isNullOrEmpty(@Nullable Collection items) {
        return items == null || items.isEmpty();
    }

    private Preconditions() {
        throw new AssertionError("No instances.");
    }
}
