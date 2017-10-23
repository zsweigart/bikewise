package com.zsweigart.core_components;

import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsweigart on 10/19/17.
 */
public class Router<C extends InteractorComponent, I extends Interactor> {

    private final List<Router> children = new ArrayList<>();
    private final C component;
    private final I interactor;

    private boolean isLoaded;


    @SuppressWarnings("unchecked")
    public Router(C component, I interactor) {
        this.component = component;
        this.interactor = interactor;
        component.inject(interactor);
        interactor.setRouter(this);
    }

    @CallSuper
    protected void willAttach() {}

    @CallSuper
    protected void willDetach() {}

    @MainThread
    protected void attachChild(Router<?, ?> childRouter) {
        children.add(childRouter);
        childRouter.dispatchAttach();
    }

    @MainThread
    protected void detachChild(Router childRouter) {
        children.remove(childRouter);
        childRouter.dispatchDetach();
    }

    @CallSuper
    protected void dispatchAttach() {
        if (!isLoaded) {
            isLoaded = true;
        }

        willAttach();

        interactor.dispatchAttach();
    }

    @CallSuper
    protected void dispatchDetach() {
        interactor.dispatchDetach();
        willDetach();

        for (Router child : children) {
            detachChild(child);
        }
    }

    public I getInteractor() {
        return interactor;
    }

    public C getComponent() {
        return component;
    }
}
