package com.zsweigart.bikewise.incidents.list;

import com.uber.autodispose.AutoDispose;
import com.zsweigart.bikewise.incidents.list.searchbar.SearchBarStream;
import com.zsweigart.core_components.ViewInteractor;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * {@link ViewInteractor} for the {@link HomeScope} which listens for showing and hiding the search bar and
 * showing and hiding progress
 */
public class HomeInteractor extends ViewInteractor<HomePresenter, HomeRouter> {

    @Inject SearchBarStream searchBarShownStream;

    @Override
    protected void didBecomeActive() {
        super.didBecomeActive();

        searchBarShownStream.search()
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .to(AutoDispose.with(this).<Boolean>forObservable())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean show) throws Exception {
                        if (show) {
                            getRouter().routeToSearchBar();
                        } else {
                            getRouter().removeSearchBar();
                        }
                    }
                });
    }

    /**
     * Listens for calls to show and hide the progress view
     */
    public class ProgressListener {

        /**
         * Show the progress view
         */
        public void showProgress() {
            getPresenter().showProgress();
        }

        /**
         * Hide the progress view
         */
        public void hideProgress() {
            getPresenter().hideProgress();
        }
    }
}
