package com.zsweigart.bikewise.incidents.list.searchbar;

import android.view.View;

import com.zsweigart.bikewise.BikeWiseTestBase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by zsweigart on 10/23/17.
 */
public class SearchBarPresenterTest extends BikeWiseTestBase {

    private static final String ID = "1";

    @Mock private SearchBarView searchBarView;
    @Mock private SearchBarPresenter.Listener listener;
    @Mock private View view;

    private SearchBarPresenter searchBarPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(searchBarView.getIdText()).thenReturn(ID);
        searchBarPresenter = new SearchBarPresenter(searchBarView, listener);
        searchBarPresenter.didLoad();
    }

    @Test
    public void searchClick_callsListener() {
        searchBarPresenter.searchClickListener.onClick(view);

        verify(listener).onSearchClick(ID);
    }

    @Test
    public void closeClick_callsListener() {
        searchBarPresenter.closeClickListener.onClick(view);

        verify(listener).onCloseClick();
    }

    @Test
    public void clearText_callsView() {
        searchBarPresenter.clearText();

        verify(searchBarView).clearText();
    }
}
