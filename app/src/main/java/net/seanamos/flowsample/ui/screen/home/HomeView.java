package net.seanamos.flowsample.ui.screen.home;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import net.seanamos.flowsample.ui.common.TransitionOut;

import flow.Flow;
import flow.path.Path;

public class HomeView extends FrameLayout implements TransitionOut {

    public HomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public Animator transitionOut(Path to, Flow.Direction direction) {
        return null;
    }
}
