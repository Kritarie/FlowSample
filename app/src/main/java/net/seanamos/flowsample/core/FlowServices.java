package net.seanamos.flowsample.core;

import android.util.Log;

import net.seanamos.flowsample.core.dagger.DaggerService;
import net.seanamos.flowsample.core.dagger.ScreenComponentFactory;

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
        MortarScope parentScope = root.findChild("Activity");
        Log.d(TAG, "Setting up " + key);
        if (!parentScope.hasService(key.toString())) {
            @SuppressWarnings("unchecked")
            Object component = key.buildComponent(parentScope.getService(DaggerService.SERVICE_NAME));
            MortarScope childScope = parentScope.buildChild()
                    .withService(DaggerService.SERVICE_NAME, component)
                    .build(key.toString());
            services.bind(DaggerService.SERVICE_NAME, childScope.getService(DaggerService.SERVICE_NAME));
        }
    }

    @Override
    public void tearDownServices(Services services) {
        Object key = services.getKey();
        Log.d(TAG, "Tearing down " + key);
        MortarScope parentScope = root.findChild("Activity");
        MortarScope childScope = parentScope.findChild(key.toString());
        if (childScope != null) {
            childScope.destroy();
            Log.d(TAG, childScope + " destroyed");
        }
    }
}
