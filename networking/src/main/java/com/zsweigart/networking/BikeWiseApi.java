package com.zsweigart.networking;

import com.zsweigart.models.Incident;
import com.zsweigart.models.Incidents;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zsweigart on 10/18/17.
 */
interface BikeWiseApi {

    @GET("v2/incidents")
    Observable<Incidents> getIncidentList();

    @GET("v2/incidents/{id}")
    Observable<Incident> getIncident(@Path("id") Integer incidentId);
}
