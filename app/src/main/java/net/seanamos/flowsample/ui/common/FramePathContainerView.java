package net.seanamos.flowsample.ui.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.dagger.DaggerContextFactory;

import flow.Flow;
import flow.path.Path;
import flow.path.PathContainer;
import flow.path.PathContainerView;

public class FramePathContainerView extends FrameLayout implements PathContainerView {

    @NonNull
    private final PathContainer pathContainer;

    @SuppressWarnings("UnusedDeclaration") // Used by layout inflation
    public FramePathContainerView(Context context, AttributeSet attrs) {
        this(context, attrs, new SimplePathContainer(R.id.screen_switcher_tag, Path.contextFactory(new DaggerContextFactory())));
    }

    /**
     * Allows subclasses to use custom {@link flow.path.PathContainer} implementations. Allows the use
     * of more sophisticated transition schemes, and customized context wrappers.
     */
    protected FramePathContainerView(Context context, AttributeSet attrs, @NonNull PathContainer pathContainer) {
        super(context, attrs);
        this.pathContainer = pathContainer;
    }

    @Override
    public ViewGroup getCurrentChild() {
        return (ViewGroup) getContainerView().getChildAt(0);
    }

    @Override
    public ViewGroup getContainerView() {
        return this;
    }

    // Override to provide custom screen animations
    @Override
    public void dispatch(Flow.Traversal traversal, Flow.TraversalCallback callback) {
        pathContainer.executeTraversal(this, traversal, callback);
    }
}
