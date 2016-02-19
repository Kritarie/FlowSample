package net.seanamos.flowsample.core;

import android.content.Context;
import android.content.ContextWrapper;

import mortar.MortarScope;

public class MortarScopeContext extends ContextWrapper {

    private static final String SERVICE_NAME = "SneakyMortarParentHook";

    private final MortarScope parentScope;

    public MortarScopeContext(Context base, MortarScope scope) {
        super(scope.createContext(base));
        this.parentScope = MortarScope.getScope(base);
    }

    @Override
    public Object getSystemService(String name) {
        if (name.equals(SERVICE_NAME)) {
            return parentScope;
        }

        return parentScope.hasService(name) ? parentScope.getService(name) : super.getSystemService(name);
    }
}
