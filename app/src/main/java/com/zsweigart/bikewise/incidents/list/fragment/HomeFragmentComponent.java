package com.zsweigart.bikewise.incidents.list.fragment;

import com.zsweigart.bikewise.incidents.SelectedIncidentStream;
import com.zsweigart.bikewise.incidents.list.searchbar.SearchBarStream;
import com.zsweigart.bikewise.lifecycle.MenuStream;
import com.zsweigart.core_components.FragmentComponent;
import com.zsweigart.networking.BikeWiseClient;
import com.zsweigart.storage.IncidentStorage;

import dagger.Component;

/**
 * Created by zsweigart on 9/24/17.
 */
@Component(modules = HomeFragmentModule.class, dependencies = HomeFragmentParentComponent.class)
@HomeFragmentScope
public interface HomeFragmentComponent extends FragmentComponent<HomeDependencyFragment> {

    BikeWiseClient bikeWiseClient();

    IncidentStorage incidentStorage();

    MenuStream menuStream();

    SearchBarStream searchBarStream();

    SelectedIncidentStream selectedIncidentStream();
}
