package com.zsweigart.core_components;

import android.view.View;

/**
 * Created by zsweigart on 10/20/17.
 */
public class ViewRouter<V extends View, C extends InteractorComponent, I extends ViewInteractor> extends Router<C, I> {

    private final V view;

    public ViewRouter(V view, C component, I interactor) {
        super(component, interactor);
        this.view = view;
    }

    public V getView() {
        return view;
    }
}
