package com.forateq.farmfinancialreport.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.forateq.farmfinancialreport.FarmApplication;
import com.forateq.farmfinancialreport.MainActivity;
import com.forateq.farmfinancialreport.R;
import com.forateq.farmfinancialreport.models.TasksModel;
import com.forateq.farmfinancialreport.views.TaskDetailsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vallejos Family on 2/6/2016.
 */
public class TaskCustomAdapter extends BaseAdapter {
    private Context context;
    private static LayoutInflater inflater=null;
    private List<String> projectsList;
    private View view;
    private String projectName;

    public TaskCustomAdapter(Context context, View view, String projectName) {
        // TODO Auto-generated constructor stub
        this.context = context;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.view = view;
        this.projectName = projectName;
        projectsList = new ArrayList<String>();
    }

    public void addProject(String projectName){
        projectsList.add(projectName);
        notifyDataSetChanged();
    }

    public void removeProject(String taskName){
        projectsList.remove(taskName);
        notifyDataSetChanged();
    }

    public void clearList(){
        projectsList.clear();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return projectsList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.project_list, null);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TasksModel tasksModel = TasksModel.getTask(holder.tv.getText().toString());
                TaskDetailsView taskDetailsView = new TaskDetailsView(context, projectName, holder.tv.getText().toString());
                taskDetailsView.getTaskNameEditText().setText(tasksModel.getTaskName());
                taskDetailsView.getTaskStartDateEditText().setText(tasksModel.getTaskStartDate());
                taskDetailsView.getTaskEndDateEditText().setText(tasksModel.getTaskEndDate());
                taskDetailsView.getTaskDetailsEditText().setText(tasksModel.getTaskDetails());
                FarmApplication.viewDeque.add(view);
                MainActivity.replaceView(taskDetailsView);
            }
        });
        holder.tv=(TextView) rowView.findViewById(R.id.project_name);
        holder.tv.setText(projectsList.get(position));
        return rowView;
    }

}
