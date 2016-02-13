package net.seanamos.flowsample.ui.common;

import android.animation.Animator;

import flow.Flow;
import flow.path.Path;

public interface TransitionIn {
    Animator transitionIn(Path to, Flow.Direction direction);
}
