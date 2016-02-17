package com.forateq.farmfinancialreport.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
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

    @Column(name = "task_start_date")
    private String taskStartDate;

    @Column(name = "task_end_date")
    private String taskEndDate;

    @Column(name = "task_details")
    private String taskDetails;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskStartDate() {
        return taskStartDate;
    }

    public void setTaskStartDate(String taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    public String getTaskEndDate() {
        return taskEndDate;
    }

    public void setTaskEndDate(String taskEndDate) {
        this.taskEndDate = taskEndDate;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public static List<TasksModel> getTasksByProject(String projectName){
        return new Select().from(TasksModel.class).where("project_name = ?", projectName).execute();
    }

    public static TasksModel getTask(String taskName){
        return new Select().from(TasksModel.class).where("task_name = ?", taskName).executeSingle();
    }

    public static void updateTask(String taskName, String taskStartDate, String taskEndDate, String taskDetails){
        TasksModel tasksModel = TasksModel.getTask(taskName);
        tasksModel.setTaskStartDate(taskStartDate);
        tasksModel.setTaskEndDate(taskEndDate);
        tasksModel.setTaskDetails(taskDetails);
        tasksModel.save();
    }

    public static void deleteTask(String taskName){
        new Delete().from(TasksModel.class).where("task_name = ?", taskName).execute();
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
