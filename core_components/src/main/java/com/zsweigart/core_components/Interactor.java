package com.zsweigart.core_components;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.jakewharton.rxrelay2.Relay;
import com.uber.autodispose.LifecycleEndedException;
import com.uber.autodispose.LifecycleScopeProvider;
import com.zsweigart.core_components.lifecycle.InteractorEvent;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by zsweigart on 10/18/17.
 */
public class Interactor<R extends Router> implements LifecycleScopeProvider<InteractorEvent> {

    @Nullable private R router;

    private final BehaviorRelay<InteractorEvent> behaviorRelay = BehaviorRelay.create();
    private final Relay<InteractorEvent> lifecycleRelay = behaviorRelay.toSerialized();

    private static final Function<InteractorEvent, InteractorEvent> LIFECYCLE_MAP_FUNCTION =
            new Function<InteractorEvent, InteractorEvent>() {
                @Override
                public InteractorEvent apply(InteractorEvent interactorEvent) {
                    switch (interactorEvent) {
                        case ACTIVE:
                            return InteractorEvent.INACTIVE;
                        default:
                            throw new LifecycleEndedException();
                    }
                }
            };

    void setRouter(R router) {
        this.router = router;
    }

    @CallSuper
    protected void didBecomeActive() { }

    @CallSuper
    protected void willResignActive() { }

    void dispatchAttach() {
        lifecycleRelay.accept(InteractorEvent.ACTIVE);
        didBecomeActive();
    }

    void dispatchDetach() {
        lifecycleRelay.accept(InteractorEvent.INACTIVE);
        willResignActive();
    }

    @Override
    public Observable<InteractorEvent> lifecycle() {
        return lifecycleRelay.hide();
    }

    @Override
    public Function<InteractorEvent, InteractorEvent> correspondingEvents() {
        return LIFECYCLE_MAP_FUNCTION;
    }

    @Override
    public InteractorEvent peekLifecycle() {
        return behaviorRelay.getValue();
    }

    @NonNull
    public R getRouter() {
        if (router == null) {
            throw new IllegalStateException("Attempting to get interactor's router before being set.");
        }
        return router;
    }
}
