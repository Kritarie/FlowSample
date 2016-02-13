package net.seanamos.flowsample.ui.common;

import android.animation.Animator;

import flow.Flow;
import flow.path.Path;

public interface TransitionOut {
    Animator transitionOut(Path to, Flow.Direction direction);
}
