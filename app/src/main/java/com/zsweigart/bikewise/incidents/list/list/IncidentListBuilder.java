package com.zsweigart.bikewise.incidents.list.list;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zsweigart.bikewise.R;
import com.zsweigart.bikewise.incidents.list.HomeComponent;
import com.zsweigart.core_components.Builder;
import com.zsweigart.core_components.InteractorComponent;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by zsweigart on 10/19/17.
 */
public class IncidentListBuilder extends Builder<IncidentListRouter, HomeComponent> {

    public IncidentListBuilder(HomeComponent dependency) {
        super(dependency);
    }

    @NonNull
    public IncidentListRouter buildRouter(ViewGroup parentViewGroup) {
        IncidentListView incidentListView = (IncidentListView) LayoutInflater.from(parentViewGroup.getContext())
                .inflate(R.layout.incident_list, parentViewGroup, false);
        IncidentListInteractor incidentListInteractor = new IncidentListInteractor();

        IncidentListComponent incidentListComponent = DaggerIncidentListBuilder_IncidentListComponent.builder()
                .incidentListModule(new IncidentListModule(incidentListView, incidentListInteractor))
                .homeComponent(getDependency())
                .build();

        return new IncidentListRouter(incidentListView, incidentListComponent, incidentListInteractor);
    }

    @Component(modules = IncidentListModule.class, dependencies = HomeComponent.class)
    @IncidentListScope
    public interface IncidentListComponent extends InteractorComponent<IncidentListInteractor> { }

    @Module
    public class IncidentListModule {

        private IncidentListView incidentListView;
        private IncidentListInteractor incidentListInteractor;

        public IncidentListModule(IncidentListView incidentListView, IncidentListInteractor incidentListInteractor) {
            this.incidentListView = incidentListView;
            this.incidentListInteractor = incidentListInteractor;
        }

        @Provides
        @IncidentListScope
        IncidentListPresenter incidentListPresenter() {
            return new IncidentListPresenter(incidentListView, incidentListInteractor);
        }
    }

    @Scope
    @Retention(RetentionPolicy.CLASS)
    public @interface IncidentListScope { }
}
