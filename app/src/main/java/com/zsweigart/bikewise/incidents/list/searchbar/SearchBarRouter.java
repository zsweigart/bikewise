package com.zsweigart.bikewise.incidents.list.searchbar;

import com.zsweigart.core_components.ViewRouter;

/**
 * {@link ViewRouter} for the {@link SearchBarBuilder.SearchBarScope} only handles initializing this rib as there are
 * no children
 */
public class SearchBarRouter extends
        ViewRouter<SearchBarView, SearchBarBuilder.SearchBarComponent, SearchBarInteractor> {

    public SearchBarRouter(
            SearchBarView searchBarView,
            SearchBarBuilder.SearchBarComponent component,
            SearchBarInteractor interactor) {
        super(searchBarView, component, interactor);
    }
}
