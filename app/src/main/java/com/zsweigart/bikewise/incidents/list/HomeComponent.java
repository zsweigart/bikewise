package com.zsweigart.bikewise.incidents.list;

import com.zsweigart.bikewise.incidents.SelectedIncidentStream;
import com.zsweigart.bikewise.incidents.list.fragment.HomeFragmentComponent;
import com.zsweigart.bikewise.incidents.list.searchbar.SearchBarStream;
import com.zsweigart.bikewise.lifecycle.MenuStream;
import com.zsweigart.core_components.InteractorComponent;
import com.zsweigart.networking.BikeWiseClient;
import com.zsweigart.storage.IncidentStorage;

import dagger.Component;

/**
 * Lists dependencies provided by this Rib
 */
@Component(modules = HomeModule.class, dependencies = HomeFragmentComponent.class)
@HomeScope
public interface HomeComponent extends InteractorComponent<HomeInteractor> {

    BikeWiseClient bikeWiseClient();

    HomeActivity homeActivity();

    HomeInteractor.ProgressListener progressListener();

    IncidentStorage incidentStorage();

    MenuStream menuStream();

    SearchBarStream searchBarStream();

    SelectedIncidentStream selectedIncidentStream();
}
