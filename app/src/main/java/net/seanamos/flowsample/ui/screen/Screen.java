package net.seanamos.flowsample.ui.screen;

import android.support.annotation.LayoutRes;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Marks a class that designates a screen and specifies its layout and name. A screen is a distinct part of
 * an application containing all information that describes this state.
 *
 * <p>For example, <pre><code>
 * {@literal@}Screen(layout = R.layout.conversation_screen_layout, name = "ConversationScreen")
 * public class ConversationScreen { ... }
 * </code></pre>
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface Screen {
    @LayoutRes int layout();
    String name() default("");
}
