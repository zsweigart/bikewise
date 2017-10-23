package com.zsweigart.bikewise.incidents.details.media;

import com.zsweigart.core_components.ViewRouter;

/**
 * Created by zsweigart on 10/22/17.
 */
public class MediaRouter extends ViewRouter<MediaView, MediaBuilder.MediaComponent, MediaInteractor> {

    public MediaRouter(
            MediaView view,
            MediaBuilder.MediaComponent component,
            MediaInteractor interactor) {
        super(view, component, interactor);
    }
}
