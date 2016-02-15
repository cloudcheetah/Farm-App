package com.forateq.farmfinancialreport.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.forateq.farmfinancialreport.R;

/**
 * Created by Vallejos Family on 2/4/2016.
 */
public class AddProjectView extends LinearLayout {

    private EditText projectName;
    private  EditText projectStartDate;
    private EditText projectEndDate;
    private EditText projectDetails;

    public AddProjectView(Context context) {
        super(context);
        init();
    }

    public AddProjectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AddProjectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        inflate(getContext(), R.layout.add_project_view, this);
        projectName = (EditText) findViewById(R.id.project_name);
        projectStartDate = (EditText) findViewById(R.id.start_date);
        projectStartDate.setClickable(true);
        projectStartDate.setFocusable(false);
        projectEndDate = (EditText) findViewById(R.id.end_date);
        projectEndDate.setClickable(true);
        projectEndDate.setFocusable(false);
        projectDetails = (EditText) findViewById(R.id.project_description);
    }

    public EditText getProjectName() {
        return projectName;
    }

    public EditText getProjectStartDate() {
        return projectStartDate;
    }

    public EditText getProjectEndDate() {
        return projectEndDate;
    }

    public EditText getProjectDetails() {
        return projectDetails;
    }
}
