package com.zsweigart.bikewise.incidents.list.actionbar;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.zsweigart.bikewise.incidents.list.HomeComponent;
import com.zsweigart.core_components.Builder;
import com.zsweigart.core_components.InteractorComponent;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import dagger.Component;
import dagger.Module;

/**
 * Created by zsweigart on 10/20/17.
 */
public class ActionBarBuilder extends Builder<ActionBarRouter, HomeComponent>{

    public ActionBarBuilder(HomeComponent dependency) {
        super(dependency);
    }

    @NonNull
    public ActionBarRouter buildRouter(ViewGroup parentViewGroup) {
        ActionBarComponent searchBarComponent = DaggerActionBarBuilder_ActionBarComponent.builder()
                .actionBarModule(new ActionBarModule())
                .homeComponent(getDependency())
                .build();

        return new ActionBarRouter(searchBarComponent, new ActionBarInteractor());
    }

    @Component(modules = ActionBarModule.class, dependencies = HomeComponent.class)
    @ActionBarScope
    public interface ActionBarComponent extends InteractorComponent<ActionBarInteractor> { }

    @Module
    public class ActionBarModule {

    }

    @Scope
    @Retention(RetentionPolicy.CLASS)
    public @interface ActionBarScope { }
}
