package com.zsweigart.networking;

import android.support.annotation.Nullable;

import com.google.common.base.Optional;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.zsweigart.models.Incident;
import com.zsweigart.models.Incidents;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import retrofit2.Retrofit;

/**
 * Created by zsweigart on 10/18/17.
 */
public class BikeWiseClient {

    public static final String BASE_URL = "https://bikewise.org:443/api/";

    private final Retrofit retrofit;
    private final BikeWiseApi bikeWiseApi;
    private final BehaviorRelay<List<Incident>> listBehaviorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Optional<Incident>> incidentBehaviorRelay = BehaviorRelay.create();

    public BikeWiseClient(Retrofit retrofit) {
        this.retrofit = retrofit;
        bikeWiseApi = retrofit.create(BikeWiseApi.class);
    }

    public void requestIncidentList() {
        bikeWiseApi.getIncidentList()
                .subscribe(new Consumer<Incidents>() {
                    @Override
                    public void accept(Incidents incidents) throws Exception {
                        listBehaviorRelay.accept(incidents.incidents());
                    }
                });
    }

    public void requestIncident(Integer incidentId) {
        bikeWiseApi.getIncident(incidentId)
                .subscribeWith(new DisposableObserver<Incident>() {
                    @Override
                    public void onNext(@NonNull Incident incident) {
                        putIncident(incident);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        putIncident(null);
                    }

                    @Override
                    public void onComplete() { }
                });

    }

    public Observable<List<Incident>> incidentList() {
        return listBehaviorRelay
                .hide()
                .replay(1)
                .refCount();
    }

    public Observable<Optional<Incident>> incident() {
        return incidentBehaviorRelay
                .hide()
                .replay(1)
                .refCount();
    }

    public void putIncident(@Nullable Incident incident) {
        incidentBehaviorRelay.accept(Optional.fromNullable(incident));
    }
}
