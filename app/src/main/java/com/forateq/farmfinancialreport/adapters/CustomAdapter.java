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
import com.forateq.farmfinancialreport.models.ProjectsModel;
import com.forateq.farmfinancialreport.views.ProjectsDetailsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vallejos Family on 2/4/2016.
 */
public class CustomAdapter extends BaseAdapter {
    private Context context;
    private static LayoutInflater inflater=null;
    private List<String> projectsList;
    private View view;

    public CustomAdapter(Context context, View view) {
        // TODO Auto-generated constructor stub
        this.context = context;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        projectsList = new ArrayList<String>();
        this.view = view;
    }

    public void addProject(String projectName){
        projectsList.add(projectName);
        notifyDataSetChanged();
    }

    public void removeProject(String projectName){
        projectsList.remove(projectName);
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
                ProjectsModel projectsModel = ProjectsModel.getProjectByName(holder.tv.getText().toString());
                ProjectsDetailsView projectsDetailsView = new ProjectsDetailsView(context, projectsModel.getProjectName());
                projectsDetailsView.getProjectName().setText(projectsModel.getProjectName());
                projectsDetailsView.getProjectStartDate().setText(projectsModel.getProjectStart());
                projectsDetailsView.getProjectEndDate().setText(projectsModel.getProjectEnd());
                projectsDetailsView.getProjectDetails().setText(projectsModel.getProjectDescription());
                FarmApplication.viewDeque.addLast(view);
                MainActivity.replaceView(projectsDetailsView);
            }
        });
        holder.tv=(TextView) rowView.findViewById(R.id.project_name);
        holder.tv.setText(projectsList.get(position));
        return rowView;
    }

}
