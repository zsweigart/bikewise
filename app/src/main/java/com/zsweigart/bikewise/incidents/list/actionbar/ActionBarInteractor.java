package com.zsweigart.bikewise.incidents.list.actionbar;

import android.view.MenuItem;

import com.uber.autodispose.AutoDispose;
import com.zsweigart.bikewise.R;
import com.zsweigart.bikewise.incidents.list.HomeActivity;
import com.zsweigart.bikewise.incidents.list.HomeInteractor;
import com.zsweigart.bikewise.incidents.list.searchbar.SearchBarStream;
import com.zsweigart.bikewise.lifecycle.MenuStream;
import com.zsweigart.core_components.Interactor;
import com.zsweigart.networking.BikeWiseClient;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by zsweigart on 10/20/17.
 */
public class ActionBarInteractor extends Interactor<ActionBarRouter> {
    @Inject HomeActivity homeActivity;
    @Inject MenuStream menuStream;
    @Inject SearchBarStream searchBarStream;
    @Inject BikeWiseClient bikeWiseClient;
    @Inject HomeInteractor.ProgressListener progressListener;

    @Override
    protected void didBecomeActive() {
        super.didBecomeActive();

        menuStream.menuItem()
                .observeOn(AndroidSchedulers.mainThread())
                .to(AutoDispose.with(this).<MenuItem>forObservable())
                .subscribe(new Consumer<MenuItem>() {
                    @Override
                    public void accept(MenuItem menuItem) throws Exception {
                        switch (menuItem.getItemId()) {
                            case R.id.action_search:
                                searchBarStream.showSearch();
                                break;
                            case R.id.action_refresh:
                                progressListener.showProgress();
                                bikeWiseClient.requestIncidentList();
                                break;
                        }
                    }
                });
    }
}
