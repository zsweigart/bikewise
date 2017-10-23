package com.zsweigart.bikewise.incidents.list.fragment;

import com.zsweigart.bikewise.incidents.SelectedIncidentStream;
import com.zsweigart.bikewise.lifecycle.ConfigurationStream;
import com.zsweigart.networking.BikeWiseClient;
import com.zsweigart.storage.IncidentStorage;

/**
 * Created by zsweigart on 10/18/17.
 */
public interface HomeFragmentParentComponent {

    BikeWiseClient bikeWiseClient();

    ConfigurationStream configurationStream();

    IncidentStorage incidentStorage();

    SelectedIncidentStream selectedIncidentStream();
}
