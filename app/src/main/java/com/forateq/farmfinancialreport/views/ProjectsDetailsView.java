package com.forateq.farmfinancialreport.views;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.forateq.farmfinancialreport.R;
import com.forateq.farmfinancialreport.adapters.TaskCustomAdapter;
import com.forateq.farmfinancialreport.models.TasksModel;
import com.melnykov.fab.FloatingActionButton;

/**
 * Created by Vallejos Family on 2/6/2016.
 */
public class ProjectsDetailsView extends LinearLayout implements View.OnClickListener {

    private TextView projectName;
    private TextView projectStartDate;
    private TextView projectEndDate;
    private TextView projectDetails;
    private EditText searchEditText;
    private ListView tasksListView;
    private FloatingActionButton fab;
    private TaskCustomAdapter adapter;
    private String projectNameString;
    private Context context;

    public ProjectsDetailsView(Context context, String projectNameString) {
        super(context);
        this.context = context;
        this.projectNameString = projectNameString;
        init();
    }

    public ProjectsDetailsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public ProjectsDetailsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public void init(){
        inflate(getContext(), R.layout.project_details_view, this);
        projectName = (TextView) findViewById(R.id.project_name);
        projectStartDate = (TextView) findViewById(R.id.project_start_date);
        projectEndDate = (TextView) findViewById(R.id.project_end_date);
        projectDetails = (TextView) findViewById(R.id.project_description);
        tasksListView = (ListView) findViewById(R.id.tasks_container);
        searchEditText = (EditText) findViewById(R.id.search);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        adapter = new TaskCustomAdapter(context);
        addTasksToList();
        tasksListView.setAdapter(adapter);
        fab.attachToListView(tasksListView);
        fab.setOnClickListener(this);
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
                for(TasksModel tasksModel : TasksModel.getSearchProject(getProjectNameString(), searchText)){
                    adapter.addProject(tasksModel.getTaskName());
                }
            }
        });
    }

    public TextView getProjectName() {
        return projectName;
    }

    public TextView getProjectStartDate() {
        return projectStartDate;
    }

    public TextView getProjectEndDate() {
        return projectEndDate;
    }

    public TextView getProjectDetails() {
        return projectDetails;
    }

    public String getProjectNameString() {
        return projectNameString;
    }

    public void addTasksToList(){
        Log.e("Project Name", getProjectNameString());
        for(TasksModel tasksModel : TasksModel.getTasksByProject(getProjectNameString())){
            adapter.addProject(tasksModel.getTaskName());
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.fab:{
                final AddTaskView addTaskView = new AddTaskView(context);
                final MaterialDialog.Builder createNoteDialog = new MaterialDialog.Builder(context)
                        .title("Add Task")
                        .titleColorRes(R.color.colorPrimaryText)
                        .backgroundColorRes(R.color.colorPrimaryDark)
                        .widgetColorRes(R.color.colorPrimaryText)
                        .customView(addTaskView, true)
                        .positiveText("Ok")
                        .positiveColorRes(R.color.colorPrimaryText)
                        .negativeText("Cancel")
                        .negativeColorRes(R.color.colorPrimaryText)
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                super.onPositive(dialog);
                                adapter.addProject(addTaskView.getTaskNameEditText().getText().toString());
                                TasksModel tasksModel = new TasksModel();
                                Log.e("Project Name", getProjectNameString());
                                tasksModel.setProjectName(getProjectNameString());
                                tasksModel.setTaskName(addTaskView.getTaskNameEditText().getText().toString());
                                tasksModel.save();
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
}
