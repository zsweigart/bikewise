package com.zsweigart.bikewise.incidents.list;

import com.zsweigart.core_components.Presenter;

/**
 * {@link Presenter} for the {@link HomeScope} which handles showing and hiding the progress view
 */
public class HomePresenter extends Presenter<HomeRootView> {

    public HomePresenter(HomeRootView view) {
        super(view);
    }

    /**
     * Show the progress view
     */
    void showProgress() {
        getView().showProgressView();
    }

    /**
     * Hide the progress view
     */
    void hideProgress() {
        getView().hideProgressView();
    }
}
