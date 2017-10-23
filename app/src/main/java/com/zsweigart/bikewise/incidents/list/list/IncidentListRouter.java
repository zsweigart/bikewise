package com.zsweigart.bikewise.incidents.list.list;

import com.zsweigart.core_components.ViewRouter;

/**
 * Created by zsweigart on 10/19/17.
 */
public class IncidentListRouter extends
        ViewRouter<IncidentListView, IncidentListBuilder.IncidentListComponent, IncidentListInteractor> {

    public IncidentListRouter(
            IncidentListView incidentListView,
            IncidentListBuilder.IncidentListComponent component,
            IncidentListInteractor interactor) {
        super(incidentListView, component, interactor);
    }
}
