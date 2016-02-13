package net.seanamos.flowsample.core.dagger;

import android.content.Context;
import android.content.ContextWrapper;
import android.text.TextUtils;
import android.view.LayoutInflater;

import net.seanamos.flowsample.ui.screen.Screen;

import flow.path.Path;
import flow.path.PathContextFactory;
import mortar.MortarScope;

public class DaggerContextFactory implements PathContextFactory {

    @Override
    public Context setUpContext(Path path, Context parentContext) {
        Screen screen = path.getClass().getAnnotation(Screen.class);
        if (screen == null)
            throw new IllegalArgumentException(path.getClass().getName() + " must have @Screen annotation");
        String name = screen.name();
        if (TextUtils.isEmpty(name)) {
            name = path.getClass().getName();
        }
        MortarScope scope = getScreenScope(parentContext, name, path);
        return new MortarScopeContext(parentContext, scope);
    }

    @Override
    public void tearDownContext(Context context) {

    }

    private MortarScope getScreenScope(Context context, String name, Path path) {
        MortarScope parentScope = MortarScope.getScope(context);

        MortarScope childScope = parentScope.findChild(name);
        if (childScope != null) {
            return childScope;
        }

        if (!(path instanceof ScreenComponentFactory)) {
            throw new IllegalStateException("Path must implement ComponentFactory");
        }
        ScreenComponentFactory screenComponentFactory = (ScreenComponentFactory) path;

        @SuppressWarnings("unchecked")
        Object component = screenComponentFactory.buildComponent(parentScope.getService(DaggerService.SERVICE_NAME));

        MortarScope.Builder builder = parentScope.buildChild()
                .withService(DaggerService.SERVICE_NAME, component);

        return builder.build(name);
    }

    private static class MortarScopeContext extends ContextWrapper {

        private static final String SERVICE_NAME = "SNEAKY_MORTAR_PARENT_HOOK";

        private final MortarScope parentScope;
        private LayoutInflater inflater;

        public MortarScopeContext(Context base, MortarScope scope) {
            super(scope.createContext(base));
            this.parentScope = MortarScope.getScope(base);
        }

        @Override
        public Object getSystemService(String name) {
            if (name.equals(LAYOUT_INFLATER_SERVICE)) {
                if (inflater == null) {
                    inflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
                }
                return inflater;
            }

            if (name.equals(SERVICE_NAME)) {
                return parentScope;
            }

            return super.getSystemService(name);
        }
    }
}
