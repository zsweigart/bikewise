package com.zsweigart.bikewise.incidents.list.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by zsweigart on 10/19/17.
 */
public class IncidentListView extends RecyclerView {

    public IncidentListView(Context context) {
        this(context, null);
    }

    public IncidentListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IncidentListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
}
