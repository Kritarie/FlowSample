package net.seanamos.flowsample.ui.toolbar;

import android.support.annotation.NonNull;

public class ToolbarController {

    private Activity activity;

    public void takeActivity(@NonNull Activity activity) {
        if (this.activity != null) throw new IllegalStateException("An activity is already set");
        this.activity = activity;
    }

    public void dropActivity(@NonNull Activity activity) {
        if (this.activity == null) throw new IllegalStateException("No activity to drop");
        if (this.activity != activity) throw new IllegalArgumentException("Can not drop the given activity");
        this.activity = null;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public interface Activity {

    }
}
