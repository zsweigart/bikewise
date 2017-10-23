package com.zsweigart.bikewise.incidents.details.fragment;

import com.zsweigart.bikewise.incidents.SelectedIncidentStream;
import com.zsweigart.core_components.FragmentComponent;
import com.zsweigart.networking.BikeWiseClient;

import dagger.Component;

/**
 * Created by zsweigart on 9/24/17.
 */
@Component(modules = DetailsFragmentModule.class, dependencies = DetailsFragmentParentComponent.class)
@DetailsFragmentScope
public interface DetailsFragmentComponent extends FragmentComponent<DetailsDependencyFragment> {

    BikeWiseClient bikeWiseClient();

    SelectedIncidentStream selectedIncidentStream();
}
