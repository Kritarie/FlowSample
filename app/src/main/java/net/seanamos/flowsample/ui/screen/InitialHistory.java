package net.seanamos.flowsample.ui.screen;

import android.support.annotation.NonNull;

import java.util.concurrent.atomic.AtomicReference;

import flow.History;

public final class InitialHistory {

    @NonNull
    private AtomicReference<History> initialHistory;

    public InitialHistory(@NonNull History initialHistory) {
        this.initialHistory = new AtomicReference<>(initialHistory);
    }

    @NonNull
    public History get() {
        return initialHistory.get();
    }

    public void set(@NonNull History initialHistory) {
        this.initialHistory = new AtomicReference<>(initialHistory);
    }

}
