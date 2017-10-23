package com.zsweigart.bikewise.incidents.list.searchbar;

import com.jakewharton.rxrelay2.BehaviorRelay;

import io.reactivex.Observable;

/**
 * Stream which holds the value of whether the search bar should be shown or not
 */
public class SearchBarStream {

    private BehaviorRelay<Boolean> searchRelay = BehaviorRelay.create();

    /**
     * @return an {@link Observable} which has true for showing the search bar or false for hiding it
     */
    public Observable<Boolean> search() {
        return searchRelay.hide();
    }

    /**
     * Set the value of the stream to true to show the search bar
     */
    public void showSearch() {
        searchRelay.accept(true);
    }

    /**
     * Set the value of the stream to false to hide the search bar
     */
    public void hideSearch() {
        searchRelay.accept(false);
    }
}
