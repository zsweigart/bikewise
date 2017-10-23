package com.zsweigart.bikewise.incidents.list.fragment;

import com.zsweigart.bikewise.incidents.list.searchbar.SearchBarStream;
import com.zsweigart.bikewise.lifecycle.MenuStream;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zsweigart on 9/24/17.
 */
@Module
public class HomeFragmentModule {

    public HomeFragmentModule() {
    }

    @Provides
    @HomeFragmentScope
    MenuStream menuStream() {
        return new MenuStream();
    }

    @Provides
    @HomeFragmentScope
    SearchBarStream searchBarShownStream() {
        return new SearchBarStream();
    }

}
