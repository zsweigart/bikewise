package com.zsweigart.bikewise.incidents.details;

import com.zsweigart.bikewise.incidents.SelectedIncidentStream;
import com.zsweigart.bikewise.incidents.details.fragment.DetailsFragmentComponent;
import com.zsweigart.core_components.InteractorComponent;

import dagger.Component;

/**
 * Created by zsweigart on 10/19/17.
 */
@Component(modules = DetailsInteractorModule.class, dependencies = DetailsFragmentComponent.class)
@DetailsInteractorScope
public interface DetailsInteractorComponent extends InteractorComponent<DetailsInteractor> {

    DetailsActivity detailsActivity();

    SelectedIncidentStream selectedIncidentStream();
}
