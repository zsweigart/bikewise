package com.zsweigart.core_components;

import android.support.annotation.CallSuper;
import android.view.View;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.jakewharton.rxrelay2.Relay;
import com.uber.autodispose.ScopeProvider;
import com.zsweigart.core_components.lifecycle.PresenterEvent;

import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Created by zsweigart on 10/19/17.
 */
public class Presenter<V extends View> implements ScopeProvider {

    private final BehaviorRelay<PresenterEvent> behaviorRelay = BehaviorRelay.create();
    private final Relay<PresenterEvent> lifecycleRelay = behaviorRelay.toSerialized();

    private boolean isLoaded = false;

    protected final V view;

    public Presenter(V view) {
        this.view = view;
    }

    @CallSuper
    protected void didLoad() {}

    @CallSuper
    protected void willUnload() {}

    protected void dispatchLoad() {
        isLoaded = true;
        lifecycleRelay.accept(PresenterEvent.LOADED);

        didLoad();
    }

    protected void dispatchUnload() {
        isLoaded = false;
        willUnload();

        lifecycleRelay.accept(PresenterEvent.UNLOADED);
    }

    public Observable<PresenterEvent> lifecycle() {
        return lifecycleRelay.hide();
    }

    @Override
    public Maybe<?> requestScope() {
        return lifecycleRelay.skip(1).firstElement();
    }

    public V getView() {
        return view;
    }
}
