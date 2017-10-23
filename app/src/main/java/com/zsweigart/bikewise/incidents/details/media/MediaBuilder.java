package com.zsweigart.bikewise.incidents.details.media;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.zsweigart.bikewise.R;
import com.zsweigart.bikewise.incidents.details.DetailsInteractorComponent;
import com.zsweigart.core_components.Builder;
import com.zsweigart.core_components.InteractorComponent;
import com.zsweigart.models.Media;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by zsweigart on 10/22/17.
 */
public class MediaBuilder extends Builder<MediaRouter, DetailsInteractorComponent> {

    public MediaBuilder(DetailsInteractorComponent dependency) {
        super(dependency);
    }

    @NonNull
    public MediaRouter buildRouter(ViewGroup parentViewGroup, Media media) {
        MediaView mediaView = (MediaView) LayoutInflater.from(parentViewGroup.getContext())
                .inflate(R.layout.media_view, parentViewGroup, false);
        MediaInteractor mediaInteractor = new MediaInteractor();

        MediaComponent mediaComponent = DaggerMediaBuilder_MediaComponent.builder()
                .mediaModule(new MediaModule(mediaView, media))
                .detailsInteractorComponent(getDependency())
                .build();

        return new MediaRouter(mediaView, mediaComponent, mediaInteractor);
    }

    @Component(modules = MediaModule.class, dependencies = DetailsInteractorComponent.class)
    @MediaScope
    public interface MediaComponent extends InteractorComponent<MediaInteractor> { }

    @Module
    public class MediaModule {

        private final MediaView mediaView;
        private final Media media;

        public MediaModule(MediaView mediaView, Media media) {
            this.mediaView = mediaView;
            this.media = media;
        }

        @Provides
        @MediaScope
        MediaPresenter mediaPresenter() {
            return new MediaPresenter(mediaView);
        }

        @Provides
        @MediaScope
        Media media() {
            return media;
        }
    }

    @Scope
    @Retention(RetentionPolicy.CLASS)
    public @interface MediaScope { }
}
