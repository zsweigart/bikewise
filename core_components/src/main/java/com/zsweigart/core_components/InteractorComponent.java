package com.zsweigart.core_components;

/**
 * Created by zsweigart on 10/19/17.
 */
public interface InteractorComponent<I extends Interactor<?>> {

    void inject(I interactor);
}
