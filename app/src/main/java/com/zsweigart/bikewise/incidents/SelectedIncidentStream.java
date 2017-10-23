package com.zsweigart.bikewise.incidents;

import com.google.common.base.Optional;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.zsweigart.models.Incident;

import io.reactivex.Observable;

/**
 * Created by zsweigart on 10/22/17.
 */
public class SelectedIncidentStream {

    private BehaviorRelay<Optional<Incident>> incidentRelay = BehaviorRelay.create();

    public Observable<Optional<Incident>> selectedIncident() {
        return incidentRelay.hide();
    }

    public void setSelectedIncident(Optional<Incident> incident) {
        incidentRelay.accept(incident);
    }
}
