package com.forateq.farmfinancialreport.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.activeandroid.util.SQLiteUtils;

import java.util.List;

/**
 * Created by Vallejos Family on 2/10/2016.
 */
@Table(name = "CashOut")
public class CashOutModel extends Model {

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "date")
    private String date;

    @Column(name = "amount")
    private String amount;

    @Column(name = "task")
    private String task;

    @Column(name = "description")
    private String description;

    @Column(name = "attachment_file")
    private String attachmentFile;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(String attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    public static void insertCashOut(String projectName, String date, String amount, String taskName, String description, String attachmentFile){
        CashOutModel cashOutModel = new CashOutModel();
        cashOutModel.setProjectName(projectName);
        cashOutModel.setDate(date);
        cashOutModel.setAmount(amount);
        cashOutModel.setTask(taskName);
        cashOutModel.setDescription(description);
        cashOutModel.setAttachmentFile(attachmentFile);
        cashOutModel.save();
    }

    public static List<CashOutModel> getExpenses(String projectName, String taskName){
        return new Select().from(CashOutModel.class).where("project_name = ? AND task = ?", projectName, taskName).execute();
    }

    public static List<CashOutModel> getSearchExpense(String projectName, String taskName, String name){
        String [] selectionArgs = new String[] {projectName, taskName, "%" + name + "%"};
        List<CashOutModel> searchItems =
                SQLiteUtils.rawQuery(CashOutModel.class,
                        "SELECT * FROM CashOut WHERE project_name = ? AND task = ? AND  description LIKE ?",
                        selectionArgs);
        return searchItems;
    }
}
