package com.zsweigart.bikewise.incidents.details;

import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.zsweigart.bikewise.incidents.details.media.MediaBuilder;
import com.zsweigart.bikewise.incidents.details.media.MediaRouter;
import com.zsweigart.core_components.ViewRouter;
import com.zsweigart.models.Media;

/**
 * Created by zsweigart on 10/19/17.
 */
public class DetailsRouter extends ViewRouter<DetailsRootView, DetailsInteractorComponent, DetailsInteractor> {

    @Nullable private MediaRouter mediaRouter;

    public DetailsRouter(
            DetailsRootView detailsRootView,
            DetailsInteractorComponent component,
            DetailsInteractor interactor) {
        super(detailsRootView, component, interactor);
    }

    void routeToMedia(Media media) {
        if (mediaRouter == null) {
            mediaRouter = new MediaBuilder(getComponent()).buildRouter(getView(), media);
            attachChild(mediaRouter);
            if (mediaRouter.getView() != null) {
                getView().addMediaView(mediaRouter.getView());
            }
        }
    }
}
