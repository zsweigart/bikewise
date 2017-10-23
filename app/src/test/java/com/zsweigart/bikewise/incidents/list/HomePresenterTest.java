package com.zsweigart.bikewise.incidents.list;

import com.zsweigart.bikewise.BikeWiseTestBase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Created by zsweigart on 10/23/17.
 */
public class HomePresenterTest extends BikeWiseTestBase {

    @Mock private HomeRootView homeRootView;

    private HomePresenter homePresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        homePresenter = new HomePresenter(homeRootView);
    }

    @Test
    public void showProgress_callsRootViewShowProgressView() {
        homePresenter.showProgress();

        verify(homeRootView).showProgressView();
    }

    @Test
    public void hideProgress_callsRootViewHideProgressView() {
        homePresenter.hideProgress();

        verify(homeRootView).hideProgressView();
    }
}
