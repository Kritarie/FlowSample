package net.seanamos.flowsample.core;

import android.app.Application;
import android.content.Context;

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

    @Override
    public void onCreate() {
        super.onCreate();
        scope = buildMortarScope();
    }

    protected MortarScope buildMortarScope() {
        return MortarScope.buildRootScope()
                .withService(DaggerService.SERVICE_NAME, buildApplicationComponent())
                .build("Root");
    }

    protected ApplicationComponent buildApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, scope))
                .build();
    }

}
