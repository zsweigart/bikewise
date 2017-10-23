package com.zsweigart.bikewise.incidents.list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zsweigart.bikewise.R;
import com.zsweigart.bikewise.incidents.list.list.IncidentListView;
import com.zsweigart.bikewise.incidents.list.searchbar.SearchBarView;

/**
 * View that houses all children views for the {@link HomeScope} and contains the progress overlay
 */
public class HomeRootView extends RelativeLayout {

    private LinearLayout container;
    private RelativeLayout progressOverlay;

    public HomeRootView(Context context) {
        this(context, null);
    }

    public HomeRootView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomeRootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        container = findViewById(R.id.home_root_view_container);
        progressOverlay = findViewById(R.id.home_root_view_progress_overlay);
    }

    /**
     * Show the progress overlay
     */
    void showProgressView() {
        progressOverlay.setVisibility(View.VISIBLE);
        progressOverlay.bringToFront();
    }

    /**
     * Hide the progress overlay
     */
    void hideProgressView() {
        progressOverlay.setVisibility(View.GONE);
    }

    /**
     * @param searchBarView to be added to the top of the main container
     */
    void addSearchView(SearchBarView searchBarView) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        searchBarView.setLayoutParams(params);
        int index = indexOfChild(findViewById(R.id.incident_list_view)) - 1;
        index = index < 0 ? 0 : index;
        container.addView(searchBarView, index);
    }

    /**
     * @param searchBarView to be removed from the main container
     */
    void removeSearchView(SearchBarView searchBarView) {
        container.removeView(searchBarView);
    }

    /**
     * @param incidentListView to be added to the main container
     */
    void addIncidentListView(IncidentListView incidentListView) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        incidentListView.setLayoutParams(params);
        container.addView(incidentListView);
    }

    /**
     * @param incidentListView to be removed from the main container
     */
    void removeIncidentListView(IncidentListView incidentListView) {
        container.removeView(incidentListView);
    }
}
