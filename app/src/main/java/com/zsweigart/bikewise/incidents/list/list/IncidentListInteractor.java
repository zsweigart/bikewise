package com.zsweigart.bikewise.incidents.list.list;

import android.content.Intent;

import com.google.common.base.Optional;
import com.uber.autodispose.AutoDispose;
import com.zsweigart.bikewise.incidents.SelectedIncidentStream;
import com.zsweigart.bikewise.incidents.details.DetailsActivity;
import com.zsweigart.bikewise.incidents.list.HomeActivity;
import com.zsweigart.bikewise.incidents.list.HomeInteractor;
import com.zsweigart.core_components.ViewInteractor;
import com.zsweigart.models.Incident;
import com.zsweigart.networking.BikeWiseClient;
import com.zsweigart.storage.IncidentStorage;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by zsweigart on 10/19/17.
 */
public class IncidentListInteractor extends ViewInteractor<IncidentListPresenter, IncidentListRouter> implements
        IncidentListPresenter.Listener {

    @Inject BikeWiseClient bikeWiseClient;
    @Inject HomeActivity homeActivity;
    @Inject IncidentStorage incidentStorage;
    @Inject HomeInteractor.ProgressListener progressListener;
    @Inject SelectedIncidentStream selectedIncidentStream;

    @Override
    protected void didBecomeActive() {
        super.didBecomeActive();

        progressListener.showProgress();
        incidentStorage.getIncidentList()
                .take(1)
                .observeOn(AndroidSchedulers.mainThread())
                .to(AutoDispose.with(this).<Optional<List<Incident>>>forObservable())
                .subscribe(new Consumer<Optional<List<Incident>>>() {
                    @Override
                    public void accept(Optional<List<Incident>> listOptional) throws Exception {
                        if (listOptional.isPresent()) {
                            updateIncidentList(listOptional.get());
                            progressListener.hideProgress();
                        } else {
                            bikeWiseClient.requestIncidentList();
                        }
                    }
                });

        bikeWiseClient.incidentList()
                .observeOn(AndroidSchedulers.mainThread())
                .to(AutoDispose.with(this).<List<Incident>>forObservable())
                .subscribe(new Consumer<List<Incident>>() {
                    @Override
                    public void accept(List<Incident> incidents) throws Exception {
                        updateIncidentList(incidents);
                        progressListener.hideProgress();
                        incidentStorage.putIncidentList(incidents);
                    }
                });
    }

    @Override
    public void onItemClick(Incident incident) {
        selectedIncidentStream.setSelectedIncident(Optional.fromNullable(incident));
        homeActivity.startActivity(new Intent(homeActivity, DetailsActivity.class));
    }

    private void updateIncidentList(List<Incident> incidentList) {
        getPresenter().updateIncidentList(incidentList);
    }
}
