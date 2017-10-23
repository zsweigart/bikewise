package com.zsweigart.bikewise.incidents.list.searchbar;

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
 * {@link Builder} for the {@link SearchBarBuilder.SearchBarScope} which handles defining dependencies, building the
 * object graph and building the router.
 */
public class SearchBarBuilder extends Builder<SearchBarRouter, HomeComponent> {

    public SearchBarBuilder(HomeComponent dependency) {
        super(dependency);
    }

    @NonNull
    public SearchBarRouter buildRouter(ViewGroup parentViewGroup) {
        SearchBarView searchBarView = (SearchBarView) LayoutInflater.from(parentViewGroup.getContext())
                .inflate(R.layout.search_bar, parentViewGroup, false);
        SearchBarInteractor searchBarInteractor = new SearchBarInteractor();

        SearchBarComponent searchBarComponent = DaggerSearchBarBuilder_SearchBarComponent.builder()
                .searchBarModule(new SearchBarModule(searchBarView, searchBarInteractor))
                .homeComponent(getDependency())
                .build();

        return new SearchBarRouter(searchBarView, searchBarComponent, searchBarInteractor);
    }

    @Component(modules = SearchBarModule.class, dependencies = HomeComponent.class)
    @SearchBarScope
    public interface SearchBarComponent extends InteractorComponent<SearchBarInteractor> { }

    @Module
    public class SearchBarModule {

        private SearchBarView searchBarView;
        private SearchBarInteractor searchBarInteractor;

        public SearchBarModule(SearchBarView searchBarView, SearchBarInteractor searchBarInteractor) {
            this.searchBarView = searchBarView;
            this.searchBarInteractor = searchBarInteractor;
        }

        @Provides
        @SearchBarScope
        SearchBarPresenter searchBarPresenter() {
            return new SearchBarPresenter(searchBarView, searchBarInteractor);
        }
    }

    @Scope
    @Retention(RetentionPolicy.CLASS)
    public @interface SearchBarScope { }
}
