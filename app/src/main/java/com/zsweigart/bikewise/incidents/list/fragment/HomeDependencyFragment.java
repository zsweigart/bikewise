package com.zsweigart.bikewise.incidents.list.fragment;

import com.zsweigart.bikewise.MainApplication;
import com.zsweigart.bikewise.lifecycle.ConfigurationStream;
import com.zsweigart.bikewise.lifecycle.MenuStream;
import com.zsweigart.core_components.DependencyFragment;
import com.zsweigart.networking.BikeWiseClient;

import javax.inject.Inject;

/**
 *
 */
public class HomeDependencyFragment extends DependencyFragment<HomeFragmentComponent> {

    @Inject BikeWiseClient bikeWiseClient;
    @Inject ConfigurationStream configurationStream;
    @Inject MenuStream menuStream;

    @Override
    protected HomeFragmentComponent createComponent() {
        return DaggerHomeFragmentComponent.builder()
                .homeFragmentParentComponent(MainApplication.getMainComponent())
                .build();
    }

    public BikeWiseClient getBikeWiseClient() {
        return bikeWiseClient;
    }

    public ConfigurationStream getConfigurationStream() {
        return configurationStream;
    }

    public MenuStream getMenuStream() {
        return menuStream;
    }
}
