package com.forateq.farmfinancialreport.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.forateq.farmfinancialreport.R;

/**
 * Created by Vallejos Family on 2/4/2016.
 */
public class ProjectListView extends LinearLayout {

    private TextView projectNameTextView;

    public ProjectListView(Context context) {
        super(context);
        init();
    }

    public ProjectListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProjectListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        inflate(getContext(), R.layout.project_list, this);
        projectNameTextView = (TextView) findViewById(R.id.project_name);
    }

    public TextView getProjectNameTextView() {
        return projectNameTextView;
    }
}
