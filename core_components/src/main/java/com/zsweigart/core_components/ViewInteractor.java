package com.zsweigart.core_components;

import android.support.annotation.VisibleForTesting;

import javax.inject.Inject;

/**
 * Created by zsweigart on 10/20/17.
 */
public class ViewInteractor<P extends Presenter, R extends ViewRouter> extends Interactor<R> {

    @Inject P presenter;

    @Override
    protected void dispatchAttach() {
        super.dispatchAttach();
        getPresenter().dispatchLoad();
    }

    @Override
    protected void dispatchDetach() {
        super.dispatchDetach();
        getPresenter().dispatchUnload();
    }

    public P getPresenter() {
        return presenter;
    }
}
