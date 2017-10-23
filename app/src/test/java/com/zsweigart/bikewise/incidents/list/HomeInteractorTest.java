package com.zsweigart.bikewise.incidents.list;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.zsweigart.bikewise.BikeWiseTestBase;
import com.zsweigart.bikewise.incidents.list.searchbar.SearchBarStream;
import com.zsweigart.core_components.InteractorTestHelper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by zsweigart on 10/23/17.
 */
public class HomeInteractorTest extends BikeWiseTestBase {

    @Mock private SearchBarStream searchBarStream;
    @Mock private HomeRouter homeRouter;
    @Mock private HomePresenter homePresenter;

    private BehaviorRelay<Boolean> searchBarShowRelay = BehaviorRelay.create();

    private HomeInteractor homeInteractor;
    private HomeInteractor.ProgressListener progressListener;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(searchBarStream.search()).thenReturn(searchBarShowRelay);

        homeInteractor = new HomeInteractor();
        homeInteractor.searchBarShownStream = searchBarStream;
        InteractorTestHelper.attach(homeInteractor, homePresenter, homeRouter);

        progressListener = homeInteractor.new ProgressListener();
    }

    @Test
    public void searchBarRelayEmitsTrue_whenInteractorAttached_callsRouteToSearchBar() {
        searchBarShowRelay.accept(true);

        verify(homeRouter).routeToSearchBar();
    }

    @Test
    public void searchBarRelayEmitsFalse_whenInteractorAttached_callsRemoveSearchBar() {
        searchBarShowRelay.accept(false);

        verify(homeRouter).removeSearchBar();
    }

    @Test
    public void progressListener_showProgress_callsPresenterShowProgress() {
        progressListener.showProgress();

        verify(homePresenter).showProgress();
    }

    @Test
    public void progressListener_hideProgress_callsPresenterHideProgress() {
        progressListener.hideProgress();

        verify(homePresenter).hideProgress();
    }
}
