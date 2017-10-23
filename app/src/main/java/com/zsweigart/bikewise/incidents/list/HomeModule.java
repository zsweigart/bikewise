package com.zsweigart.bikewise.incidents.list;

import dagger.Module;
import dagger.Provides;

/**
 * Provides depedencies for the Home Rib
 */
@Module
public class HomeModule {

    private final HomeRootView homeRootView;
    private final HomeActivity homeActivity;
    private final HomeInteractor homeInteractor;

    public HomeModule(HomeRootView homeRootView, HomeActivity homeActivity, HomeInteractor homeInteractor) {
        this.homeRootView = homeRootView;
        this.homeActivity = homeActivity;
        this.homeInteractor = homeInteractor;
    }

    @Provides
    @HomeScope
    HomePresenter homePresenter() {
        return new HomePresenter(homeRootView);
    }

    @Provides
    @HomeScope
    HomeActivity homeActivity() {
        return homeActivity;
    }

    @Provides
    @HomeScope
    HomeInteractor.ProgressListener progressListener() {
        return homeInteractor.new ProgressListener();
    }
}
