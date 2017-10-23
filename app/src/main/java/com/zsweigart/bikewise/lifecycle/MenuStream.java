package com.zsweigart.bikewise.lifecycle;

import android.view.MenuItem;

import com.jakewharton.rxrelay2.BehaviorRelay;

import io.reactivex.Observable;

/**
 * Created by zsweigart on 10/20/17.
 */
public class MenuStream {

    private BehaviorRelay<MenuItem> menuItemBehaviorRelay = BehaviorRelay.create();

    public Observable<MenuItem> menuItem() {
        return menuItemBehaviorRelay.hide();
    }

    public void setMenuItem(MenuItem menuItem) {
        menuItemBehaviorRelay.accept(menuItem);
    }
}
