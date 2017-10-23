package com.zsweigart.bikewise.incidents.details.fragment;

import com.zsweigart.bikewise.MainApplication;
import com.zsweigart.core_components.DependencyFragment;
import com.zsweigart.networking.BikeWiseClient;

import javax.inject.Inject;

/**
 *
 */
public class DetailsDependencyFragment extends DependencyFragment<DetailsFragmentComponent> {

    @Inject BikeWiseClient bikeWiseClient;

    @Override
    protected DetailsFragmentComponent createComponent() {
        return DaggerDetailsFragmentComponent.builder()
                .detailsFragmentParentComponent(MainApplication.getMainComponent())
                .build();
    }

    public BikeWiseClient getBikeWiseClient() {
        return bikeWiseClient;
    }
}
