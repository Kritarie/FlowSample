package net.seanamos.flowsample.ui.common;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.support.annotation.LayoutRes;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.seanamos.flowsample.ui.screen.Screen;
import net.seanamos.flowsample.ui.UiUtil;

import java.util.Map;

import flow.Flow;
import flow.path.Path;
import flow.path.PathContainer;
import flow.path.PathContext;
import flow.path.PathContextFactory;

import static flow.Flow.Direction.REPLACE;

public class SimplePathContainer extends PathContainer {

    private static final Map<Class, Integer> PATH_LAYOUT_CACHE = new ArrayMap<>();
    private final PathContextFactory pathContextFactory;

    protected SimplePathContainer(int tagKey, PathContextFactory pathContextFactory) {
        super(tagKey);
        this.pathContextFactory = pathContextFactory;
    }

    @Override
    protected void performTraversal(final ViewGroup containerView, final TraversalState traversalState,
                                    final Flow.Direction direction, final Flow.TraversalCallback callback) {

        final PathContext context;
        final PathContext oldPath;
        if (containerView.getChildCount() > 0) {
            oldPath = PathContext.get(containerView.getChildAt(0).getContext());
        } else {
            oldPath = PathContext.root(containerView.getContext());
        }

        final Path destination = traversalState.toPath();

        final View destinationView;
        context = PathContext.create(oldPath, destination, pathContextFactory);
        // Get the layout res from the destination screen's annotation
        @LayoutRes int layout = getLayout(destination);
        // Now we know the layout res, let's inflate it
        destinationView = LayoutInflater
                .from(context)
                .cloneInContext(context)
                .inflate(layout, containerView, false);

        // Before we switch em out, save state of the old view
        View originView = null;
        final Path origin = traversalState.fromPath();
        if (origin != null) {
            originView = containerView.getChildAt(0);
            traversalState.saveViewState(originView);
        }
        // Restore the state of the destination view if it has any
        traversalState.restoreViewState(destinationView);

        // Now we switch them out
        if (originView == null || direction == REPLACE) {
            // No need for animation in this case, since there is no previous view
            containerView.removeAllViews();
            containerView.addView(destinationView);
            oldPath.destroyNotIn(context, pathContextFactory);
            callback.onTraversalCompleted();
        } else {
            containerView.addView(destinationView);
            // At this point both the origin and destination views are on screen. Animate!
            final View finalOriginView = originView;
            UiUtil.waitForMeasure(destinationView, new UiUtil.OnMeasured() {
                @Override
                public void onMeasured(View view, int width, int height) {
                    AnimatorSet set = new AnimatorSet();
                    if (finalOriginView instanceof TransitionOut) {
                        set.play(((TransitionOut) finalOriginView).transitionOut(destination, direction));
                    }
                    if (destinationView instanceof TransitionIn) {
                        set.play(((TransitionIn) destinationView).transitionIn(origin, direction));
                    }
                    set.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            // Called when the animation is complete (if well-behaved), so remove the origin view
                            containerView.removeView(finalOriginView);
                            oldPath.destroyNotIn(context, pathContextFactory);
                            callback.onTraversalCompleted();
                        }
                    });
                    set.start();
                }
            });
        }
    }

    @LayoutRes
    private int getLayout(Path path) {
        Class pathType = path.getClass();
        @LayoutRes Integer layoutRes = PATH_LAYOUT_CACHE.get(pathType);
        if (layoutRes == null) {
            // Couldn't find in cache, let's get it from the annotation
            Screen screen = (Screen) pathType.getAnnotation(Screen.class);
            if (screen == null) {
                // Path (Screen) didn't have layout annotation
                throw new IllegalArgumentException(
                        String.format("@%s annotation not found on class %s", Screen.class.getSimpleName(),
                                pathType.getName()));
            }
            layoutRes = screen.layout();
            // Stuff it in the cache for next time
            PATH_LAYOUT_CACHE.put(pathType, layoutRes);
        }
        return layoutRes;
    }
}
