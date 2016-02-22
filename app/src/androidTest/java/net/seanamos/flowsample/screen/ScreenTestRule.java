package net.seanamos.flowsample.screen;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.UiThreadTestRule;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;

import com.squareup.spoon.Spoon;

import net.seanamos.flowsample.ui.FlowSampleActivity;
import net.seanamos.flowsample.core.dagger.DaggerService;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import flow.History;

public class ScreenTestRule extends UiThreadTestRule {

    private static final int SHOW_SCREEN_FLAGS =
            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                    | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD;

    private static final String TAG = "ActivityTestRule";
    private Instrumentation instrumentation;
    private Activity activity;
    private final Object testClassInstance;

    public ScreenTestRule(Object testClassInstance) {
        this.testClassInstance = testClassInstance;
        instrumentation = InstrumentationRegistry.getInstrumentation();
    }

    protected Intent getActivityIntent() {
        return new Intent("android.intent.action.MAIN");
    }

    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    activity = launchActivity(getActivityIntent());
                    WithHistory withHistory = description.getAnnotation(WithHistory.class);
                    if (withHistory == null || TextUtils.isEmpty(withHistory.value())) {
                        throw new IllegalStateException("Test method must be annotated @WithHistory");
                    }
                    History history = (History) testClassInstance.getClass().getMethod(withHistory.value()).invoke(testClassInstance);
                    DaggerService.<ActivityComponent>getComponentForContext(activity)
                            .initialHistory().set(history);
                    instrumentation.waitForIdleSync();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activity.getWindow().addFlags(SHOW_SCREEN_FLAGS);
                        }
                    });
                    SystemClock.sleep(1000);
                    base.evaluate();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activity.getWindow().addFlags(SHOW_SCREEN_FLAGS);
                        }
                    });
                } finally {
                    //TODO screenshot here
                    Screenshot screenshot = description.getAnnotation(Screenshot.class);
                    if (screenshot != null) {
                        String name = screenshot.name();
                        if (TextUtils.isEmpty(name)) {
                            name = description.getDisplayName();
                        }
                        Spoon.screenshot(activity, name, description.getTestClass().getName(), description.getMethodName());
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activity.getWindow().clearFlags(SHOW_SCREEN_FLAGS);
                        }
                    });
                    finishActivity();
                }
            }
        };
    }

    public Activity launchActivity(@Nullable Intent startIntent) {
        String targetPackage = instrumentation.getTargetContext().getPackageName();
        if(null == startIntent) {
            startIntent = this.getActivityIntent();
            if(null == startIntent) {
                Log.w(TAG, "getActivityIntent() returned null using default: Intent(Intent.ACTION_MAIN)");
                startIntent = new Intent("android.intent.action.MAIN");
            }
        }

        //TODO startIntent.putExtras(mArgs);
        startIntent.setClassName(targetPackage, FlowSampleActivity.class.getName());
        startIntent.addFlags(268435456);
        Log.d(TAG, String.format("Launching activity %s", FlowSampleActivity.class.getName()));
        FlowSampleActivity activity = FlowSampleActivity.class.cast(instrumentation.startActivitySync(startIntent));
        return activity;
    }

    void finishActivity() {
        if(this.activity != null) {
            this.activity.finish();
            this.activity = null;
        }

    }

}
