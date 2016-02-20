package net.seanamos.flowsample.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.ApplicationComponent;
import net.seanamos.flowsample.core.FlowServices;
import net.seanamos.flowsample.core.dagger.DaggerService;
import net.seanamos.flowsample.ui.ActivityComponent;
import net.seanamos.flowsample.ui.ActivityModule;
import net.seanamos.flowsample.ui.screen.Screen;

import java.util.Map;

import flow.Dispatcher;
import flow.Flow;
import flow.History;
import flow.KeyChanger;
import flow.KeyDispatcher;
import flow.KeyParceler;
import flow.Traversal;
import flow.TraversalCallback;
import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;

public class FlowSampleActivity extends AppCompatActivity implements Dispatcher {

    private static final Map<Class, Integer> PATH_LAYOUT_CACHE = new ArrayMap<>();

    private MortarScope scope;

    @Override
    public Object getSystemService(@NonNull String name) {
        // If Activity scope is null, build it
        if (scope == null) {
            MortarScope parentScope = MortarScope.getScope(getApplicationContext());
            scope = parentScope.findChild("Activity");
            if (scope == null) {
                scope = parentScope.buildChild()
                        .withService(DaggerService.SERVICE_NAME,
                                buildActivityComponent(DaggerService.<ApplicationComponent>
                                        getComponent(getApplicationContext())))
                        .withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner())
                        .build("Activity");
            }
        }
        // Check mortar for service
        if (scope.hasService(name)){
            return scope.getService(name);
        }

        // Default
        return super.getSystemService(name);
    }

    protected ActivityComponent buildActivityComponent(ApplicationComponent parent) {
        return parent.plus(new ActivityModule(this));
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Context application = newBase.getApplicationContext();
        ApplicationComponent component = DaggerService.<ApplicationComponent>getComponent(application);
        History history = component.initialHistory().get();
        KeyParceler keyParceler = component.parceler();
        newBase = Flow.configure(newBase, this)
                .addServicesFactory(new FlowServices(MortarScope.getScope(application)))
                .defaultKey(history.top())
                .keyParceler(keyParceler)
                .dispatcher(this)
                .install();
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BundleServiceRunner.getBundleServiceRunner(this).onCreate(savedInstanceState);
        setContentView(R.layout.root);
    }

    @Override
    protected void onDestroy() {
        if (isFinishing()) {
            MortarScope activityScope = MortarScope.findChild(getApplicationContext(), "Activity");
            if (activityScope != null) {
                activityScope.destroy();
            }
        }
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Flow.onNewIntent(intent, this);
    }

    @Override public void onBackPressed() {
        if (!Flow.get(this).goBack()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BundleServiceRunner.getBundleServiceRunner(this).onSaveInstanceState(outState);
    }

    @Override
    public void dispatch(Traversal traversal, TraversalCallback callback) {
        ViewGroup frame = (ViewGroup) findViewById(R.id.root_layout);

        History history = traversal.origin;
        Object destination = traversal.destination.top();

        Class clazz = destination.getClass().getSuperclass();
        @LayoutRes Integer layoutRes = PATH_LAYOUT_CACHE.get(clazz);
        if (layoutRes == null) {
            Screen screen = (Screen) clazz.getAnnotation(Screen.class);
            layoutRes = screen.layout();
            PATH_LAYOUT_CACHE.put(clazz, layoutRes);
        }
        Context incomingContext = traversal.createContext(destination, this);
        View incomingView = LayoutInflater.from(incomingContext).inflate(layoutRes, frame, false);
        traversal.getState(destination).restore(incomingView);

        if (history != null) {
            Object origin = history.top();
            traversal.getState(origin).save(frame.getChildAt(0));
        }

        frame.removeAllViews();
        frame.addView(incomingView);

        Log.d("FlowSampleActivity", "Dispatching to " + destination);
        callback.onTraversalCompleted();
    }
}
