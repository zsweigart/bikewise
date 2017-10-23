package com.zsweigart.bikewise.incidents.list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.zsweigart.bikewise.incidents.list.actionbar.ActionBarBuilder;
import com.zsweigart.bikewise.incidents.list.actionbar.ActionBarRouter;
import com.zsweigart.bikewise.incidents.list.list.IncidentListBuilder;
import com.zsweigart.bikewise.incidents.list.list.IncidentListRouter;
import com.zsweigart.bikewise.incidents.list.searchbar.SearchBarBuilder;
import com.zsweigart.bikewise.incidents.list.searchbar.SearchBarRouter;
import com.zsweigart.core_components.ViewRouter;

/**
 * {@link ViewRouter} for the {@link HomeScope} which handles routing to and
 * from the ActionBar Rib, IncidentList Rib and SearchBar Rib
 */
public class HomeRouter extends ViewRouter<HomeRootView, HomeComponent, HomeInteractor> {

    @NonNull private final ActionBarBuilder actionBarBuilder;
    @NonNull private final IncidentListBuilder incidentListBuilder;
    @NonNull private final SearchBarBuilder searchBarBuilder;

    @Nullable private ActionBarRouter actionBarRouter;
    @Nullable private IncidentListRouter incidentListRouter;
    @Nullable private SearchBarRouter searchBarRouter;

    /**
     * @param homeRootView root view to which child views will be added
     * @param component the interactor component to provide dependencies
     * @param interactor the interactor associated with this rib
     * @param actionBarBuilder a builder for action bar ribs
     * @param incidentListBuilder a builder for incident list ribs
     * @param searchBarBuilder a builder for search bar ribs
     */
    public HomeRouter(
            HomeRootView homeRootView,
            HomeComponent component,
            HomeInteractor interactor,
            ActionBarBuilder actionBarBuilder,
            IncidentListBuilder incidentListBuilder,
            SearchBarBuilder searchBarBuilder) {
        super(homeRootView, component, interactor);
        this. actionBarBuilder = actionBarBuilder;
        this.incidentListBuilder = incidentListBuilder;
        this.searchBarBuilder = searchBarBuilder;
    }

    @Override
    protected void willAttach() {
        super.willAttach();
        routeToActionBar();
        routeToIncidentList();
    }

    @Override
    protected void willDetach() {
        super.willDetach();
        removeSearchBar();
        removeActionBar();
        removeIncidentList();
    }

    /**
     * Build and attach the action bar rib if one is not attached already
     */
    void routeToActionBar() {
        if (actionBarRouter == null) {
            actionBarRouter = actionBarBuilder.buildRouter(getView());
            attachChild(actionBarRouter);
        }
    }

    /**
     * Build and attach the incident list rib if one is not attached and add it's view
     */
    void routeToIncidentList() {
        if (incidentListRouter == null) {
            incidentListRouter = incidentListBuilder.buildRouter(getView());
            attachChild(incidentListRouter);
            if (incidentListRouter.getView() != null) {
                getView().addIncidentListView(incidentListRouter.getView());
            }
        }
    }
    /**
     * Build and attach the search bar rib if one is not attached and add it's view
     */
    void routeToSearchBar() {
        if (searchBarRouter == null) {
            searchBarRouter = searchBarBuilder.buildRouter(getView());
            attachChild(searchBarRouter);
            if (searchBarRouter.getView() != null) {
                getView().addSearchView(searchBarRouter.getView());
            }
        }
    }

    /**
     * Remove the search rib if one is attached and add remove it's view
     */
    void removeSearchBar() {
        if (searchBarRouter != null) {
            detachChild(searchBarRouter);
            getView().removeSearchView(searchBarRouter.getView());
            searchBarRouter = null;
        }
    }

    /**
     * Remove the action bar rib if one is attached
     */
    void removeActionBar() {
        if (actionBarRouter != null) {
            detachChild(actionBarRouter);
            actionBarRouter = null;
        }
    }

    /**
     * Remove the incident list rib if one is attached and add remove it's view
     */
    void removeIncidentList() {
        if (incidentListRouter != null) {
            detachChild(incidentListRouter);
            getView().removeIncidentListView(incidentListRouter.getView());
            incidentListRouter = null;
        }
    }
}
