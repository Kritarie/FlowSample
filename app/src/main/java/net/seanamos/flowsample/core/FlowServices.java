package net.seanamos.flowsample.core;

import android.util.Log;

import net.seanamos.flowsample.core.dagger.DaggerService;
import net.seanamos.flowsample.core.dagger.ScreenComponentFactory;

import flow.Services;
import flow.ServicesFactory;
import mortar.MortarScope;

public class FlowServices extends ServicesFactory {

    private static final String TAG = "FlowServices";

    private final MortarScope parentScope;

    public FlowServices(MortarScope parentScope) {
        this.parentScope = parentScope;
    }

    @Override
    public void bindServices(Services.Binder services) {
        Object key = services.getKey();
        Log.d(TAG, "Setting up " + key);
        if (key instanceof ScreenComponentFactory) {
            //Object parentComponent = services.getService(DaggerService.SERVICE_NAME);
            Object parentComponent = parentScope.getService(DaggerService.SERVICE_NAME);
            ScreenComponentFactory factory = (ScreenComponentFactory) key;
            @SuppressWarnings("unchecked")
            Object component = factory.buildComponent(parentComponent);
            parentScope.buildChild()
                    .withService(DaggerService.SERVICE_NAME, component)
                    .build(key.toString());
            services.bind(DaggerService.SERVICE_NAME, component);
        }
    }

    @Override
    public void tearDownServices(Services services) {
        Object key = services.getKey();
        Log.d(TAG, "Tearing down " + key);
        MortarScope childScope = parentScope.findChild(key.toString());
        if (childScope == null) throw new IllegalStateException("Scope for key " + key + " was already destroyed!");
        childScope.destroy();
    }
}
