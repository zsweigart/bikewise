package com.zsweigart.bikewise.incidents.details;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zsweigart on 10/19/17.
 */
@Module
public class DetailsInteractorModule {

    private final DetailsActivity detailsActivity;
    private final DetailsRootView detailsRootView;

    public DetailsInteractorModule(DetailsActivity detailsActivity, DetailsRootView detailsRootView) {
        this.detailsActivity = detailsActivity;
        this.detailsRootView = detailsRootView;
    }

    @Provides
    @DetailsInteractorScope
    DetailsActivity detailsActivity() {
        return detailsActivity;
    }

    @Provides
    @DetailsInteractorScope
    DetailsPresenter detailsPresenter() {
        return new DetailsPresenter(detailsRootView);
    }
}
