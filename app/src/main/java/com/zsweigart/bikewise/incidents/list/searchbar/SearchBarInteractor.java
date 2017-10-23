package com.zsweigart.bikewise.incidents.list.searchbar;

import android.content.Intent;
import android.widget.Toast;

import com.google.common.base.Optional;
import com.uber.autodispose.AutoDispose;
import com.zsweigart.bikewise.R;
import com.zsweigart.bikewise.incidents.SelectedIncidentStream;
import com.zsweigart.bikewise.incidents.details.DetailsActivity;
import com.zsweigart.bikewise.incidents.list.HomeActivity;
import com.zsweigart.bikewise.incidents.list.HomeInteractor;
import com.zsweigart.core_components.ViewInteractor;
import com.zsweigart.models.Incident;
import com.zsweigart.networking.BikeWiseClient;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * {@link ViewInteractor} for the {@link SearchBarBuilder.SearchBarScope} which handles searching for ids from the
 * api and closing itself
 */
public class SearchBarInteractor extends ViewInteractor<SearchBarPresenter, SearchBarRouter> implements
        SearchBarPresenter.Listener {

    @Inject BikeWiseClient bikeWiseClient;
    @Inject HomeActivity homeActivity;
    @Inject SearchBarStream searchBarStream;
    @Inject HomeInteractor.ProgressListener progressListener;
    @Inject SelectedIncidentStream selectedIncidentStream;

    @Override
    protected void didBecomeActive() {
        super.didBecomeActive();

        bikeWiseClient.incident()
                .observeOn(AndroidSchedulers.mainThread())
                .to(AutoDispose.with(this).<Optional<Incident>>forObservable())
                .subscribe(new Consumer<Optional<Incident>>() {
                    @Override
                    public void accept(Optional<Incident> incidentOptional) throws Exception {
                        progressListener.hideProgress();
                        getPresenter().clearText();
                        if (incidentOptional.isPresent()) {
                            selectedIncidentStream.setSelectedIncident(incidentOptional);
                            homeActivity.startActivity(new Intent(homeActivity, DetailsActivity.class));
                        } else {
                            Toast.makeText(homeActivity, R.string.search_no_results, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onCloseClick() {
        searchBarStream.hideSearch();
    }

    @Override
    public void onSearchClick(String search) {
        try {
            Integer id = Integer.parseInt(search);
            if (id < 0) {
                showIdNumberError();
            } else {
                progressListener.showProgress();
                bikeWiseClient.requestIncident(id);
            }
        } catch (NumberFormatException ex) {
            showIdNumberError();
        }
    }

    private void showIdNumberError() {
        Toast.makeText(homeActivity, R.string.invalid_id_number, Toast.LENGTH_SHORT).show();
    }
}
