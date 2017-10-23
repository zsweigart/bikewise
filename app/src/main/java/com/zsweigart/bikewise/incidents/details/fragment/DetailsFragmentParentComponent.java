package com.zsweigart.bikewise.incidents.details.fragment;

import com.zsweigart.bikewise.incidents.SelectedIncidentStream;
import com.zsweigart.networking.BikeWiseClient;

/**
 * Created by zsweigart on 10/18/17.
 */
public interface DetailsFragmentParentComponent {

    BikeWiseClient bikeWiseClient();

    SelectedIncidentStream selectedIncidentStream();
}
