package net.seanamos.flowsample.core;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.dagger.DaggerService;
import net.seanamos.flowsample.ui.ActivityComponent;
import net.seanamos.flowsample.ui.ActivityModule;

import flow.Flow;
import flow.FlowDelegate;
import flow.History;
import flow.StateParceler;
import flow.path.PathContainerView;
import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;

public class FlowSampleActivity extends AppCompatActivity implements Flow.Dispatcher {

    private MortarScope scope;
    private FlowDelegate flowDelegate;
    private PathContainerView flowContainer;

    @Override
    public Object getSystemService(@NonNull String name) {
        if (scope == null) {
            scope = MortarScope.buildRootScope()
                    .withService(DaggerService.SERVICE_NAME,
                            buildActivityComponent(DaggerService.<ApplicationComponent>
                                    getApplicationComponent(this)))
                    .withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner())
                    .build("Root");
        }
        if (scope.hasService(name)) return scope.getService(name);
        if (flowDelegate != null) {
            Object flowService = flowDelegate.getSystemService(name);
            if (flowService != null) {
                return flowService;
            }
        }
        return super.getSystemService(name);
    }

    protected ActivityComponent buildActivityComponent(ApplicationComponent parent) {
        return parent.plus(new ActivityModule(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root);
        flowContainer = (PathContainerView) findViewById(R.id.root_layout);

        BundleServiceRunner.getBundleServiceRunner(this).onCreate(savedInstanceState);

        FlowDelegate.NonConfigurationInstance nonConfig =
                (FlowDelegate.NonConfigurationInstance) getLastCustomNonConfigurationInstance();

        ActivityComponent component = DaggerService.getComponentForContext(this);
        StateParceler parceler = component.parceler();
        History history = component.initialHistory().get();

        flowDelegate = FlowDelegate.onCreate(nonConfig, getIntent(),
                savedInstanceState, parceler, history, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        flowDelegate.onResume();
    }

    @Override
    protected void onPause() {
        flowDelegate.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (isFinishing()) {
            MortarScope activityScope = MortarScope.findChild(getApplicationContext(), getClass().getName());
            if (activityScope != null) {
                activityScope.destroy();
            }
        }
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        flowDelegate.onNewIntent(intent);
    }

    @Override public void onBackPressed() {
        if (!flowDelegate.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BundleServiceRunner.getBundleServiceRunner(this).onSaveInstanceState(outState);
        flowDelegate.onSaveInstanceState(outState);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return flowDelegate.onRetainNonConfigurationInstance();
    }

    @Override
    public void dispatch(Flow.Traversal traversal, Flow.TraversalCallback callback) {
        flowContainer.dispatch(traversal, callback);
    }
}
