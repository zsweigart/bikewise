package com.zsweigart.bikewise.incidents.list.actionbar;

import com.zsweigart.core_components.Router;

/**
 * Created by zsweigart on 10/20/17.
 */
public class ActionBarRouter extends Router<ActionBarBuilder.ActionBarComponent, ActionBarInteractor> {

    public ActionBarRouter(
            ActionBarBuilder.ActionBarComponent component,
            ActionBarInteractor interactor) {
        super(component, interactor);
    }
}
