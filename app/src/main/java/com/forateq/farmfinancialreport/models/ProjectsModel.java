package com.forateq.farmfinancialreport.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.activeandroid.util.SQLiteUtils;

import java.util.List;

/**
 * Created by Vallejos Family on 2/4/2016.
 */
@Table(name = "Project")
public class ProjectsModel  extends Model{

    @Column(name = "project_name")
    private String projectName;
    @Column(name = "project_start")
    private String projectStart;
    @Column(name = "project_end")
    private String projectEnd;
    @Column(name = "project_description")
    private String projectDescription;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectStart() {
        return projectStart;
    }

    public void setProjectStart(String projectStart) {
        this.projectStart = projectStart;
    }

    public String getProjectEnd() {
        return projectEnd;
    }

    public void setProjectEnd(String projectEnd) {
        this.projectEnd = projectEnd;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public static List<ProjectsModel> getProjectNames(){
        return new Select().from(ProjectsModel.class).execute();
    }

    public static ProjectsModel getProjectByName(String projectName){
        return new Select().from(ProjectsModel.class).where("project_name = ?", projectName).executeSingle();
    }

    public static List<ProjectsModel> getSearchProject(String name){
        String [] selectionArgs = new String[] {"%" + name + "%"};
        List<ProjectsModel> searchItems =
                SQLiteUtils.rawQuery(ProjectsModel.class,
                        "SELECT * FROM Project WHERE project_name  LIKE ?",
                        selectionArgs);
        return searchItems;
    }
}
