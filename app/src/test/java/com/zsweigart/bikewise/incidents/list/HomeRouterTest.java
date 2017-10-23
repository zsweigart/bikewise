package com.zsweigart.bikewise.incidents.list;

import com.zsweigart.bikewise.incidents.list.actionbar.ActionBarBuilder;
import com.zsweigart.bikewise.incidents.list.actionbar.ActionBarRouter;
import com.zsweigart.bikewise.incidents.list.list.IncidentListBuilder;
import com.zsweigart.bikewise.incidents.list.list.IncidentListRouter;
import com.zsweigart.bikewise.incidents.list.list.IncidentListView;
import com.zsweigart.bikewise.incidents.list.searchbar.SearchBarBuilder;
import com.zsweigart.bikewise.incidents.list.searchbar.SearchBarRouter;
import com.zsweigart.bikewise.incidents.list.searchbar.SearchBarView;
import com.zsweigart.core_components.RouterHelper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by zsweigart on 10/23/17.
 */
public class HomeRouterTest {

    @Mock private HomeRootView homeRootView;
    @Mock private HomeComponent component;
    @Mock private HomeInteractor interactor;
    @Mock private ActionBarBuilder actionBarBuilder;
    @Mock private IncidentListBuilder incidentListBuilder;
    @Mock private SearchBarBuilder searchBarBuilder;
    @Mock private ActionBarRouter actionBarRouter;
    @Mock private IncidentListRouter incidentListRouter;
    @Mock private IncidentListView incidentListView;
    @Mock private SearchBarRouter searchBarRouter;
    @Mock private SearchBarView searchBarView;

    private HomeRouter homeRouter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(actionBarBuilder.buildRouter(homeRootView)).thenReturn(actionBarRouter);
        when(incidentListRouter.getView()).thenReturn(incidentListView);
        when(incidentListBuilder.buildRouter(homeRootView)).thenReturn(incidentListRouter);
        when(searchBarRouter.getView()).thenReturn(searchBarView);
        when(searchBarBuilder.buildRouter(homeRootView)).thenReturn(searchBarRouter);

        homeRouter = new HomeRouter(homeRootView, component, interactor, actionBarBuilder, incidentListBuilder,
                searchBarBuilder);
    }

    @Test
    public void routeToActionBar_attachesActionBar() {
        homeRouter.routeToActionBar();

        RouterHelper.verifyAttached(actionBarRouter);
    }

    @Test
    public void routeToIncidentList_attachesIncidentList() {
        homeRouter.routeToIncidentList();

        RouterHelper.verifyAttached(incidentListRouter);
        verify(homeRootView).addIncidentListView(incidentListView);
    }

    @Test
    public void routeToSearchBar_attachesSearchBar() {
        homeRouter.routeToSearchBar();

        RouterHelper.verifyAttached(searchBarRouter);
        verify(homeRootView).addSearchView(searchBarView);
    }

    @Test
    public void removeActionBar_whenActionBarAdded_removesActionBar() {
        homeRouter.routeToActionBar();
        Mockito.reset();
        homeRouter.removeActionBar();

        RouterHelper.verifyDetached(actionBarRouter);
    }

    @Test
    public void removeIncidentList_whenIncidentListAdded_removesIncidentList() {
        homeRouter.routeToIncidentList();
        Mockito.reset();
        homeRouter.removeIncidentList();

        RouterHelper.verifyDetached(incidentListRouter);
        verify(homeRootView).removeIncidentListView(incidentListView);
    }

    @Test
    public void removeSearchBar_whenSearchBarAdded_removesSearchBar() {
        homeRouter.routeToSearchBar();
        Mockito.reset();
        homeRouter.removeSearchBar();

        RouterHelper.verifyDetached(searchBarRouter);
        verify(homeRootView).removeSearchView(searchBarView);
    }
}
