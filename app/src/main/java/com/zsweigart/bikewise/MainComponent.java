package com.zsweigart.bikewise;

import android.content.Context;

import com.zsweigart.bikewise.incidents.IncidentModule;
import com.zsweigart.bikewise.incidents.SelectedIncidentStream;
import com.zsweigart.bikewise.incidents.details.fragment.DetailsFragmentParentComponent;
import com.zsweigart.bikewise.incidents.list.fragment.HomeFragmentParentComponent;
import com.zsweigart.bikewise.lifecycle.ConfigurationStream;
import com.zsweigart.bikewise.networking.NetworkModule;
import com.zsweigart.bikewise.storage.StorageModule;
import com.zsweigart.networking.BikeWiseClient;
import com.zsweigart.storage.IncidentStorage;

import dagger.Component;

/**
 * Created by zsweigart on 9/24/17.
 */
@Component(modules = {
        MainModule.class,
        NetworkModule.class,
        StorageModule.class,
        IncidentModule.class,
})
@PerApp
public interface MainComponent extends HomeFragmentParentComponent, DetailsFragmentParentComponent {

    void inject(MainApplication demoApplication);

    @PerApp
    BikeWiseClient bikeWiseClient();

    @PerApp
    ConfigurationStream configurationStream();

    @PerApp
    Context getContext();

    @PerApp
    IncidentStorage incidentStorage();

    @PerApp
    SelectedIncidentStream selectedIncidentStream();

    @PerApp
    MainApplication getApplication();
}
