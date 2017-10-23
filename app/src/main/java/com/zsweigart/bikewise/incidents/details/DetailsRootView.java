package com.zsweigart.bikewise.incidents.details;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zsweigart.bikewise.R;
import com.zsweigart.bikewise.incidents.details.media.MediaView;

/**
 * Created by zsweigart on 10/21/17.
 */
public class DetailsRootView extends RelativeLayout {

    private TextView titleTextView;
    private TextView occurredAtTextView;
    private TextView addressTextView;
    private TextView descriptionTextView;
    private FrameLayout mediaContainer;

    public DetailsRootView(Context context) {
        this(context, null);
    }

    public DetailsRootView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DetailsRootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        titleTextView = findViewById(R.id.details_root_title_text);
        occurredAtTextView = findViewById(R.id.details_root_occured_text);
        addressTextView = findViewById(R.id.details_root_address_text);
        descriptionTextView = findViewById(R.id.details_root_description_text);
        mediaContainer = findViewById(R.id.details_media_container_container);
    }

    void setTitle(String title) {
        titleTextView.setText(title);
    }

    void setOccurredAtText(String occurredAtText) {
        occurredAtTextView.setText(occurredAtText);
    }

    void setAddressText(String addressText) {
        addressTextView.setText(addressText);
        addressTextView.setVisibility(TextUtils.isEmpty(addressText) ? GONE : VISIBLE);
    }

    void setDescription(String description) {
        descriptionTextView.setText(description);
    }

    void addMediaView(MediaView mediaView) {
        mediaContainer.addView(mediaView);
        mediaContainer.setVisibility(VISIBLE);
    }
}
