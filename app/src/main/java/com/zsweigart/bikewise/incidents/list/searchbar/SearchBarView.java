package com.zsweigart.bikewise.incidents.list.searchbar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.zsweigart.bikewise.R;

/**
 * View which represents the search bar
 */
public class SearchBarView extends LinearLayout {

    private EditText idEntry;
    private Button searchBtn;
    private Button closeBtn;

    public SearchBarView(Context context) {
        this(context, null);
    }

    public SearchBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        idEntry = findViewById(R.id.search_bar_search_edit);
        searchBtn = findViewById(R.id.search_bar_search_search_button);
        closeBtn = findViewById(R.id.search_bar_search_close_button);
        idEntry.requestFocus();
    }

    /**
     * @param clickListener {@link OnClickListener} which handles clicks of the search button
     */
    void setSearchClickListener(OnClickListener clickListener) {
        searchBtn.setOnClickListener(clickListener);
    }

    /**
     * @param clickListener {@link OnClickListener} which handles clicks of the close button
     */
    void setCloseOnClickListener(OnClickListener clickListener) {
        closeBtn.setOnClickListener(clickListener);
    }

    /**
     * @return The current text in the idEntry EditText
     */
    String getIdText() {
        return idEntry.getText().toString();
    }

    /**
     * Remove the text from the idEntry EditText and set focus to the field
     */
    void clearText() {
        idEntry.setText("");
        idEntry.requestFocus();
    }
}
