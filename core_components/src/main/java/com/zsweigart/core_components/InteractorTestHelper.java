package com.zsweigart.core_components;

import android.support.annotation.Nullable;

/**
 * Created by zsweigart on 10/23/17.
 */
public class InteractorTestHelper {

    private InteractorTestHelper() { }

    public static <R extends Router> void attach(
            Interactor<R> interactor, R router) {
        interactor.setRouter(router);
        interactor.dispatchAttach();
    }

    public static <P extends Presenter, R extends ViewRouter> void attach(
            ViewInteractor<P, R> interactor, P presenter, R router) {
        interactor.presenter = presenter;
        interactor.setRouter(router);
        interactor.dispatchAttach();
    }
}
