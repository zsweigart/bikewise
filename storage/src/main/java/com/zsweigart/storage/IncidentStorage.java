package com.zsweigart.storage;

import com.google.common.base.Optional;
import com.zsweigart.models.Incident;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by zsweigart on 10/21/17.
 */
public interface IncidentStorage {

    void putIncidentList(List<Incident> incidentList);

    Observable<Optional<List<Incident>>> getIncidentList();
}
