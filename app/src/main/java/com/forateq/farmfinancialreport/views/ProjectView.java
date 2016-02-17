package com.forateq.farmfinancialreport.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.forateq.farmfinancialreport.R;
import com.forateq.farmfinancialreport.adapters.CustomAdapter;
import com.forateq.farmfinancialreport.models.ProjectsModel;
import com.melnykov.fab.FloatingActionButton;

import java.util.Calendar;

/**
 * Created by Vallejos Family on 2/4/2016.
 */
public class ProjectView extends LinearLayout implements View.OnClickListener {

    private ListView projectContainerLayout;
    private EditText searchEditText;
    private FloatingActionButton fab;
    private Context context;
    private CustomAdapter adapter;

    public ProjectView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ProjectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public ProjectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public void init(){
        inflate(getContext(), R.layout.view_project_menu, this);
        projectContainerLayout = (ListView) findViewById(R.id.projects_container);
        adapter = new CustomAdapter(context, this);
        addProjectsToList();
        projectContainerLayout.setAdapter(adapter);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(projectContainerLayout);
        searchEditText = (EditText) findViewById(R.id.search);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchText = searchEditText.getText().toString();
                adapter.clearList();
                for(ProjectsModel projectsModel : ProjectsModel.getSearchProject(searchText)){
                    adapter.addProject(projectsModel.getProjectName());
                }
            }
        });
        fab.setOnClickListener(this);
    }

    public ListView getProjectContainerLayout() {
        return projectContainerLayout;
    }

    public void removeProject(String projectName){
        adapter.removeProject(projectName);
    }

    public void addProjectsToList(){
        for(ProjectsModel projectsModel : ProjectsModel.getProjectNames()){
            adapter.addProject(projectsModel.getProjectName());
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.fab:{
                Log.e("FAB", "Clicked");
                final AddProjectView addProjectView = new AddProjectView(context);
                addProjectView.getProjectStartDate().setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setDate(addProjectView.getProjectStartDate());
                    }
                });
                addProjectView.getProjectEndDate().setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setDate(addProjectView.getProjectEndDate());
                    }
                });
                final MaterialDialog.Builder createNoteDialog = new MaterialDialog.Builder(context)
                        .title("Add project")
                        .titleColorRes(R.color.colorPrimaryText)
                        .backgroundColorRes(R.color.colorPrimaryDark)
                        .widgetColorRes(R.color.colorPrimaryText)
                        .customView(addProjectView, true)
                        .positiveText("Ok")
                        .positiveColorRes(R.color.colorPrimaryText)
                        .negativeText("Cancel")
                        .negativeColorRes(R.color.colorPrimaryText)
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                super.onPositive(dialog);
                                adapter.addProject(addProjectView.getProjectName().getText().toString());
                                ProjectsModel projectsModel = new ProjectsModel();
                                projectsModel.setProjectStart(addProjectView.getProjectStartDate().getText().toString());
                                projectsModel.setProjectEnd(addProjectView.getProjectEndDate().getText().toString());
                                projectsModel.setProjectName(addProjectView.getProjectName().getText().toString());
                                projectsModel.setProjectDescription(addProjectView.getProjectDetails().getText().toString());
                                projectsModel.save();
                            }

                            @Override
                            public void onNegative(MaterialDialog dialog) {
                                super.onNegative(dialog);
                            }
                        });
                final MaterialDialog addNoteDialog = createNoteDialog.build();
                addNoteDialog.show();
                break;
            }
        }
    }


    public void setDate(final EditText editText){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        editText.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }


}
