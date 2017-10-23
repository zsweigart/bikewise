package com.zsweigart.bikewise.incidents.details.media;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zsweigart.bikewise.R;

/**
 * Created by zsweigart on 10/22/17.
 */
public class MediaView extends LinearLayout {

    private ImageView imageView;
    private TextView textView;

    public MediaView(Context context) {
        this(context, null);
    }

    public MediaView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MediaView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        imageView = findViewById(R.id.media_image);
        textView = findViewById(R.id.media_text);
    }

    ImageView getImageView() {
        return imageView;
    }

    void setSourceText(String text) {
        textView.setText(text);
    }
}
