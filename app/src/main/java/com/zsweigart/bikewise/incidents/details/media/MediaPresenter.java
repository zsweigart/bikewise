package com.zsweigart.bikewise.incidents.details.media;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;

import com.squareup.picasso.Picasso;
import com.zsweigart.bikewise.R;
import com.zsweigart.core_components.Presenter;
import com.zsweigart.models.Media;

/**
 * Created by zsweigart on 10/22/17.
 */
public class MediaPresenter extends Presenter<MediaView> {

    public MediaPresenter(MediaView view) {
        super(view);
    }

    public void loadMedia(Media media) {

        Picasso.with(view.getContext())
                .load(media.image_url())
                .placeholder(new ColorDrawable(ContextCompat.getColor(view.getContext(), R.color.colorAccent)))
                .into(view.getImageView());
        
        view.setSourceText(view.getContext().getString(R.string.source_label, media.image_url()));
    }
}
