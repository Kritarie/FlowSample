package net.seanamos.flowsample.core.dagger;

public interface ScreenComponentFactory<T> {
    Object buildComponent(T parent);
}
