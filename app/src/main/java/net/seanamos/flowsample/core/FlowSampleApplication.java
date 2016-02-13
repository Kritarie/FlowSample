package net.seanamos.flowsample.core;

import android.app.Application;

import net.seanamos.flowsample.data.api.ApiModule;
import net.seanamos.flowsample.core.dagger.DaggerService;

import mortar.MortarScope;

public class FlowSampleApplication extends Application {

    private MortarScope scope;

    @Override
    public Object getSystemService(String name) {
        if (scope == null) {
            scope = MortarScope.buildRootScope()
                    .withService(DaggerService.SERVICE_NAME, buildApplicationComponent())
                    .build("Root");
        }
        if (scope.hasService(name)) return scope.getService(name);
        return super.getSystemService(name);
    }

    protected ApplicationComponent buildApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .apiModule(new ApiModule())
                .build();
    }
}
