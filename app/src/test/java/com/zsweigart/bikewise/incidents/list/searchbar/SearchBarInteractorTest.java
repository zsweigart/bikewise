package com.zsweigart.bikewise.incidents.list.searchbar;

import android.content.Intent;
import android.widget.Toast;

import com.google.common.base.Optional;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.zsweigart.bikewise.BikeWiseAppRobolectricTestBase;
import com.zsweigart.bikewise.incidents.SelectedIncidentStream;
import com.zsweigart.bikewise.incidents.details.DetailsActivity;
import com.zsweigart.bikewise.incidents.list.HomeActivity;
import com.zsweigart.bikewise.incidents.list.HomeInteractor;
import com.zsweigart.core_components.InteractorTestHelper;
import com.zsweigart.models.Incident;
import com.zsweigart.networking.BikeWiseClient;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowToast;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by zsweigart on 10/23/17.
 */
public class SearchBarInteractorTest extends BikeWiseAppRobolectricTestBase {

    @Mock private SearchBarRouter searchBarRouter;
    @Mock private SearchBarPresenter searchBarPresenter;
    @Mock private BikeWiseClient bikeWiseClient;
    @Mock private HomeActivity homeActivity;
    @Mock private SearchBarStream searchBarStream;
    @Mock private HomeInteractor.ProgressListener progressListener;
    @Mock private SelectedIncidentStream selectedIncidentStream;
    @Mock private Incident incident;

    private BehaviorRelay<Optional<Incident>> incidentRelay = BehaviorRelay.create();

    private SearchBarInteractor searchBarInteractor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(bikeWiseClient.incident()).thenReturn(incidentRelay);
        when(homeActivity.getResources()).thenReturn(RuntimeEnvironment.application.getResources());

        searchBarInteractor = new SearchBarInteractor();
        searchBarInteractor.bikeWiseClient = bikeWiseClient;
        searchBarInteractor.homeActivity = homeActivity;
        searchBarInteractor.searchBarStream = searchBarStream;
        searchBarInteractor.progressListener = progressListener;
        searchBarInteractor.selectedIncidentStream = selectedIncidentStream;

        InteractorTestHelper.attach(searchBarInteractor, searchBarPresenter, searchBarRouter);
    }

    @Test
    public void bikeWiseClientEmits_whenNoIncident_displaysToast() {
        incidentRelay.accept(Optional.<Incident>absent());
        Toast toast = ShadowToast.getLatestToast();

        assertThat(toast).isNotNull();
    }

    @Test
    public void bikeWiseClientEmits_whenIncident_startsDetails() {
        incidentRelay.accept(Optional.of(incident));

        verify(selectedIncidentStream).setSelectedIncident(Optional.of(incident));
        ArgumentCaptor<Intent> intentArgumentCaptor = ArgumentCaptor.forClass(Intent.class);
        verify(homeActivity).startActivity(intentArgumentCaptor.capture());
        assertThat(intentArgumentCaptor.getValue().getComponent().getClassName())
                .isEqualTo(DetailsActivity.class.getName());
    }

    @Test
    public void onCloseClick_hidesSearch() {
        searchBarInteractor.onCloseClick();

        verify(searchBarStream).hideSearch();
    }

    @Test
    public void onSearchClick_whenInvalidNumber_showsToast() {
        searchBarInteractor.onSearchClick("-1");
        Toast toast = ShadowToast.getLatestToast();

        assertThat(toast).isNotNull();
    }

    @Test
    public void onSearchClick_whenValidNumber_callsBikeWiseClient() {
        searchBarInteractor.onSearchClick("1");

        verify(bikeWiseClient).requestIncident(1);
        verify(progressListener).showProgress();
    }
}
