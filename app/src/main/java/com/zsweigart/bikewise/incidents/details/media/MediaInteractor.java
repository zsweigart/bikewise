package com.zsweigart.bikewise.incidents.details.media;

import com.zsweigart.core_components.ViewInteractor;
import com.zsweigart.models.Media;

import javax.inject.Inject;

/**
 * Created by zsweigart on 10/22/17.
 */
public class MediaInteractor extends ViewInteractor<MediaPresenter, MediaRouter> {

    @Inject Media media;

    @Override
    protected void didBecomeActive() {
        super.didBecomeActive();

        getPresenter().loadMedia(media);
    }
}
