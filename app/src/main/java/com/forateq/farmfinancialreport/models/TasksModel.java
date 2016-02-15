package com.forateq.farmfinancialreport.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.activeandroid.util.SQLiteUtils;

import java.util.List;

/**
 * Created by Vallejos Family on 2/6/2016.
 */
@Table(name = "Tasks")
public class TasksModel extends Model {

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "task_name")
    private String taskName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public static List<TasksModel> getTasksByProject(String projectName){
        return new Select().from(TasksModel.class).where("project_name = ?", projectName).execute();
    }

    public static List<TasksModel> getSearchProject(String projectName, String name){
        String [] selectionArgs = new String[] {projectName, "%" + name + "%"};
        List<TasksModel> searchItems =
                SQLiteUtils.rawQuery(TasksModel.class,
                        "SELECT * FROM Tasks WHERE project_name = ? AND  task_name LIKE ?",
                        selectionArgs);
        return searchItems;
    }
}
