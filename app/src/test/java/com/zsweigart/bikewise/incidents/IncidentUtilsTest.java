package com.zsweigart.bikewise.incidents;

import com.zsweigart.bikewise.BikeWiseAppRobolectricTestBase;
import com.zsweigart.models.Incident;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by zsweigart on 10/23/17.
 */
public class IncidentUtilsTest extends BikeWiseAppRobolectricTestBase {

    private static final String DEFAULT_TITLE = "<<No Title>>";
    private static final String DEFAULT_DESCRIPTION = "<<No Description>>";

    @Mock private Incident incident;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getTitle_whenNoTitle_returnsDefaultString() {
        setupIncident("", "");

        assertThat(IncidentUtils.getTitle(incident, RuntimeEnvironment.application)).isEqualTo(DEFAULT_TITLE);
    }

    @Test
    public void getTitle_whenHasTitle_returnsTitle() {
        String title = "TITLE";
        setupIncident(title, "");

        assertThat(IncidentUtils.getTitle(incident, RuntimeEnvironment.application)).isEqualTo(title);
    }

    @Test
    public void getTitle_whenNoDescription_returnsDefaultString() {
        setupIncident("", "");

        assertThat(IncidentUtils.getDescription(incident, RuntimeEnvironment.application))
                .isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    public void getTitle_whenHasDescription_returnsDescription() {
        String description = "DESCRIPTION";
        setupIncident("", description);

        assertThat(IncidentUtils.getDescription(incident, RuntimeEnvironment.application)).isEqualTo(description);
    }

    private void setupIncident(String title, String description) {
        when(incident.title()).thenReturn(title);
        when(incident.description()).thenReturn(description);
    }
}
