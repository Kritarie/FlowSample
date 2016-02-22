package net.seanamos.flowsample.core.flow;

import android.util.Log;

import net.seanamos.flowsample.core.dagger.DaggerService;
import net.seanamos.flowsample.core.dagger.ScreenComponentFactory;

import java.util.Map;

import flow.Services;
import flow.ServicesFactory;
import mortar.MortarScope;

public class FlowServices extends ServicesFactory {

    private static final String TAG = "FlowServices";

    private final MortarScope root;

    public FlowServices(MortarScope root) {
        this.root = root;
    }

    @Override
    public void bindServices(Services.Binder services) {
        ScreenComponentFactory key = services.getKey();
        if (!root.hasService(key.toString())) {
            @SuppressWarnings("unchecked")
            Object component = key.buildComponent(root.getService(DaggerService.SERVICE_NAME));
            MortarScope childScope = root.findChild(key.toString());
            if (childScope == null) {
                Log.d(TAG, "Setting up " + key);
                root.buildChild()
                        .withService(DaggerService.SERVICE_NAME, component)
                        .build(key.toString());
            }
            services.bind(DaggerService.SERVICE_NAME, component);
        }
    }

    @Override
    public void tearDownServices(Services services) {
        Object key = services.getKey();
        MortarScope childScope = root.findChild(key.toString());
        if (childScope != null) {
            Log.d(TAG, "Tearing down " + key);
            childScope.destroy();
        }
    }
}
