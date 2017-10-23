package com.zsweigart.bikewise.incidents.list.searchbar;

import android.support.annotation.VisibleForTesting;
import android.view.View;

import com.zsweigart.core_components.Presenter;

/**
 * {@link Presenter} for the {@link SearchBarBuilder.SearchBarScope} which handles searching for ids from the
 * api and closing itself
 */
public class SearchBarPresenter extends Presenter<SearchBarView> {

    private final Listener listener;

    @VisibleForTesting
    View.OnClickListener searchClickListener;

    @VisibleForTesting
    View.OnClickListener closeClickListener;

    public SearchBarPresenter(SearchBarView view, Listener listener) {
        super(view);
        this.listener = listener;
    }

    @Override
    protected void didLoad() {
        super.didLoad();

        closeClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCloseClick();
            }
        };
        view.setCloseOnClickListener(closeClickListener);

        searchClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSearchClick(view.getIdText());
            }
        };
        view.setSearchClickListener(searchClickListener);
    }

    /**
     * Remove text from the edit text in the search bar
     */
    void clearText() {
        view.clearText();
    }

    /**
     * Handle callbacks from the presenter
     */
    public interface Listener {

        /**
         * Called when the user presses the search button.  Reads the id entered into the field and calls the api
         *
         * @param search the id entered into the field
         */
        void onSearchClick(String search);

        /**
         * Called when the user clicks the close button to hide the search bar.
         */
        void onCloseClick();
    }
}
