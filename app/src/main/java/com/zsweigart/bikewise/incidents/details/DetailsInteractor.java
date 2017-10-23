package com.zsweigart.bikewise.incidents.details;

import android.text.TextUtils;
import android.widget.Toast;

import com.google.common.base.Optional;
import com.uber.autodispose.AutoDispose;
import com.zsweigart.bikewise.R;
import com.zsweigart.bikewise.incidents.SelectedIncidentStream;
import com.zsweigart.core_components.ViewInteractor;
import com.zsweigart.models.Incident;
import com.zsweigart.models.Media;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by zsweigart on 10/19/17.
 */
public class DetailsInteractor extends ViewInteractor<DetailsPresenter, DetailsRouter> {

    @Inject DetailsActivity detailsActivity;
    @Inject SelectedIncidentStream selectedIncidentStream;

    @Override
    protected void didBecomeActive() {
        super.didBecomeActive();

        selectedIncidentStream
                .selectedIncident()
                .take(1)
                .observeOn(AndroidSchedulers.mainThread())
                .to(AutoDispose.with(this).<Optional<Incident>>forObservable())
                .subscribe(new Consumer<Optional<Incident>>() {
                    @Override
                    public void accept(Optional<Incident> incidentOptional) throws Exception {
                        if (incidentOptional.isPresent()) {
                            Incident incident = incidentOptional.get();
                            getPresenter().updateFields(incident);

                            Media media = incident.media();
                            if (media != null) {
                                if (!TextUtils.isEmpty(media.image_url())) {
                                    getRouter().routeToMedia(media);
                                }
                            }
                        } else {
                            Toast.makeText(detailsActivity, R.string.details_load_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
